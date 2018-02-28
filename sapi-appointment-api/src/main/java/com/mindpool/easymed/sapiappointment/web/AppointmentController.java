package com.mindpool.easymed.sapiappointment.web;

import com.mindpool.easymed.sapiappointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("/")
    @ResponseBody
    @Transactional(readOnly=true)
    public Long helloWorld() {
        return appointmentService.findByPatient(1l, new PageRequest(1,20)).getTotalElements();
    }
}
