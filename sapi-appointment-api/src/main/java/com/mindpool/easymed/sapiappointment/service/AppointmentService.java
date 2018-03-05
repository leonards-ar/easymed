package com.mindpool.easymed.sapiappointment.service;

import com.mindpool.easymed.sapiappointment.model.Appointment;
import com.mindpool.easymed.sapiappointment.model.AppointmentRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Page findByPatient(Long patient, Pageable pageable){
        Assert.notNull(patient, "patient must not be null");
        return this.appointmentRepository.findByPatient(patient, pageable);

    }

    public Page findByDate(DateTime date){
        Assert.notNull(date, "Date must not be null");
        return this.appointmentRepository.findByAppointmentDate(date, new PageRequest(1, 20));
    }

    public Appointment create(Appointment appointment) {

        return appointmentRepository.save(appointment);
    }

}
