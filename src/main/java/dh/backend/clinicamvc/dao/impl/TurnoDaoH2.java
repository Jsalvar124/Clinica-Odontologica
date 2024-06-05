package dh.backend.clinicamvc.dao.impl;

import dh.backend.clinicamvc.dao.IDao;
import dh.backend.clinicamvc.model.Turno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TurnoDaoH2 implements IDao<Turno> {

    private static Logger LOGGER = LoggerFactory.getLogger(TurnoDaoH2.class);
    private List<Turno> turnos;

    public TurnoDaoH2(List<Turno> turnos) {
        this.turnos = turnos;
    }

    @Override
    public Turno registrar(Turno turno) {
        Integer id = turnos.size()+1;
        turno.setId(id);
        turnos.add(turno);
        LOGGER.info("Turno creado con Exito " + turno);
        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        for (Turno turno: turnos){
            if (turno.getId().equals(id)){
                LOGGER.info("Turno Encontrado con Exito: " + turno);
                return turno;
            }
        }
        LOGGER.info("Turno no encontrado");
        return null;
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnos;
    }

    @Override
    public void actulizar(Turno turno) {
        boolean actualizado = false;
        for (Turno t : turnos) {
            if (t.getId().equals(turno.getId())) {
                t.setPaciente(turno.getPaciente());
                t.setOdontologo(turno.getOdontologo());
                t.setFecha(turno.getFecha());
                LOGGER.info("Turno actualizado con éxito: " + turno);
                actualizado = true;
                break;
            }
        }
        if (!actualizado) {
            LOGGER.info("Turno no actualizado: " + turno);
        }
    }

    @Override
    public void eliminar(Integer id) {
        Turno turnoAEliminar = null;
        for(Turno turno: turnos){
            if(turno.getId().equals(id)){
                turnoAEliminar = turno;
                break;
            }
        }
        turnos.remove(turnoAEliminar);
        LOGGER.info("turno eliminado ");
    }
}
