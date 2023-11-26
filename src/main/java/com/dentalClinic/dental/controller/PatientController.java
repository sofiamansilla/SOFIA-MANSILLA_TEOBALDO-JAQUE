package com.dentalClinic.dental.controller;

import com.dentalClinic.dental.service.IPatientService;

public class PatientController {

    private IPatientService patientService;


    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }


    //POST
    @PostMapping("/register")
    public ResponseEntity<PacienteSalidaDto> registrarPaciente(@RequestBody @Valid PacienteEntradaDto paciente) {
        return new ResponseEntity<>(pacienteService.registrarPaciente(paciente), HttpStatus.CREATED);
    }


    //GET
    @GetMapping("{id}")
    public ResponseEntity<PacienteSalidaDto> obtenerPacientePorId(@PathVariable Long id) {
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PacienteSalidaDto>> listarPacientes() {
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }

    //PUT
    @PutMapping("/update")
    public PacienteSalidaDto actualizarPaciente(@RequestBody PacienteModificacionEntradaDto paciente) {
        return pacienteService.actualizarPaciente(paciente);
    }

    //DELETE
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.OK);
    }























}
