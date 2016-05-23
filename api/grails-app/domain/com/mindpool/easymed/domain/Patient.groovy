package com.mindpool.easymed.domain

import org.joda.time.DateTime

/**
 * Created by federicobarreraoro on 5/1/16.
 */
class Patient {

    User user
    String fullname
    DateTime birth
    String phone
    String cell
    String email
    String nationality
    String street
    String city
    String state
    String zipCode


    static constraints = {
        email email:true
    }

    static mapping = {
        table 'patient'
        cache true
        version false
        fullname column: 'full_name'
        user column: 'user_id'
        birth column: 'birth'
        phone column: 'phone'
        cell column: 'cell_phone'
        email column: 'email'
        nationality column: 'nationality'
        street column:'street'
        zipCode column:'zipcode'
        city column:'city'
        state column: 'state'
    }

}
