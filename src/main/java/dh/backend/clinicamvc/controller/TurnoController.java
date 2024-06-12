package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    public ResponseEntity<TurnoResponseDto> agregarTurno(@RequestBody TurnoRequestDto turno){
        TurnoResponseDto turnoARetornar = turnoService.registrar(turno);
        if (turnoARetornar == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(turnoARetornar);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponseDto> buscarTurnoPorId (@PathVariable Integer id){
        TurnoResponseDto turno = turnoService.buscarPorId(id);
        if (turno != null) {
            return ResponseEntity.ok(turno);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TurnoResponseDto>> buscarTodosTurnos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarTurno(@PathVariable Integer id, @RequestBody TurnoRequestDto turno){
        turnoService.actualizarTurno(id, turno);
        return ResponseEntity.ok("Turno modificado con Exito");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id){
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("¡ Turno eliminado con exito !");
    }
}
