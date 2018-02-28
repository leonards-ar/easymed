package com.mindpool.easymed.sapiappointment.domain;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="appointment", schema="easymed")
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long patient;
    private Long doctor;
    DateTime appointmentDate;

    @Column(name="patient", nullable=false)
    public Long getPatient() {
        return patient;
    }

    public void setPatient(Long patient) {
        this.patient = patient;
    }

    @Column(name="doctor", nullable=false)
    public Long getDoctor() {
        return doctor;
    }

    public void setDoctor(Long doctor) {
        this.doctor = doctor;
    }

    @Column(name="appointment_date", nullable=false)
    public DateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(DateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(patient, that.patient) &&
                Objects.equals(doctor, that.doctor) &&
                Objects.equals(appointmentDate, that.appointmentDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(patient, doctor, appointmentDate);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "patient=" + patient +
                ", doctor=" + doctor +
                ", appointmentDate=" + appointmentDate +
                '}';
    }
}
