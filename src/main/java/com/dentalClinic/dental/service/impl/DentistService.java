package com.dentalClinic.dental.service.impl;

import com.dentalClinic.dental.dto.input.dentist.DentistInputDto;
import com.dentalClinic.dental.dto.input.patient.PatientInputDto;
import com.dentalClinic.dental.dto.output.dentist.DentistOutputDto;
import com.dentalClinic.dental.dto.output.patient.PatientOutputDto;
import com.dentalClinic.dental.dto.update.DentistUpdateInputDto;
import com.dentalClinic.dental.dto.update.PatientUpdateInputDto;
import com.dentalClinic.dental.entity.Dentist;
import com.dentalClinic.dental.entity.Patient;
import com.dentalClinic.dental.repository.DentistRepository;
import com.dentalClinic.dental.service.IDentistService;
import com.dentalClinic.dental.utils.JsonPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistService implements IDentistService {

    private final Logger LOGGER = LoggerFactory.getLogger(DentistService.class);
    private DentistRepository dentistIRepository;
    private ModelMapper modelMapper;

    public DentistService(DentistRepository dentistIRepository,
                          ModelMapper modelMapper) {
        this.dentistIRepository = dentistIRepository;
        this.modelMapper = modelMapper;
    }

    /*
        registerDentist: This method registers a new dentist in the system;
        Parameters: The dentist information to register;
        Return value: DentistOutputDto, the registered dentist's information;
        Example usage:
        DentistInputDto dentistInputDto = new DentistInputDto("Sarah McKay",
        43, "female");
        DentistOutputDto dentistOutputDto = dentistService.registerDentist
        (dentistInputDto);
         */
    @Override
    public DentistOutputDto registerDentist(DentistInputDto dentist) {
        LOGGER.info("DentistInputDto: " + JsonPrinter.toString(dentist));
        Dentist dentistEntity = modelMapper.map(dentist, Dentist.class);

        Dentist dentistToPersist = dentistIRepository.save(dentistEntity);
        DentistOutputDto dentistOutputDto = modelMapper.map(dentistToPersist,
                DentistOutputDto.class);
        LOGGER.info("DentistOutputDto : " + JsonPrinter.toString(dentistOutputDto));
        return dentistOutputDto;

    }

    /*
    listDentists: This method retrieves a list of all dentists and returns
    it as a List<DentistOutputDto>;
    Return value: List<DentistOutputDto>: A list of dentist output DTOs.
    Example Usage:
    List<DentistOutputDto> dentists = dentistService.listDentists();
    */
    @Override
    public List<DentistOutputDto> listDentists() {
        List<DentistOutputDto> dentistsOutputDto =
                dentistIRepository.findAll()
                        .stream()
                        .map(dentist -> modelMapper.map(dentist,
                                DentistOutputDto.class))
                        .toList();

        if (LOGGER.isInfoEnabled())
            LOGGER.info("List of all dentist: {}",
                    JsonPrinter.toString(dentistsOutputDto));
        return dentistsOutputDto;
    }

    /*
    searchDentistForId: This method retrieve a dentist's information from
    the database using their ID.
    Return value: A DentistOutputDto object containing the dentist's details
    or null if not found.
    Example usage:
    Long dentistId = 00125;
    DentistOutputDto dentistFound = searchDentistForId(dentistId);
    if (dentistFound != null) {
    System.out.println(dentistFound.getName()); // Access dentist information
    } else {
    System.out.println("Dentist not found.");
}*/
    @Override
    public DentistOutputDto searchDentistForId(Long id) {
        Dentist searchedDentist = dentistIRepository.findById(id).orElse(null);
        DentistOutputDto dentistFound = null;

        if (searchedDentist != null) {
            dentistFound = modelMapper.map(searchedDentist,
                    DentistOutputDto.class);
            LOGGER.info("Dentist found: {}",
                    JsonPrinter.toString(dentistFound));
        } else LOGGER.error("The id is not registered in the database");
        return dentistFound;
    }

    /*
    updateDentist: This method updates an existing dentist record in the
    database;
    Parameters: dentist: An instance of DentistUpdateInputDto containing the
    updated dentist information;
    Return Value: An instance of DentistOutputDto containing the updated
    dentist information, or null if the dentist was not found or the update
    failed;
    Usage Example:
    DentistUpdateInputDto dentistUpdateInputDto = new DentistUpdateInputDto();
    dentistUpdateInputDto.setId(12345L);
    dentistUpdateInputDto.setName("John Doe");
    dentistUpdateInputDto.setEmail("johndoe@example.com");

    DentistOutputDto dentistOutputDto = dentistService.updateDentist
    (dentistUpdateInputDto);

    if (dentistOutputDto != null) {
    System.out.println("Dentist updated successfully");
    } else {
    System.out.println("Dentist update failed");
}
*/
    @Override
    public DentistOutputDto updateDentist(DentistUpdateInputDto dentist) {
        Dentist dentistReceived = modelMapper.map(dentist,
                Dentist.class);
        Dentist dentistToUpdate =
                dentistIRepository.findById(dentistReceived.getId()).orElse(null);

        DentistOutputDto dentistOutputDto = null;

        if (dentistToUpdate != null) {
            dentistToUpdate = dentistReceived;
            dentistIRepository.save(dentistToUpdate);

            dentistOutputDto = modelMapper.map(dentistToUpdate,
                    DentistOutputDto.class);
            LOGGER.warn("Dentist updated: {}",
                    JsonPrinter.toString(dentistOutputDto));
        } else {
            LOGGER.error("The dentist update failed because the dentist is " +
                    "not in our database");

        }
        return dentistOutputDto;
    }

    /*
    deleteDentist: This method deletes an existing dentist record from the
    database;
    Parameters: id: The ID of the dentist record to delete;
    Return Value: void;
    Usage Example:
    Long id = 12345L;
    dentistService.deleteDentist(id);
    */
    @Override
    public void deleteDentist(Long id) {
        if (dentistIRepository.findById(id).orElse(null) != null) {
            dentistIRepository.deleteById(id);
            LOGGER.warn("The dentist with id: " + id + "has been deleted");
            /*LOGGER.warn("Se ha eliminado el dentista con id: {}", id);*/
        } else {
            LOGGER.error("The dentist with the id " + id + " was not found");
//            throw new ResourceNotFoundException("The dentist with the id "
//            + id + " was not found")
        }

    }

    @Override
    public DentistOutputDto searchDentistForLicenceNumber(String licenceNumber) {
        return modelMapper.map(dentistIRepository.findByLicenceNumber(licenceNumber),
                DentistOutputDto.class);
    }


}
