package dh.backend.clinicamvc.service;


import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {

    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoServiceTest.class);

    @Autowired
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
    void registerTest() throws BadRequestException {
        Odontologo odontologoEnBD = odontologoService.registrarOdontologo(odontologo);
        LOGGER.info("Odontologo Guardado: "+ odontologoEnBD);
        assertNotNull(odontologoEnBD);
    }
    @Test
    @DisplayName("Testeo mostrar lista de odontologos")
    void test1(){
        List<Odontologo> listaOdontologos = odontologoService.listarOdontologos();
        assertTrue(listaOdontologos.size()!=0);
    }

    @Test
    @DisplayName("Testear busqueda odontologo por id")
    void testOdontologoPorId(){
        Integer id = 15;
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarOdontologoPorId(id);
        if(odontologoEncontrado.isPresent()){
            Odontologo odontologo1 = odontologoEncontrado.get();
            LOGGER.info("Odontologo Encontrado");
            assertEquals(id, odontologo1.getId());
        }


    }

}