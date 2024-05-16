package Tests;
import application.model.Fad;
import application.model.Lager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FadTest {

    @Test
    void tilføjVedPladsTilgængelig() {
        Lager lager = new Lager("Vej",10);
        Fad fad = new Fad("Benns kælder", "Sherry", 1, "Spanien", "Ola", 100, lager);
        fad.setMængdePåFad(50);
        fad.fyldPåFad(25);
        assertEquals(75, fad.getMængdePåFad());
    }

    @Test
    void påfyldningAfAlleredeFyldtFad() {
        Lager lager = new Lager("Vej",10);
        Fad fad = new Fad("Bertils kælder", "Bourbon", 1, "Danmark", "B0", 100, lager);
        fad.setMængdePåFad(50);
        assertThrows(IllegalArgumentException.class, () -> fad.fyldPåFad(51));
    }

    @Test
    void påfyldningTopGrænseværdiAfFad() {
        Lager lager = new Lager("Vej",10);
        Fad fad = new Fad("Emils kælder", "Pisang ambon", 1, "Skotland", "Jul", 100, lager);
        fad.setMængdePåFad(50);
        fad.fyldPåFad(50);
        assertEquals(100, fad.getMængdePåFad());
    }

    @Test
    void tilføjningAfNul() {
        Lager lager = new Lager("Vej",10);
        Fad fad = new Fad("Lucas' kælder", "Ubrugt", 1, "USA", "Thy", 100, lager);
        fad.fyldPåFad(0);
        assertEquals(0, fad.getMængdePåFad());
    }
}