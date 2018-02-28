package com.mindpool.easymed.sapiappointment.service;

import com.mindpool.easymed.sapiappointment.domain.AppointmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;


public class AppointmentService {

    private  AppointmentRepository appointmentRepository;


    public void setAppointmentRepository(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Page findByPatient(Long patient, Pageable pageable){
        Assert.notNull(patient, "patient must not be null");
        return this.appointmentRepository.findByPatient(patient, pageable);

    }

}
