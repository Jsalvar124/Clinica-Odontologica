package dh.backend.clinicamvc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "odontologos")
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String matricula;

    @OneToMany(mappedBy = "odontologo",cascade = CascadeType.ALL) // Hace alusión al atributo en Turno, Si se borra un odontólogo, se borran los turnos relacionados.
    @JsonIgnore
    private Set<Turno> turnoSet = new HashSet<>();
}
