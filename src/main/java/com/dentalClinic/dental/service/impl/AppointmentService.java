package com.dentalClinic.dental.service.impl;

import com.dentalClinic.dental.dto.input.appointment.AppointmentInputDto;
import com.dentalClinic.dental.dto.input.patient.PatientInputDto;
import com.dentalClinic.dental.dto.output.appointment.AppointmentOutputDto;
import com.dentalClinic.dental.dto.output.appointment.DentistAppointmentOutputDto;
import com.dentalClinic.dental.dto.output.appointment.PatientAppointmentOutputDto;
import com.dentalClinic.dental.dto.output.dentist.DentistOutputDto;
import com.dentalClinic.dental.dto.output.patient.PatientOutputDto;
import com.dentalClinic.dental.dto.update.AppointmentUpdateInputDto;
import com.dentalClinic.dental.entity.Appointment;
import com.dentalClinic.dental.entity.Dentist;
import com.dentalClinic.dental.entity.Patient;
import com.dentalClinic.dental.exceptions.BadRequestException;
import com.dentalClinic.dental.exceptions.ResourceNotFoundException;
import com.dentalClinic.dental.repository.AppointmentRepository;
import com.dentalClinic.dental.service.IAppointmentService;
import com.dentalClinic.dental.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {
    private final Logger LOGGER =
            LoggerFactory.getLogger(AppointmentService.class);
    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;

    private final PatientService patientService;

    private final DentistService dentistService;


    public AppointmentService(AppointmentRepository appointmentRepository,
                              ModelMapper modelMapper,
                              PatientService patientService,
                              DentistService dentistService) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
        this.patientService = patientService;
        this.dentistService = dentistService;
    }


    //POST - Register a new appointment
    /*
This method registers a new appointment. It validates the patient and dentist
 IDs, creates and saves the appointment entity, and then converts and returns
  the appointment output DTO.
Parameters: appointment: An AppointmentOutputDto object containing the
appointment details.
Return Value: An AppointmentOutputDto object containing the details of the
created appointment, or null if the appointment could not be registered.
Example Code:

AppointmentOutputDto appointmentOutputDto = registerAppointment(new
    AppointmentOutputDto(1L, 2L, "2023-11-29", "10:00"));
if (appointmentOutputDto != null) {
    System.out.println("Appointment created: " + JsonPrinter.toString
    (appointmentOutputDto));
} else {
    System.out.println("Appointment could not be registered");
}

    */

    @Override
    public AppointmentOutputDto registerAppointment(AppointmentInputDto appointmentInputDto) throws BadRequestException {
        AppointmentOutputDto appointmentOutputDto;

        PatientOutputDto patient =
                patientService.searchPatientForId(appointmentInputDto.getPatient());
        DentistOutputDto dentist =
                dentistService.searchDentistForId(appointmentInputDto.getDentist());

        String pacienteNoEnBdd = "El paciente no se encuentra en nuestra base" +
                " de datos";
        String odontologoNoEnBdd = "El odontologo no se encuentra en nuestra " +
                "base de datos";

        if (patient == null || dentist == null) {
            if (patient == null && dentist == null) {
                LOGGER.error("El paciente y el odontologo no se encuentran en" +
                        " nuestra base de datos");
                throw new BadRequestException("El paciente y el odontologo no" +
                        " se encuentran en nuestra base de datos");
            } else if (patient == null) {
                LOGGER.error(pacienteNoEnBdd);
                throw new BadRequestException(pacienteNoEnBdd);
            } else {
                LOGGER.error(odontologoNoEnBdd);
                throw new BadRequestException(odontologoNoEnBdd);
            }
        } else {

            Patient patient2 = modelMapper.map(patient, Patient.class);
            Dentist dentist2 = modelMapper.map(dentist, Dentist.class);

            Appointment newAppointment =
                    appointmentRepository.save(modelMapper.map(appointmentInputDto,
                            Appointment.class));
            newAppointment.setPatient(patient2);
            newAppointment.setDentist(dentist2);
            appointmentOutputDto = entidadADto(newAppointment);

            LOGGER.info("Nuevo turno registrado con exito: {}",
                    appointmentOutputDto);
        }
        return appointmentOutputDto;
    }

    private PatientAppointmentOutputDto patientOutputDtoToOutputAppointmentDto(Long id) {
        return modelMapper.map(patientService.searchPatientForId(id),
                PatientAppointmentOutputDto.class);
    }

    private DentistAppointmentOutputDto dentistOutputDtoToOutputAppointmentDto(Long id) {
        return modelMapper.map(dentistService.searchDentistForId(id),
                DentistAppointmentOutputDto.class);
    }

    private AppointmentOutputDto entidadADto(Appointment appointment) {
        AppointmentOutputDto appointmentOutputDto = modelMapper.map(appointment,
                AppointmentOutputDto.class);
        appointmentOutputDto.setPatientAppointmentOutputDto(patientOutputDtoToOutputAppointmentDto(appointment.getPatient().getId()));
        appointmentOutputDto.setDentistAppointmentOutputDto(dentistOutputDtoToOutputAppointmentDto(appointment.getDentist().getId()));
        return appointmentOutputDto;
    }


    ;

    private boolean isValidPatientAndDentist(PatientOutputDto patient,
                                             DentistOutputDto dentist) {
        return patient != null && patient.getId() != null && dentist != null && dentist.getId() != null;
    }

    ;


    //GET - List dentists
    /*
    listAppointments: This method retrieves all appointment records from the
    database;
    Parameters: None;
    Return Value: A list of AppointmentOutputDto instances, each containing
    the information of an appointment;
    Usage Example:
    List<AppointmentOutputDto> appointmentsOutputDto = dentistService
    .listAppointments();
    System.out.println("List of appointments:");
    for (AppointmentOutputDto appointmentOutputDto : appointmentsOutputDto) {
    System.out.println(appointmentOutputDto);
    }
*/

    @Override
    public List<AppointmentOutputDto> listAppointments() {

        List<AppointmentOutputDto> appointmentsOutputDto =
                appointmentRepository.findAll()
                        .stream()
                        .map(appointment -> modelMapper.map(appointment,
                                AppointmentOutputDto.class))
                        .toList();

        if (LOGGER.isInfoEnabled())
            LOGGER.info("List of all appointments: {}",
                    JsonPrinter.toString(appointmentsOutputDto));
        return appointmentsOutputDto;
    }


    //GET -- Search an appointment by ID
    /* This method searches for an appointment by its ID. It retrieves the
    appointment entity from the repository, converts it to an
    AppointmentOutputDto object, and returns the result.
    Parameters: id: The ID of the appointment to search for.
    Return Value: An AppointmentOutputDto object containing the details of
    the appointment, or null if the appointment was not found.
    Example Code:

    AppointmentOutputDto appointmentOutputDto = searchAppointmentForId(1L);
    if (appointmentOutputDto != null) {
        System.out.println("Appointment found: " + JsonPrinter.toString
    (appointmentOutputDto));
    } else {
        System.out.println("Appointment not found");
    }
*/
    @Override
    public AppointmentOutputDto searchAppointmentForId(Long id) {

        Appointment searchedAppointment =
                appointmentRepository.findById(id).orElse(null);
        AppointmentOutputDto appointmentFound = null;

        if (searchedAppointment != null) {
            appointmentFound = modelMapper.map(searchedAppointment,
                    AppointmentOutputDto.class);
            LOGGER.info("Appointment found: {}",
                    JsonPrinter.toString(appointmentFound));
        } else LOGGER.error("The id is not registered in the database");
        return appointmentFound;
    }


    // PUT - Update an Appointment
    /* This method updates an existing appointment. It retrieves the
    appointment entity from the repository, updates its details based on the
    provided input, saves the updated entity, and converts and returns the
    updated appointment output DTO.
    Parameters: appointment: An AppointmentUpdateInputDto object containing
    the updated
    appointment details.
    Return Value: An AppointmentOutputDto object containing the details of
    the updated appointment, or null if the appointment was not found.
    Example Code:

    AppointmentUpdateInputDto appointmentUpdateInputDto = new
    AppointmentUpdateInputDto(1L, "2023-11-30", "11:00");
    try {
        AppointmentOutputDto updatedAppointment = updateAppointment
        (appointmentUpdateInputDto);
    if (updatedAppointment != null) {
            System.out.println("Appointment updated: " + JsonPrinter.toString
            (updatedAppointment));
    } else {
        System.out.println("Appointment not found");
    }
    } catch (ConfigDataResourceNotFoundException e) {
        System.out.println("Error updating appointment: " + e.getMessage());
}
*/
    @Override
    public AppointmentOutputDto updateAppointment(AppointmentUpdateInputDto appointment) throws ConfigDataResourceNotFoundException {

        Appointment appointmentReceived = modelMapper.map(appointment,
                Appointment.class);
        Appointment appointmentToUpdate =
                appointmentRepository.findById(appointmentReceived.getId()).orElse(null);
        AppointmentOutputDto appointmentOutputDto = null;
        if (appointmentToUpdate != null) {
            appointmentToUpdate = appointmentReceived;
            appointmentRepository.save(appointmentToUpdate);

            appointmentOutputDto = modelMapper.map(appointmentToUpdate,
                    AppointmentOutputDto.class);
            LOGGER.warn("Updated appointment: {}",
                    JsonPrinter.toString(appointmentOutputDto));
        }


        return null;
    }


    //DELETE - Delete an appointment by ID
    /*
    deleteAppointment: This method deletes an appointment by its ID. It checks
     if the appointment exists, and if so, it deletes the appointment and
     logs a message indicating successful deletion. If the appointment is not
      found,
    it throws a ResourceNotFoundException.
    Parameters: id: The ID of the appointment to delete.
    Return Value: Void.
    Example Code:

    try {
        deleteAppointment(1L);
           System.out.println("Appointment deleted");
    } catch (ResourceNotFoundException e) {
           System.out.println("Error deleting appointment: " + e.getMessage());
}
*/
    @Override
    public void deleteAppointment(Long id) throws ResourceNotFoundException {

        if (appointmentRepository.findById(id).orElse(null) != null) {
            appointmentRepository.deleteById(id);
            LOGGER.warn("The appointment was deleted: {}", id);
        } else {
            LOGGER.error("The appointment with the id " + id + " was not " +
                    "found");
            throw new ResourceNotFoundException("The appointment with the id "
                    + id + " was not found");
        }

    }


}



