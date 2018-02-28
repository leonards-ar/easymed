package com.mindpool.easymed.sapiappointment.domain;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    Page<Appointment> findByPatient(Long patient, Pageable pageable);
    Page<Appointment> findByDoctor(Long doctor, DateTime datetime, Pageable pageable);
    Page<Appointment> findByAppointmentDate(DateTime appointmentDate, Pageable pageable);

}