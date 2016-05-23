package com.mindpool.easymed.domain.api

import com.mindpool.easymed.domain.BaseController

com.mindpool.
import grails.util.GrailsNameUtils
import grails.validation.ValidationErrors
import org.joda.time.DateTime

import javax.servlet.http.HttpServletResponse
import java.text.SimpleDateFormat

/**
 * Created by federico on 5/22/16.
 */
abstract class ApiController extends BaseController {

    protected dateFormatter = new SimpleDateFormat("MM/dd/YYYY")

    protected def createError(String errorCode, Integer code) {
        return [message:message(code: errorCode) , code: code]
    }

    protected def invalidPayload() {
        return this.createError("Invalid Request Payload", HttpServletResponse.SC_BAD_REQUEST)
    }

    protected def notFoundError() {
        return this.createError("Resource Not Found", HttpServletResponse.SC_NOT_FOUND)
    }

    protected def serverError() {
        return this.createError("Internal server error", HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
    }
    protected def notAllowed() {
        return this.createError("Operation not allowed", HttpServletResponse.SC_BAD_REQUEST)
    }

    protected def formatDate(Date date) {
        return dateFormatter.format(date)
    }

    protected def formatDate(DateTime date) {
        return dateFormatter.format(date)
    }

    protected def formatDateWithPatter(Date date, String pattern) {
        def dateFormatter = new SimpleDateFormat(pattern)
        return dateFormatter.format(date)
    }
    protected def formatDateWithPatter(DateTime date, String pattern) {
        def dateFormatter = new SimpleDateFormat(pattern)
        return dateFormatter.format(date)
    }

    protected def createValidationErrors(ValidationErrors validation) {
        def list = []
        validation.allErrors.each { error ->
            def i18nKey = "api." + GrailsNameUtils.getShortName(error.getObjectName()) + "." + error.field + "." + error.getCode() + ".error"
            list << this.createError(message(code: i18nKey), ErrorCodes.VALIDATION_ERROR)
        }
        return list
    }

    protected def createMessageErrorsFrom(List errors) {
        def list = []
        errors.each { errorCode ->
            def i18nKey = "message.error.code." + errorCode
            list << this.createError(message(code: i18nKey), ErrorCodes.VALIDATION_ERROR)
        }
        return list
    }
}
