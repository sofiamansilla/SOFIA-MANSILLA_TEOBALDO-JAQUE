package com.dentalClinic.dental.controller;

import com.dentalClinic.dental.dto.input.patient.PatientInputDto;
import com.dentalClinic.dental.dto.output.dentist.DentistOutputDto;
import com.dentalClinic.dental.dto.output.patient.PatientOutputDto;
import com.dentalClinic.dental.dto.update.PatientUpdateInputDto;
import com.dentalClinic.dental.exceptions.ResourceNotFoundException;
import com.dentalClinic.dental.service.IPatientService;
import com.dentalClinic.dental.service.impl.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private final IPatientService patientService;


    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }


    //POST - Register a new patient
    @Operation(summary = "New patient registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The patient was" +
                    " registered successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation =
                                    PatientOutputDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server" +
                    " error",
                    content = @Content)
    })
    @PostMapping("/register")

    public ResponseEntity<PatientOutputDto> registerPatient(@Valid @RequestBody PatientInputDto patient) {
        return new ResponseEntity<>(patientService.registerPatient(patient),
                HttpStatus.CREATED);
    }


    //GET -- Search a patient by ID

    @Operation(summary = "Search for a patient by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient found " +
                    "succesfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation =
                                    PatientOutputDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Patient not " +
                    "found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal " +
                    "server error",
                    content = @Content)
    })
    @GetMapping("/search/{id}")
    public ResponseEntity<PatientOutputDto> getPatientByID(@PathVariable Long id) {
        return new ResponseEntity<>(patientService.searchPatientForId(id),
                HttpStatus.OK);

    }

//GET -- List patients


    @Operation(summary = "List of all patients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of " +
                    "patients successfully obtained",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation =
                                    PatientOutputDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal " +
                    "server error",
                    content = @Content)
    })
    @GetMapping("/list")
    public ResponseEntity<List<PatientOutputDto>> listPatients() {
        return new ResponseEntity<>(patientService.listPatients(), OK);
    }


    //PUT - Update Patients


    @Operation(summary = "Patient Update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient " +
                    "successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation =
                                    PatientOutputDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Patient not " +
                    "found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server" +
                    " error",
                    content = @Content)
    })
    @PutMapping("/update")
    public ResponseEntity<PatientOutputDto> updatePatient(@Valid @RequestBody PatientUpdateInputDto patient) throws ResourceNotFoundException {
        return new ResponseEntity<>(patientService.updatePatient(patient),
                HttpStatus.OK);
    }


    //DELETE - Delete patient

    @Operation(summary = "Delete a patient by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Patient " +
                    "successfully deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "The ID is not " +
                    "valid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Patient " +
                    "not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server" +
                    " error",
                    content = @Content)
    })
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) throws ResourceNotFoundException {
        patientService.deletePatient(id);
        return new ResponseEntity<>("Patient successfully deleted",
                HttpStatus.NO_CONTENT);
    }

}
