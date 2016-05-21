package com.mindpool.easymed.domain

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import com.mindpool.easymed.domain.UserRole

/**
 * Created by federicobarreraoro on 5/1/16.
 */
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable{

    String username
    String password
    boolean enabled = false
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this)*.role
    }

    static constraints = {
        username nullable:false, blank:false, unique:true
        password nullable:false, blank:false
    }

    static transients = ['accountExpired','accountLocked','passwordExpired']

    static mapping = {
        table 'users'
        cache true
        version false
        username column: 'dni'
        password column: 'password'
        enabled column: 'enabled'
    }

}
