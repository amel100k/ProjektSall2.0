package Tests;
import application.controller.Controller;
import application.model.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void createDestilleringTest() {
        LocalDate startDato = LocalDate.now();
        LocalDate slutDato = LocalDate.now().plusDays(1);
        Destillering destillering = Controller.createDestillering(startDato, slutDato, 1000, "Byg", 500, 40.0, "Ingen kommentar", "Tørv");
        assertEquals(startDato, destillering.getStartDato());
        assertEquals(slutDato, destillering.getSlutDato());
    }

    @Test
    void createDestilleringUgyldigeDatoer() {
        LocalDate startDato = LocalDate.now();
        LocalDate slutDato = LocalDate.now().minusDays(1);
        assertThrows(IllegalArgumentException.class, () -> Controller.createDestillering(startDato, slutDato, 1000, "Byg", 500, 40.0, "Ingen kommentar", "Tørv"));
    }

    @Test
    void createFadTest() {
        Lager lager = new Lager("Vej",10);
        Fad fad = Controller.createFad("fadHistorie", "tidligereBrug", 1, "koebssted", "fadNavn", 100,lager);
        assertEquals("fadHistorie", fad.getFadHistore());
        assertEquals("tidligereBrug", fad.getTidligereBrug());
    }

    @Test
    void createFadUgyldigKapacitet() {
        Lager lager = new Lager("Vej",10);
        assertThrows(IllegalArgumentException.class, () -> Controller.createFad("fadHistorie", "tidligereBrug", 1, "koebssted", "fadNavn", -100,lager));
    }

    @Test
    void createAftapningTest() {
        Lager lager = new Lager("Vej",10);
        Fad fad = new Fad("fadHistorie", "tidligereBrug", 1, "koebssted", "fadNavn", 100, lager);
        fad.setMængdePåFad(50);
        ArrayList<Destillat> destillat = new ArrayList<>();
        Aftapning aftapning = Controller.createAftapning(fad, destillat, 50, LocalDate.now());
        assertEquals(50, aftapning.getLiter());
    }

    @Test
    void createAftapningUgyldigLiter() {
        Lager lager = new Lager("Vej",10);
        Fad fad = new Fad("fadHistorie", "tidligereBrug", 1, "koebssted", "fadNavn", 100,lager);
        fad.setMængdePåFad(50);
        ArrayList<Destillat> destillat = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> Controller.createAftapning(fad, destillat, 150, LocalDate.now()));
    }
}