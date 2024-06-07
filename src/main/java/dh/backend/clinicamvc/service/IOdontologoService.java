package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);

    List<Odontologo> listarOdontologos();

    Odontologo buscarPorId(Integer id);
}
