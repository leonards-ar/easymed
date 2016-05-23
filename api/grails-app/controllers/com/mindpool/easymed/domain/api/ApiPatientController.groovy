package com.mindpool.easymed.domain.api

import com.mindpool.easymed.domain.Patient
import com.mindpool.easymed.domain.User
import grails.converters.JSON
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

import javax.servlet.http.HttpServletResponse

class ApiPatientController extends ApiController {

    def patientService

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    def create() {
        def jsPatient = request.JSON
        def patient = new Patient(new User(dni: jsPatient.dni, password:jsPatient.password),
                fullname: jasPatient.fullName, birth: jsonPatient.birth, phone: jsPatient.phone,
                cell: jsPatient.cell, email: jsPatient.email, nationality: jsPatient.nationality,
                street: jsPatient.street, city: jsPatient.city, state: jsPatient.state, zipCode: jsPatient.zipCode)

        try {
            patientService.savePatient(patient)
        } catch(DomainValidationException e){
            response.status = HttpServletResponse.SC_BAD_REQUEST
            render (this.createValidationErrors(e.errors) as JSON)
        }

        render (Patient.list() as JSON)
    }
}
