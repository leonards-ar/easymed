package com.mindpool.easymed.domain

import com.mindpool.easymed.errors.BusinessException
import com.mindpool.easymed.errors.DomainValidationException
import com.mindpool.easymed.errors.ErrorCodes
import grails.transaction.Transactional

@Transactional
class PatientService {
    def springSecurityService
    def userCacheService
    def notificationService

    def savePatient(Patient patient) {

        def user = User.findByUsername(patient?.user?.getUsername())

        if(user != null){
            if(user.enabled){
                throw new BusinessException("user.disabled", ErrorCodes.USER_IS_DISABLED)
            } else {
                throw new BusinessException("user.exists", ErrorCodes.DUPLICATED_USER)
            }
        }
        def plainPassword = patient.user.password
        patient.user.password = springSecurityService.encodePassword(plainPassword)

        if(!patient.validate()){
           throw new DomainValidationException(patient.errors)
        }
        //Save User
        patient.user.save(failOnError: true, flush: true)
        patient.save(failOnError: true, flush: true)
        String key = userCacheService.save(patient?.user?.id)
        notificationService.sendActivateUserNotification(patient, key)

        return user

    }

    def activateUser(String token){
        Long id = userCacheService.getElement(token)
        if(id == null){
            throw new IllegalArgumentException("User Not Found")
        }
        def user = User.get(id)
        user.enabled = true
        user.save(flush:true)
    }
}
