package com.mindpool.easymed.domain

import org.joda.time.DateTime

/**
 * Created by federicobarreraoro on 5/1/16.
 */
class Patient {

    User user
    String dni
    String fullname
    DateTime birth
    String phone
    String cell
    String email
    String gender
    String nationality
    String street
    String city
    String state
    String zipCode

    Boolean active
}
