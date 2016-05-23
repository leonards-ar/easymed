package com.mindpool.easymed.domain

import com.mindpool.easymed.domain.User
import org.springframework.security.core.userdetails.UserDetails

/**
 * Created by federico on 5/22/16.
 */
abstract class BaseController {

    def getAuthenticatedUserRoles() {
        return springSecurityService.authentication.getAuthorities()
    }

    def User getAuthenticatedUser() {
        def user = (UserDetails)springSecurityService.authentication.principal
        if (!springSecurityService.authentication.principal.getClass().equals(User.class)) {
            return new User(user.username, user.password, user.enabled, user.accountNonExpired, user.credentialsNonExpired, user.accountNonLocked, user.authorities, user.username, user.username + "@mulesoft.com")
        } else {
            return (User)user
        }
    }
}
