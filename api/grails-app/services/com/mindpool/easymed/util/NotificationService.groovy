package com.mindpool.easymed.util

import com.mindpool.easymed.domain.Patient
import com.mindpool.easymed.domain.User
import grails.transaction.Transactional

@Transactional
class NotificationService {

    static transactional = false
    def mailService
    def grailsApplication
    def groovyPageRenderer

    def sendActivateUserNotification(Patient patient, String key){
        sendJobInstanceNotification(patient.email,
                        "Innova-Sistema@vitaflo.com.ar",
                        "Bienvenido a la pagina",
                        [fullname: patient.fullname, key: key],
                        "/mail_notification/activate_notification"
        )
    }

    private void sendJobInstanceNotification(String toAddress, String fromAddress, String notificationSubject, Map model , String template) {
        def mailBody = groovyPageRenderer.render(view: template, model: model)
        mailService.sendMail {
            to(toAddress)
            from(fromAddress)
            subject(notificationSubject)
            html(mailBody)
        }
    }
}
