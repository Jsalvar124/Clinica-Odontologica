package dh.backend.clinicamvc.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Odontologo {
    private Integer id;
    private String nombre;
    private String apellido;
    private String matricula;
}
