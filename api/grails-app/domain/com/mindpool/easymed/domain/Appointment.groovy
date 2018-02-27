package com.mindpool.easymed.domain

import org.joda.time.DateTime

class Appointment {
    DateTime dateTime
    Doctor doctor
    Patient patient
    String status


    static constraints = {
    }

    static mapping = {
        version false
    }
}
