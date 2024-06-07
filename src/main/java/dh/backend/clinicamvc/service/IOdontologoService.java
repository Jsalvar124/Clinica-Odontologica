package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);

    List<Odontologo> listarOdontologos();

    Optional<Odontologo> buscarOdontologoPorId(Integer id);

    void actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Integer id);
}
