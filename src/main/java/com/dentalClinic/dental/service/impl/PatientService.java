package com.dentalClinic.dental.service.impl;

import com.dentalClinic.dental.dto.input.patient.PatientInputDto;
import com.dentalClinic.dental.dto.output.patient.PatientOutputDto;
import com.dentalClinic.dental.dto.update.PatientUpdateInputDto;
import com.dentalClinic.dental.entity.Patient;
import com.dentalClinic.dental.exceptions.ResourceNotFoundException;
import com.dentalClinic.dental.repository.PatientRepository;
import com.dentalClinic.dental.service.IPatientService;
import com.dentalClinic.dental.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PatientService implements IPatientService {

    private final Logger LOGGER =
            (Logger) LoggerFactory.getLogger(PatientService.class);

    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository,
                          ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    /*
    registerPatient: This method registers a new patient in the system;
    Parameters: The patient information to register;
    Return value: PatientOutputDto, the registered patient's information;
    Example usage:
    PatientInputDto patientInputDto = new PatientInputDto("Chris Brown", 35,
    "male");
    PatientOutputDto patientOutputDto = patientService.registerPatient
    (patientInputDto);
     */

    @Override
    public PatientOutputDto registerPatient(PatientInputDto patient) {
        LOGGER.info("PatientInputDto: " + JsonPrinter.toString(patient));
        Patient patientEntity = modelMapper.map(patient, Patient.class);

        Patient patientToPersist = patientRepository.save(patientEntity);
        PatientOutputDto patientOutputDto = modelMapper.map(patientToPersist,
                PatientOutputDto.class);
        LOGGER.info("PatientOutputDto :" + JsonPrinter.toString(patientOutputDto));
        return patientOutputDto;
    }


    /*
    listPatients: This method retrieves a list of all patients and returns
    it as a List<PatientOutputDto>;
    Return value: List<PatientOutputDto>: A list of patient output DTOs.
    Example Usage:
    List<PatientOutputDto> patients = patientService.listPatients();
    */
    @Override
    public List<PatientOutputDto> listPatients() {
        List<PatientOutputDto> patientsOutputDto =
                patientRepository.findAll()
                        .stream()
                        .map(patient -> modelMapper.map(patient,
                                PatientOutputDto.class))
                        .toList();

        if (LOGGER.isInfoEnabled())
            LOGGER.info("List of all patients: {}",
                    JsonPrinter.toString(patientsOutputDto));
        return patientsOutputDto;
    }


    /*
    searchPatientForId: This method retrieve a patient's information from
    the database using their ID.
    Return value: A PatientOutputDto object containing the patient's details
    or null if not found.
    Example usage:
    Long patientId = 12345;
    PatientOutputDto patientFound = searchPatientForId(patientId);
    if (patientFound != null) {
    System.out.println(patientFound.getName()); // Access patient information
    } else {
    System.out.println("Patient not found.");
}
 */
    @Override
    public PatientOutputDto searchPatientForId(Long id) {
        Patient searchedPatient = patientRepository.findById(id).orElse(null);
        PatientOutputDto patientFound = null;

        if (searchedPatient != null) {
            patientFound = modelMapper.map(searchedPatient,
                    PatientOutputDto.class);
            LOGGER.info("Patient found: {}",
                    JsonPrinter.toString(patientFound));
        } else LOGGER.error("The id is not registered in the database");
        return patientFound;
    }

    /*
    updatePatient: This method update the patient information in the database
    based on the provided PatientUpdateInputDto object. Check if the patient
    exists in the database using the patient ID provided. If the patient
    exists, update the corresponding patient record with the new information
    and save the updated record back to the database.
    Return value: A PatientOutputDto object containing the updated patient
    information or null if the patient is not found.
    Example Usage:
    PatientUpdateInputDto patientUpdateInputDto = new PatientUpdateInputDto();
    patientUpdateInputDto.setId(12345); // Replace with the actual patient ID
    patientUpdateInputDto.setName("Alice Doe"); // Updated patient name
    patientUpdateInputDto.setEmail("alice.doe@example.com"); // Updated
    patient email

    PatientOutputDto updatedPatient = updatePatient(patientUpdateInputDto);

    if (updatedPatient != null) {
    System.out.println("Patient updated successfully:");
    System.out.println("Name: " + updatedPatient.getName());
    System.out.println("Email: " + updatedPatient.getEmail());
    } else {
    System.out.println("Patient not found.");
    }
    */


    @Override
    public PatientOutputDto updatePatient(PatientUpdateInputDto patient) {
        Patient patientReceived = modelMapper.map(patient, Patient.class);
        Patient patientToUpdate =
                patientRepository.findById(patientReceived.getId()).orElse(null);

        PatientOutputDto patientOutputDto = null;

        if (patientToUpdate != null) {
            patientToUpdate = patientReceived;
            patientRepository.save(patientToUpdate);

            patientOutputDto = modelMapper.map(patientToUpdate,
                    PatientOutputDto.class);
            LOGGER.warn("Patient updated: {}",
                    JsonPrinter.toString(patientOutputDto));
        } else {
            LOGGER.error("The patient update failed because the patient is " +
                    "not in our database");
        /* CHECK LATER: public class PatientNotFoundException extends
            RuntimeException {
            public PatientNotFoundException(Long patientId) {
            super("Patient not found: " + patientId);
            }}

            throw new PatientNotFoundException(patientReceived.getId());
            */


        }
        return patientOutputDto;
    }

    @Override
    public void deletePatient(Long id) {
        if (patientRepository.findById(id).orElse(null) != null) {
            patientRepository.deleteById(id);
            LOGGER.warn("The patient with id: " + id + "has been deleted");
            /*LOGGER.warn("Se ha eliminado el paciente con id: {}", id);*/
        } else {
            LOGGER.error("The patient with the id " + id + " was not found");
//            throw new ResourceNotFoundException("The patient with the id "
//            + id + " was not found")
        }


    }

    @Override
    public PatientOutputDto searchPatientForDni (int dni){
        return modelMapper.map(patientRepository.findByDni(dni),
                PatientOutputDto.class);

    }

    private void configureMapping() {
        modelMapper.typeMap(PatientInputDto.class, Patient.class)
                .addMappings(mapper -> mapper.map(PatientInputDto::getAddressInputDto,
                        Patient::setAddress));
        modelMapper.typeMap(Patient.class, PatientOutputDto.class)
                .addMappings(modelMapper -> modelMapper.map(Patient::getAddress,
                        PatientOutputDto::set));
        modelMapper.typeMap(PatientUpdateInputDto.class, Patient.class)
                .addMappings(mapper -> mapper.map(PatientUpdateInputDto::getDomicilioModificacionEntradaDto, Patient::setAddress));

    }

}
//
//