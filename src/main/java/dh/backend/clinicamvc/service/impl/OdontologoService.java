package dh.backend.clinicamvc.service.impl;


import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.repository.IOdontologoRepository;
import dh.backend.clinicamvc.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public List<Odontologo> listarOdontologos(){
        return odontologoRepository.findAll();
    }

    @Override
    public void actualizarOdontologo(Odontologo odontologo) { odontologoRepository.save(odontologo);}
    @Override
    public void eliminarOdontologo(Integer id) { odontologoRepository.deleteById(id);}
    @Override
    public Optional<Odontologo> buscarOdontologoPorId(Integer id) {
        return odontologoRepository.findById(id);
    }
}
