package Tests;
import application.model.Fad;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FadTest {

    @Test
    void tilføjVedPladsTilgængelig() {
        Fad fad = new Fad("Benns kælder", "Sherry", 1, "Spanien", "Ola", 100);
        fad.fyldPåFad(50);
        assertEquals(50, fad.getMængdePåFad());
    }

    @Test
    void påfyldningAfAlleredeFyldtFad() {
        Fad fad = new Fad("Bertils kælder", "Bourbon", 1, "Danmark", "B0", 100);
        fad.fyldPåFad(101);
        assertEquals(0, fad.getMængdePåFad());
    }

    @Test
    void påfyldningTopGrænseværdiAfFad() {
        Fad fad = new Fad("Emils kælder", "Pisang ambon", 1, "Skotland", "Jul", 100);
        fad.fyldPåFad(100);
        assertEquals(100, fad.getMængdePåFad());
    }

    @Test
    void tilføjningAfNul() {
        Fad fad = new Fad("Lucas' kælder", "Ubrugt", 1, "USA", "Thy", 100);
        fad.fyldPåFad(0);
        assertEquals(0, fad.getMængdePåFad());
    }
}