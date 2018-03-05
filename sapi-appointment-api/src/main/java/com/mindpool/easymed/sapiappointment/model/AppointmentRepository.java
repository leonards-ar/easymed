package com.mindpool.easymed.sapiappointment.model;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    Page<Appointment> findByPatient(Long patient, Pageable pageable);
    Page<Appointment> findByDoctor(Long doctor, DateTime datetime, Pageable pageable);
    Page<Appointment> findByAppointmentDate(DateTime appointmentDate, Pageable pageable);

}