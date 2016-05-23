package com.mindpool.easymed.domain

import com.mindpool.easymed.domain.BaseController
import grails.converters.JSON

/**
 * Created by federico on 5/22/16.
 */
class ErrorController extends BaseController {

    def handle = {
        def exception = request.exception
        def controllerName = exception?.className
        def isAPIController  = controllerName.toLowerCase().startsWith("api");
        if (isAPIController) {
            response.status = 500
            def result = [message: "Internal server error", code: 500]

            render (result as JSON)

        }  else {
            render(view:'/error')
        }
    }

}
