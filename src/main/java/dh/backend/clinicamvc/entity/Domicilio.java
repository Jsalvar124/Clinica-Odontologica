package dh.backend.clinicamvc.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Domicilio {
    private Integer id;
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;
}
