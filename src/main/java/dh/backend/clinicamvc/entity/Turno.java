package dh.backend.clinicamvc.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Turno {
    Integer id;
    Paciente paciente;
    Odontologo odontologo;
    LocalDate fecha;
}
