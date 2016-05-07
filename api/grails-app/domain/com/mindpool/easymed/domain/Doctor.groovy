package com.mindpool.easymed.domain

import org.joda.time.DateTime

/**
 * Created by federicobarreraoro on 5/1/16.
 */
class Doctor {

    String firstName
    String lastName
    DateTime creationDate
    DateTime modificationDate
    Speciality speciality
    Institution institution
}
