package com.dentalClinic.dental.controller;

import com.dentalClinic.dental.dto.input.dentist.DentistInputDto;
import com.dentalClinic.dental.dto.output.dentist.DentistOutputDto;
import com.dentalClinic.dental.dto.update.DentistUpdateInputDto;
import com.dentalClinic.dental.exceptions.ResourceNotFoundException;
import com.dentalClinic.dental.service.IDentistService;
import com.dentalClinic.dental.service.impl.DentistService;
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
@RequestMapping("/dentists")
@CrossOrigin(origins = "*")
public class DentistController {

    private final IDentistService dentistService;

    public DentistController(IDentistService dentistService) {
        this.dentistService = dentistService;
    }


    //POST - Register a new dentist
    @Operation(summary = "New dentist registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The dentist was" +
                    " registered successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation =
                                    DentistOutputDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server" +
                    " error",
                    content = @Content)
    })
    @PostMapping("/register")

    public ResponseEntity<DentistOutputDto> registerDentist(@Valid @RequestBody DentistInputDto dentist) {
        return new ResponseEntity<>(dentistService.registerDentist(dentist),
                HttpStatus.CREATED);
    }


    //GET - Search a dentist by ID
    @Operation(summary = "Search for a dentist by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dentist " +
                    "found successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation =
                                    DentistOutputDto.class))}),
            @ApiResponse(responseCode = "400", description = "The ID is not " +
                    "valid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Dentist " +
                    "not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server" +
                    " error",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<DentistOutputDto> getDentistById(@PathVariable Long id) {
        return new ResponseEntity<>(dentistService.searchDentistForId(id),
                HttpStatus.OK);
    }


    //GET - List dentists

    @Operation(summary = "List of all dentists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of " +
                    "dentists successfully obtained",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation =
                                    DentistOutputDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server" +
                    " error",
                    content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<DentistOutputDto>> listDentists() {
        return new ResponseEntity<>(dentistService.listDentists(),
                HttpStatus.OK);
    }

    //PUT - Update Dentists
    @Operation(summary = "Dentist update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dentist " +
                    "successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation =
                                    DentistOutputDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Dentist not " +
                    "found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server" +
                    " error",
                    content = @Content)
    })
    @PutMapping("/")
    public ResponseEntity<DentistOutputDto> updateDentist(@Valid @RequestBody DentistUpdateInputDto dentist) throws ResourceNotFoundException {
        return new ResponseEntity<>(dentistService.updateDentist(dentist),
                HttpStatus.OK);
    }


    //DELETE - Delete Dentist
    @Operation(summary = "Delete a dentist by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Dentist " +
                    "successfully deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "The ID is not " +
                    "valid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Dentist " +
                    "not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server" +
                    " error",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDentist(@PathVariable Long id) throws ResourceNotFoundException {
        dentistService.deleteDentist(id);
        return new ResponseEntity<>("Dentist \" +\n" +
                "                    \"successfully deleted",
                HttpStatus.NO_CONTENT);
    }
}