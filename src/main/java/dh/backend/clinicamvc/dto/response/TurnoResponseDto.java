package dh.backend.clinicamvc.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TurnoResponseDto {
    private Integer id;
    private OdontologoResponseDto odontologo;
    private PacienteResponseDto paciente;
    private String fecha;
}
