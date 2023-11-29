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

import java.lang.annotation.Documented;
import java.util.List;


@Service
public class PatientService implements IPatientService {

    private final Logger LOGGER =

            (Logger) LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository patientIRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository,
                          ModelMapper modelMapper) {
        this.patientIRepository = patientRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    //POST - Register a new patient
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

        Patient patientToPersist = patientIRepository.save(patientEntity);
        PatientOutputDto patientOutputDto = modelMapper.map(patientToPersist,
                PatientOutputDto.class);
        LOGGER.info("PatientOutputDto :" + JsonPrinter.toString(patientOutputDto));
        return patientOutputDto;
    }

    ;

    //GET -- List patients
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
                patientIRepository.findAll()
                        .stream()
                        .map(patient -> modelMapper.map(patient,
                                PatientOutputDto.class))
                        .toList();

        if (LOGGER.isInfoEnabled())
            LOGGER.info("List of all patients: {}",
                    JsonPrinter.toString(patientsOutputDto));
        return patientsOutputDto;
    }

    ;

    //GET -- Search a patient by id
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
        Patient searchedPatient = patientIRepository.findById(id).orElse(null);
        PatientOutputDto patientFound = null;

        if (searchedPatient != null) {
            patientFound = modelMapper.map(searchedPatient,
                    PatientOutputDto.class);
            LOGGER.info("Patient found: {}",
                    JsonPrinter.toString(patientFound));
        } else LOGGER.error("The id is not registered in the database");
        return patientFound;
    }

    ;

    //PUT -- Update Patient
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
                patientIRepository.findById(patientReceived.getId()).orElse(null);

        PatientOutputDto patientOutputDto = null;

        if (patientToUpdate != null) {
            patientToUpdate = patientReceived;
            patientIRepository.save(patientToUpdate);

            patientOutputDto = modelMapper.map(patientToUpdate,
                    PatientOutputDto.class);
            LOGGER.warn("Patient updated: {}",
                    JsonPrinter.toString(patientOutputDto));
        } else {
            LOGGER.error("The patient update failed because the patient is " +
                    "not in our database");

        }
        return patientOutputDto;
    }

    ;

    //DELETE - Delete a patient by id
    /*
    deletePatient: This method deletes a patient from the database by its ID;
    Parameters id: The ID of the patient to delete;
    Return Value: Void;
    Usage Example:
    PatientService patientService = new PatientService();
    patientService.deletePatient(1L);
    */
    @Override
    public void deletePatient(Long id) throws ResourceNotFoundException {
        if (patientIRepository.findById(id).orElse(null) != null) {
            patientIRepository.deleteById(id);
            LOGGER.warn("The patient was deleted: {}", id);
        } else {
            LOGGER.error("The patient with the id " + id + " was not found");
            throw new ResourceNotFoundException("The patient with the id "
                    + id + " was not found");
        }

    }

    ;

    private void configureMapping() {
        modelMapper.typeMap(PatientInputDto.class, Patient.class)
                .addMappings(mapper -> mapper.map(PatientInputDto::getAddressInputDto,
                        Patient::setAddress));
        modelMapper.typeMap(Patient.class, PatientOutputDto.class)
                .addMappings(modelMapper -> modelMapper.map(Patient::getAddress,
                        PatientOutputDto::setAddressOutputDto));
        modelMapper.typeMap(PatientUpdateInputDto.class, Patient.class)
                .addMappings(mapper -> mapper.map(PatientUpdateInputDto::getAddressUpdateInputDto,
                        Patient::setAddress));

    }

};