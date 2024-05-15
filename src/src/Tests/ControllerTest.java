package Tests;
import application.controller.Controller;
import application.model.Aftapning;
import application.model.Destillat;
import application.model.Destillering;
import application.model.Fad;
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
        Fad fad = Controller.createFad("fadHistorie", "tidligereBrug", 1, "koebssted", "fadNavn", 100);
        assertEquals("fadHistorie", fad.getFadHistore());
        assertEquals("tidligereBrug", fad.getTidligereBrug());
    }

    @Test
    void createFadUgyldigKapacitet() {
        assertThrows(IllegalArgumentException.class, () -> Controller.createFad("fadHistorie", "tidligereBrug", 1, "koebssted", "fadNavn", -100));
    }

    @Test
    void createAftapningTest() {
        Fad fad = new Fad("fadHistorie", "tidligereBrug", 1, "koebssted", "fadNavn", 100);
        fad.setMængdePåFad(50);
        ArrayList<Destillat> destillat = new ArrayList<>();
        Aftapning aftapning = Controller.createAftapning(fad, destillat, 50, LocalDate.now());
        assertEquals(50, aftapning.getLiter());
    }

    @Test
    void createAftapningUgyldigLiter() {
        Fad fad = new Fad("fadHistorie", "tidligereBrug", 1, "koebssted", "fadNavn", 100);
        fad.setMængdePåFad(50);
        ArrayList<Destillat> destillat = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> Controller.createAftapning(fad, destillat, 150, LocalDate.now()));
    }
}