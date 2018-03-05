package com.mindpool.easymed.sapiappointment.controller;

import com.mindpool.easymed.sapiappointment.dto.AppointmentDto;
import com.mindpool.easymed.sapiappointment.model.Appointment;
import com.mindpool.easymed.sapiappointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/appointment")
public class AppointmentController extends AbstractController {

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping()
    @Transactional(readOnly=true)
    public Long helloWorld() {
        return appointmentService.findByPatient(1l, new PageRequest(1,20)).getTotalElements();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDto createAppointment(@RequestBody AppointmentDto appointmentDto) {

        Appointment appointment = orikaMapperFacade.map(appointmentDto, Appointment.class);
        appointment = appointmentService.create(appointment);
        return orikaMapperFacade.map(appointment, AppointmentDto.class);
    }
}
