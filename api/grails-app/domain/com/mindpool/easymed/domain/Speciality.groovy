package com.mindpool.easymed.domain

class Speciality {

    String name

    static constraints = {
    }

    static mapping = {
        version false
        table 'speciality'
        name column: 'name'
    }
}
