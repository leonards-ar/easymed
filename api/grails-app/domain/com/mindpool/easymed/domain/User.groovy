package com.mindpool.easymed.domain

/**
 * Created by federicobarreraoro on 5/1/16.
 */
class User {

    String dni
    String password

    static constraints = {
        dni nullable:false, blank:false, unique:true
        password nullable:false, blank:false
    }

    static mapping = {
        table 'user'
        cache true
        version false
        dni column: 'dni'
        password column: 'password'
    }

}
