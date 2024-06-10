package dh.backend.clinicamvc.service;


import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class OdontologoServiceTest {

    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoServiceTest.class);
    private OdontologoService odontologoService;

    private Odontologo odontologo;

    @BeforeEach
    void setUp(){
        odontologo = new Odontologo();
        odontologo.setNombre("Menganito");
        odontologo.setApellido("Cosme");
        odontologo.setMatricula("143");
    }

    @Test
    @DisplayName("Testear que el odontólogo fue guardado")
    void registerTest(){
        Odontologo odontologoEnBD = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontologoEnBD);
    }
    @Test
    @DisplayName("Testeo mostrar lista de odontologos")
    void test1(){
        List<Odontologo> listaOdontologos = odontologoService.listarOdontologos();

        assertEquals(1, listaOdontologos.size());
    }

    @Test
    @DisplayName("Testear busqueda odontologo por id")
    void testOdontologoPorId(){
        Integer id = 1;
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarOdontologoPorId(id);

        assertEquals(id, odontologoEncontrado.get());
    }

}