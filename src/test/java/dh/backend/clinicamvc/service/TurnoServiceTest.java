package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TurnoServiceTest {

    private static Logger LOGGER = LoggerFactory.getLogger(TurnoServiceTest.class);

    @Autowired
    private TurnoService turnoService;
    private TurnoRequestDto turnoRequestDto;

    @BeforeEach
    void setUp(){
        turnoRequestDto = new TurnoRequestDto();
        turnoRequestDto.setPaciente_id(1);
        turnoRequestDto.setOdontologo_id(3);
        turnoRequestDto.setFecha("2024-05-11");
    }

    @Test
    @DisplayName("Testear que el turno fue creado con DTO")
    void registerTest() throws BadRequestException {
        TurnoResponseDto turnoResponseDto = turnoService.registrar(turnoRequestDto);
        LOGGER.info("turno creado con response dto" + turnoResponseDto);
        assertNotNull(turnoResponseDto);
    }
}
