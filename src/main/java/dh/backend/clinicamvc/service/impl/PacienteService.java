package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IPacienteRepository;
import dh.backend.clinicamvc.service.IPacienteService;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    private static Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private IPacienteRepository pacienteRepository;
    // CONSTRUCTOR
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente registrarPaciente(Paciente paciente) throws BadRequestException {
        if (StringUtils.isEmpty(paciente.getNombre()) || StringUtils.isEmpty(paciente.getApellido())
                || StringUtils.isEmpty(paciente.getDni()) || paciente.getFechaIngreso() == null
                || paciente.getDomicilio() == null){
            LOGGER.error("paciente con campos vacios");
            throw new BadRequestException("{\"message\": \"paciente no puede tener campos vacios\"}");
        }
        LOGGER.info("Paciente creado " + paciente);
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarPacientePorId(Integer id) throws BadRequestException {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isPresent()){
            LOGGER.info("Paciente encontrado ");
        return optionalPaciente;
    }else{
            LOGGER.error("paciente no encontrado");
            throw new BadRequestException("{\"message\": \"paciente no encontrado por id\"}");
        }
    }

    @Override
    public List<Paciente> buscarTodosPacientes() {
        LOGGER.info("listando pacientes");
        return pacienteRepository.findAll();
    }

    @Override
    public void actualizarPaciente(Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(paciente.getId());
        if (optionalPaciente.isPresent()) {
            LOGGER.info("Paciente actualizado " + optionalPaciente.get());
            pacienteRepository.save(paciente);
        }else {
            LOGGER.error("paciente no se pudo actualizar");
            throw new ResourceNotFoundException("{\"message\": \"id del paciente no encontrado\" }");
        }
    }

    @Override
    public void eliminarPaciente(Integer id) throws ResourceNotFoundException, BadRequestException {
        Optional<Paciente> optionalPaciente = buscarPacientePorId(id);
        if (optionalPaciente.isPresent()) {
            LOGGER.info("Paciente eliminado " + optionalPaciente.get());
            pacienteRepository.deleteById(id);
        }else{
            LOGGER.error("paciente no se pudo eliminar");
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
        }
    }
}
