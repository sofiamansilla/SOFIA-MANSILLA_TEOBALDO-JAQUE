package com.dentalClinic.dental.controller;

import com.dentalClinic.dental.dto.output.appointment.AppointmentOutputDto;
import com.dentalClinic.dental.dto.update.AppointmentUpdateInputDto;
import com.dentalClinic.dental.exceptions.ResourceNotFoundException;
import com.dentalClinic.dental.service.IAppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private IAppointmentService appointmentService;

    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;

    }

        //POST - Register a new appointment
        @Operation(summary = "New appointment registration")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "The " +
                        "appointment was registered succesfully",
                        content = {@Content(mediaType = "application/json",
                                schema =
                                @Schema(implementation = AppointmentOutputDto.class))}),
                @ApiResponse(responseCode = "400", description = "Bad Request",
                        content = @Content),
                @ApiResponse(responseCode = "500", description =
                        "Internal server error",
                        content = @Content)
        })
        @PostMapping("/register")
        public ResponseEntity<AppointmentOutputDto> registerAppointment(@RequestBody @Valid AppointmentOutputDto appointment) {
            return new ResponseEntity<>(appointmentService.registrarTurno(appointment),
                    HttpStatus.CREATED);
        }

        //GET -- Search a patient by ID
        @Operation(summary = "Search for an appointment by ID")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Appointment" +
                        " found succesfully",
                        content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = AppointmentOutputDto.class))}),
                @ApiResponse(responseCode = "400", description = "Bad Request",
                        content = @Content),
                @ApiResponse(responseCode = "404", description = "Appointment" +
                        " not found",
                        content = @Content),
                @ApiResponse(responseCode = "500", description =
                        "Internal server error",
                        content = @Content)
        })
        @GetMapping("/id/{id}")
        public ResponseEntity<TurnoSalidaDto> getAppointmentById(@PathVariable Long id) {
            return new ResponseEntity<>(appointmentService.searchAppointmentForId(id),
                    HttpStatus.OK);

        }

    //GET -- List appointments

        @Operation(summary = "List of all appointments")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "List of all" +
                        " appointments successfully obtained",
                        content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = AppointmentOutputDto.class))}),
                @ApiResponse(responseCode = "400", description = "Bad Request",
                        content = @Content),
                @ApiResponse(responseCode = "500", description =
                        "Internal server error",
                        content = @Content)
        })
        @GetMapping("/list")
        public ResponseEntity<List<AppointmentOutputDto>> listAppointments() {
            return new ResponseEntity<>(appointmentService.listAppointments()
                    , OK);
        }

        //PUT - Update Appointment
        @Operation(summary = "Appointment update")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Appointment" +
                        " successfully updated",
                        content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = AppointmentOutputDto.class))}),
                @ApiResponse(responseCode = "400", description = "Bad Request",
                        content = @Content),
                @ApiResponse(responseCode = "404", description = "Appointment" +
                        " not found",
                        content = @Content),
                @ApiResponse(responseCode = "500", description =
                        "Internal server error",
                        content = @Content)
        })
        @PutMapping("/update")
        public ResponseEntity<AppointmentOutputDto> actualizarTurno(@RequestBody AppointmentUpdateInputDto appointment) throws ResourceNotFoundException {
            return new ResponseEntity<>(appointmentService.updateAppointment(appointment),
                    HttpStatus.OK);
        }


    //DELETE - Delete appointment
        @Operation(summary = "Delete an appointment by ID")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "204", description =
                        "Appointment successfully deleted",
                        content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = String.class))}),
                @ApiResponse(responseCode = "400", description = "The ID is " +
                        "not valid",
                        content = @Content),
                @ApiResponse(responseCode = "404", description = "Appointment" +
                        " not found",
                        content = @Content),
                @ApiResponse(responseCode = "500", description =
                        "Internal server error",
                        content = @Content)
        })
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteAppointment(@PathVariable Long id) throws ResourceNotFoundException{
            appointmentService.deleteAppointment(id);
            return new ResponseEntity<>("Appointment successfully deleted",
                    HttpStatus.NO_CONTENT);
        }

    }
