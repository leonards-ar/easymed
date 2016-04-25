import grails.plugin.springsecurity.rest.oauth.OauthUser
import grails.plugin.springsecurity.rest.oauth.OauthUserDetailsService
import groovy.util.logging.Slf4j

import org.pac4j.core.profile.CommonProfile
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsChecker
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException


@Slf4j
class DefaultOauthUserDetailsService implements OauthUserDetailsService {

	@Delegate
	UserDetailsService userDetailsService

	UserDetailsChecker preAuthenticationChecks

	OauthUser loadUserByUserProfile(CommonProfile userProfile, Collection<GrantedAuthority> defaultRoles)
			throws UsernameNotFoundException {
		UserDetails userDetails
		OauthUser oauthUser

		try {
			log.debug "Trying to fetch user details for user profile: ${userProfile}"
			userDetails = userDetailsService.loadUserByUsername userProfile.id

			log.debug "Checking user details with ${preAuthenticationChecks.class.name}"
			preAuthenticationChecks?.check(userDetails)

			Collection<GrantedAuthority> allRoles = userDetails.authorities + defaultRoles
			oauthUser = new OauthUser(userDetails.username, userDetails.password, allRoles, userProfile)
		} catch (UsernameNotFoundException unfe) {
			log.debug "User not found. Creating a new one with default roles: ${defaultRoles}"
			oauthUser = new OauthUser(userProfile.id, 'N/A', defaultRoles, userProfile)
		}

		return oauthUser
	}

}