package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {

    Paciente registrarPaciente(Paciente paciente) throws BadRequestException;

    Optional<Paciente> buscarPacientePorId(Integer id) throws BadRequestException;

    List<Paciente> buscarTodosPacientes();

    void actualizarPaciente(Paciente paciente) throws ResourceNotFoundException;
    void eliminarPaciente(Integer id) throws ResourceNotFoundException, BadRequestException;

}
