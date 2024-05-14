package Tests;

import application.model.Destillat;
import application.model.Destillering;
import application.model.Mængde;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DestilleringTest {

    @Test
    void addDestillat() {
        Destillering destillering = new Destillering(LocalDate.of(2024,2,2), LocalDate.of(2024,3,2), 1000,"Byg", 800,84.9,"Ingen kommentar", "Tørv");
        Destillat destillat = new Destillat(60.7,new Mængde(102));
        destillering.addDestillat(destillat);
        assertTrue(destillering.getDestillater().contains(destillat));
    }

    @Test
    void addAlleredeEksisterendeDestillat() {
        Destillering destillering = new Destillering(LocalDate.of(2024,4,2), LocalDate.of(2024,5,2), 500,"Byg", 750,87.4,"Ingen kommentar", "Ingen");
        Destillat destillat = new Destillat(60.2,new Mængde(122));
        destillering.addDestillat(destillat);
        int sizeBefore = destillering.getDestillater().size();
        destillering.addDestillat(destillat);
        int sizeAfter = destillering.getDestillater().size();
        assertEquals(sizeBefore, sizeAfter);
    }

    @Test
    void createOgAddDestillat() {
        Destillering destillering = new Destillering(LocalDate.of(2024,3,2), LocalDate.of(2024,4,2), 1000,"Byg", 800,85.6,"Ingen kommentar", "Ingen");
        int sizeBefore = destillering.getDestillater().size();
        Destillat destillat = destillering.createDestillat(40.0, new Mængde(132));
        int sizeAfter = destillering.getDestillater().size();
        assertTrue(destillering.getDestillater().contains(destillat));
        assertEquals(sizeBefore + 1, sizeAfter);
    }
}
