package com.dentalClinic.dental.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "APPOINTMENTS")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dateAndTime;

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    private Dentist dentist;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;


    /*Builders (an empty one, a full one and another without id*/

    public Appointment() {
    }

    public Appointment(LocalDateTime dateAndTime, Dentist dentist, Patient patient) {
        this.dateAndTime = dateAndTime;
        this.dentist = dentist;
        this.patient = patient;
    }

    public Appointment(Long id, LocalDateTime dateAndTime, Dentist dentist, Patient patient) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.dentist = dentist;
        this.patient = patient;
    }

    /*Getter & Setters*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /*To String*/

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", dateAndTime=" + dateAndTime +
                ", dentist=" + dentist +
                ", patient=" + patient +
                '}';
    }


}
