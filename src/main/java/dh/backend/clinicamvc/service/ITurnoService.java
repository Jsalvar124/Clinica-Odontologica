package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.entity.Turno;

import java.util.List;

public interface ITurnoService {

    TurnoResponseDto registrar(TurnoRequestDto turnoRequestDto);

    TurnoResponseDto buscarPorId(Integer id);
    List<TurnoResponseDto> buscarTodos();

    void actualizarTurno(Integer id, TurnoRequestDto turnoRequestDto);

    void eliminarTurno(Integer id);

}
