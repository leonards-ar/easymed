package com.mindpool.easymed.domain

import com.mindpool.easymed.domain.Patient
import grails.converters.JSON

class ApiPatientController {

    def list() {
        render (Patient.list() as JSON)
    }
}
