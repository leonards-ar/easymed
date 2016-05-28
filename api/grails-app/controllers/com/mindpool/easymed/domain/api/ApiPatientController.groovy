package com.mindpool.easymed.domain.api

import com.mindpool.easymed.domain.Patient
import com.mindpool.easymed.domain.User
import com.mindpool.easymed.errors.BusinessException
import com.mindpool.easymed.errors.DomainValidationException
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

import javax.servlet.http.HttpServletResponse

class ApiPatientController extends ApiController {

    def patientService

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Secured(["IS_AUTHENTICATED_ANONYMOUSLY"])
    def create() {
        def jsPatient = request.JSON
        def patient = new Patient(user: new User(username: jsPatient.dni, password:jsPatient.password),
                fullname: jsPatient.fullname, birth: dateFormatter.parse(jsPatient.birth), phone: jsPatient.phone,
                cell: jsPatient.cell, email: jsPatient.email, nationality: jsPatient.nationality,
                street: jsPatient.street, city: jsPatient.city, state: jsPatient.state, zipCode: jsPatient.zipCode)

        try {
            patientService.savePatient(patient)
            response.status = HttpServletResponse.SC_CREATED
            render (patient as JSON)
        } catch(DomainValidationException e){
            response.status = HttpServletResponse.SC_BAD_REQUEST
            render (this.createValidationErrors(e.errors) as JSON)
        } catch(BusinessException e){
            render (this.createError(e.message, e.errorCode))
        } catch (Exception e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            render (this.invalidPayload() as JSON)
        }
    }

    def activate() {
        String key = params.token
        try {
            patientService.activateUser(key)
            render HttpServletResponse.SC_NO_CONTENT as JSON
        } catch(IllegalArgumentException e){
            response.status = HttpServletResponse.SC_NOT_FOUND
            render (notFoundError() as JSON)
        } catch(Exception e){
            e.printStackTrace()
            response.status = HttpServletResponse.SC_BAD_REQUEST
            render (this.invalidPayload() as JSON)
        }

    }
}
