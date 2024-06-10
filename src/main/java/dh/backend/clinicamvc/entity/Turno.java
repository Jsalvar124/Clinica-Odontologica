package dh.backend.clinicamvc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne // Muchos turnos pueden corresponderle a un paciente.
    @JsonIgnore
    Paciente paciente;

    @ManyToOne //Muchos turnos pueden corresponderle a un odontólogo.
    @JsonIgnore
    Odontologo odontologo;
    LocalDate fecha;
}
