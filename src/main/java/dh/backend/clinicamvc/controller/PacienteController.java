package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.service.impl.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private PacienteService pacienteService;
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente (@RequestBody Paciente paciente){
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.registrarPaciente(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId (@PathVariable Integer id){
        Optional<Paciente> paciente = pacienteService.buscarPacientePorId(id);
        if (paciente.isPresent()) {
            Paciente pacienteARetornar = paciente.get();
            return ResponseEntity.ok(pacienteARetornar);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodosPacientes());
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        Optional<Paciente> optionalPaciente = pacienteService.buscarPacientePorId(paciente.getId());
        if (optionalPaciente.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("{\"message\": \"paciente actualizado\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"paciente no encontrado\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id){
        Optional<Paciente> paciente = pacienteService.buscarPacientePorId(id);
        if (paciente.isPresent()) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("{\"message\": \"paciente eliminado\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
