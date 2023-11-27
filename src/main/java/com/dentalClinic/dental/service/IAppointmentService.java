package com.dentalClinic.dental.service;

import com.dentalClinic.dental.dto.input.appointment.AppointmentInputDto;
import com.dentalClinic.dental.dto.input.patient.PatientInputDto;
import com.dentalClinic.dental.dto.output.appointment.AppointmentOutputDto;
import com.dentalClinic.dental.dto.output.dentist.DentistOutputDto;
import com.dentalClinic.dental.dto.update.AppointmentUpdateInputDto;
import com.dentalClinic.dental.dto.update.DentistUpdateInputDto;
import com.dentalClinic.dental.exceptions.BadRequestException;
import com.dentalClinic.dental.exceptions.ResourceNotFoundException;


import java.util.List;

public interface IAppointmentService {


    AppointmentOutputDto registerAppointment(AppointmentInputDto appointment) throws BadRequestException;

    List<AppointmentOutputDto> listAppointments();

    AppointmentOutputDto searchAppointmentForId(Long id);

    AppointmentOutputDto updateAppointment(AppointmentUpdateInputDto appointment);

    void deleteAppointment(Long id) throws ResourceNotFoundException;



}


