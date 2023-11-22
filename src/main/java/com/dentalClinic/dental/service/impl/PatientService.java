package com.dentalClinic.dental.service.impl;

import com.dentalClinic.dental.dto.input.patient.PatientInputDto;
import com.dentalClinic.dental.dto.output.patient.PatientOutputDto;
import com.dentalClinic.dental.dto.update.PatientUpdateInputDto;
import com.dentalClinic.dental.entity.Patient;
import com.dentalClinic.dental.repository.PatientRepository;
import com.dentalClinic.dental.service.IPatientService;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PatientService implements IPatientService {

private final Logger LOGGER = (Logger) LoggerFactory.getLogger(PatientService.class);

private PatientRepository patientRepository;
private ModelMapper modelMapper;

@Autowired
    public PatientService(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public PatientOutputDto registerPatient(PatientInputDto patient) {
        return null;
    }

    @Override
    public List<PatientOutputDto> listPatients() {
        return null;
    }

    @Override
    public PatientOutputDto searchPatientForId(Long id) {
        return null;
    }

    @Override
    public PatientOutputDto updatePatient(PatientUpdateInputDto patient) {
        return null;
    }

    @Override
    public void deletePatient(Long id) {

    }
}
//
//
//    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
//        //convertimos mediante el mapper de dtoEntrada a entidad
//        LOGGER.info("PacienteEntradaDto: " + JsonPrinter.toString(paciente));
//        Paciente pacienteEntidad = modelMapper.map(paciente, Paciente.class);
//
//        //mandamos a persistir a la capa dao y obtenemos una entidad
//        Paciente pacienteAPersistir = pacienteRepository.save(pacienteEntidad);
//        //transformamos la entidad obtenida en salidaDto
//        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteAPersistir, PacienteSalidaDto.class);
//        LOGGER.info("PacienteSalidaDto: " + JsonPrinter.toString(pacienteSalidaDto));
//        return pacienteSalidaDto;
//    }
//
//    public List<PacienteSalidaDto> listarPacientes() {
//        List<PacienteSalidaDto> pacientesSalidaDto = pacienteRepository.findAll()
//                .stream()
//                .map(paciente -> modelMapper.map(paciente, PacienteSalidaDto.class))
//                .toList();
//
//
//        //List<Paciente> pacientes = pacienteIDao.listarTodos();
//        //List<PacienteSalidaDto> pacienteSalidaDtos = new ArrayList<>();
//        //for (Paciente paciente : pacientes){
//        //    PacienteSalidaDto pacienteSalidaDto = modelMapper.map(paciente, PacienteSalidaDto.class);
//        //    pacienteSalidaDtos.add(pacienteSalidaDto);
//        //}
//
//        if (LOGGER.isInfoEnabled())
//            LOGGER.info("Listado de todos los pacientes: {}", JsonPrinter.toString(pacientesSalidaDto));
//        return pacientesSalidaDto;
//    }
//
//    @Override
//    public PacienteSalidaDto buscarPacientePorId(Long id) {
//        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
//        PacienteSalidaDto pacienteEncontrado = null;
//
//        if (pacienteBuscado != null) {
//            pacienteEncontrado = modelMapper.map(pacienteBuscado, PacienteSalidaDto.class);
//            LOGGER.info("Paciente encontrado: {}", JsonPrinter.toString(pacienteEncontrado));
//        } else LOGGER.error("El id no se encuentra registrado en la base de datos");
//
//        return pacienteEncontrado;
//    }
//
//    @Override
//    public PacienteSalidaDto actualizarPaciente(PacienteModificacionEntradaDto paciente) {
//        Paciente pacienteRecibido = modelMapper.map(paciente, Paciente.class);
//        Paciente pacienteAActualizar = pacienteRepository.findById(pacienteRecibido.getId()).orElse(null);
//
//        PacienteSalidaDto pacienteSalidaDto = null;
//
//        if (pacienteAActualizar != null) {
//            pacienteAActualizar = pacienteRecibido;
//            pacienteRepository.save(pacienteAActualizar);
//
//            pacienteSalidaDto = modelMapper.map(pacienteAActualizar, PacienteSalidaDto.class);
//            LOGGER.warn("Paciente actualizado: {}", JsonPrinter.toString(pacienteSalidaDto));
//
//        } else {
//            LOGGER.error("No fue posible actualizar el paciente porque no se encuentra en nuestra base de datos");
//            //lanzar excepcion correspondiente
//        }
//
//
//        return pacienteSalidaDto;
//    }
//
//    @Override
//    public void eliminarPaciente(Long id) {
//        if (pacienteRepository.findById(id).orElse(null) != null) {
//            pacienteRepository.deleteById(id);
//            LOGGER.warn("Se ha eliminado el paciente con id: {}", id);
//        } else {
//            LOGGER.error("No se ha encontrado el paciente con id {}", id);
//            //excepcion a lanzar aqui
//        }
//
//    }
//
//
//    private void configureMapping() {
//        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
//                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));
//        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
//                .addMappings(modelMapper -> modelMapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
//        modelMapper.typeMap(PacienteModificacionEntradaDto.class, Paciente.class)
//                .addMappings(mapper -> mapper.map(PacienteModificacionEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));
//
//    }