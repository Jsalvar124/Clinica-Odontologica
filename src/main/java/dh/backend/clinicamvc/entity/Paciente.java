package dh.backend.clinicamvc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String apellido;
    private String nombre;
    private String dni;
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.ALL) // Permite que se cree el domicilio automáticamente cuando se crea el paciente.
    @JoinColumn(name="domicilio_id") //Nombre de la foreign key
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL) // Hace alusión al atributo en Turno, Si se borra un paciente, se borran los turnos relacionados.
    private Set<Turno> turnoSet = new HashSet<>(); // El set es más rápido y evita duplicados, un pacietne puede tener varios turnos
}
