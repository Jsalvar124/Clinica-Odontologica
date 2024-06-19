package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.dto.response.OdontologoResponseDto;
import dh.backend.clinicamvc.dto.response.PacienteResponseDto;
import dh.backend.clinicamvc.dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.entity.Turno;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IOdontologoRepository;
import dh.backend.clinicamvc.repository.IPacienteRepository;
import dh.backend.clinicamvc.repository.ITurnoRepository;
import dh.backend.clinicamvc.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService  implements ITurnoService {

    private static Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private IOdontologoRepository odontologoRepository;
    private IPacienteRepository pacienteRepository;
    private ITurnoRepository turnoRepository;
    private ModelMapper modelMapper;

    // CONSTRUCTOR
    public TurnoService(IOdontologoRepository odontologoRepository, IPacienteRepository pacienteRepository, ITurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnoResponseDto registrar(TurnoRequestDto turnoRequestDto) throws BadRequestException {

        Optional<Paciente> paciente = pacienteRepository.findById(turnoRequestDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turnoRequestDto.getOdontologo_id());

        Turno turnoARegistrar = new Turno();
        Turno turnoGuardado = null;
        TurnoResponseDto turnoADevolver = null;

        if(paciente.isPresent() && odontologo.isPresent()) {

            turnoARegistrar.setOdontologo(odontologo.get());
            turnoARegistrar.setPaciente(paciente.get());
            turnoARegistrar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));

            turnoGuardado = turnoRepository.save(turnoARegistrar);
            turnoADevolver = mapToResponseDto(turnoGuardado);
        }else{
            LOGGER.error("turno no se pudo crear");
            throw new BadRequestException("{\"message\": \"no se pudo crear el turno\"}");
        }
        LOGGER.info("Turno creado " + turnoADevolver);
        return turnoADevolver;
    }

    @Override
    public TurnoResponseDto buscarPorId(Integer id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if (turnoOptional.isPresent()){
            Turno turnoEncontrado = turnoOptional.get();
            TurnoResponseDto turnoADevolver = mapToResponseDto(turnoEncontrado);
            LOGGER.info("Turno encontrado " + turnoADevolver);
            return turnoADevolver;
        }
        LOGGER.error("Turno no se pudo encontrar");
        return null;
    }

    @Override
    public List<TurnoResponseDto> buscarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoResponseDto> turnosADevolver = new ArrayList<>();
        TurnoResponseDto turnoAuxiliar = null;
        for (Turno turno: turnos){
            turnoAuxiliar = mapToResponseDto(turno);
            turnosADevolver.add(turnoAuxiliar);
        }
        LOGGER.info("listando turnos");
        return turnosADevolver;
    }

    @Override
    public void actualizarTurno(Integer id, TurnoRequestDto turnoRequestDto) {
        Optional<Paciente> paciente = pacienteRepository.findById(turnoRequestDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turnoRequestDto.getOdontologo_id());
        Optional<Turno> turno = turnoRepository.findById(id);

        Turno turnoAModificar = new Turno();
        if(paciente.isPresent() && odontologo.isPresent()){
            turnoAModificar.setId(id);
            turnoAModificar.setOdontologo(odontologo.get());
            turnoAModificar.setPaciente(paciente.get());
            turnoAModificar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            LOGGER.info("Turno actualizado");
            turnoRepository.save(turnoAModificar);
        }
    }

    @Override
    public void eliminarTurno(Integer id) throws ResourceNotFoundException {
        Optional<Turno> optionalTurno = turnoRepository.findById(id);
        if (optionalTurno.isPresent()){
            LOGGER.info("Turno actualizado " + optionalTurno.get());
            turnoRepository.deleteById(id);
        }else {
            LOGGER.error("paciente no se pudo turno");
            throw new ResourceNotFoundException("{\"message\": \"No se encontro id del turno\"}");
        }
    }

    @Override
    public List<TurnoResponseDto> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate) {
        List<Turno> listadoTurnos = turnoRepository.buscarTurnoEntreFechas(startDate, endDate);
        List<TurnoResponseDto> listadoARetornar = new ArrayList<>();
        TurnoResponseDto turnoAuxiliar = null;
        for (Turno turno: listadoTurnos){
            turnoAuxiliar = mapToResponseDto(turno);
            listadoARetornar.add(turnoAuxiliar);
        }
        LOGGER.info("turno encontrado");
        return listadoARetornar;
    }

    //metodo toResponseDto
    private TurnoResponseDto mapToResponseDto(Turno turno) {
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setOdontologo(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
        turnoResponseDto.setPaciente(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        return turnoResponseDto;
    }
}
