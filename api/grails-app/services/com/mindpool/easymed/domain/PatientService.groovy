package com.mindpool.easymed.domain

import com.mindpool.easymed.errors.BusinessException
import com.mindpool.easymed.errors.DomainValidationException
import com.mindpool.easymed.errors.ErrorCodes
import grails.transaction.Transactional

import javax.persistence.EntityExistsException

@Transactional
class PatientService {

    def savePatient(Patient patient) {

        def user = User.findByUsername(patient?.user?.getUsername())

        if(user != null){
            if(user.enabled){
                throw new BusinessException("user.disabled", ErrorCodes.USER_IS_DISABLED)
            } else {
                throw new BusinessException("user.exists", ErrorCodes.DUPLICATED_USER)
            }
        }

        if(!user.validate()){
            new DomainValidationException(user.errors)
        }
        user.save(failOnError: true, flush: true)

        return user

    }
}
