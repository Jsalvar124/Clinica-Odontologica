package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.model.Paciente;
import dh.backend.clinicamvc.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    public IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente (@RequestBody Paciente paciente){
        Paciente pacienteARetornar = pacienteService.registrarPaciente(paciente);
        if (pacienteARetornar == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteARetornar);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId (@PathVariable Integer id){
        Paciente paciente = pacienteService.buscarPorId(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        pacienteService.actualizarPaciente(paciente);
        return ResponseEntity.ok("¡ paciente actualizado con exito !");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id){
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("¡ Paciente eliminado con exito !");
    }

}
