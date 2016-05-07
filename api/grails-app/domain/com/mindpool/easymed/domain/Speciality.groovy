package com.mindpool.easymed.domain

class Speciality {

    String name

    static constraints = {
    }

    static mapping = {
        table 'speciality'
        name column: 'name'
    }
}
