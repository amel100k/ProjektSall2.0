package guifx;

import application.model.*;
import javafx.application.Application;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        initStorage();
        Application.launch(Gui.class);

    }
    private static void initStorage(){
        Destillering destillering = new Destillering(LocalDate.of(2024,6,20),LocalDate.of(2024,7,20),
                500,"Byg",750,67.9,"Ingen kommentar","Tørv");
        Storage.addDestillering(destillering);
        destillering.createDestillat(50,new Mængde(750));

        Lager lager1 = new Lager("Hjemme", 10);
        Lager lager2 = new Lager("Ude", 20);
        Storage.addLager(lager1);
        Storage.addLager(lager2);

        Fad fad = new Fad("Fra 2. Verdenskrig","Tidligere fyldt med Jægermeister",39,"Købt i Esbjerg","Jæger101",130, lager1);
        Fad fad2 = new Fad("Poggers", "ingen",10,"Søborg","sejsej",70, lager2);
        Storage.addFad(fad);
        Storage.addFad(fad2);
        lager2.addFad(fad);

        Aftapning aftapning1 = new Aftapning(fad,Storage.getDestillater(),50,LocalDate.now());
        Aftapning aftapning2 = new Aftapning(fad2,Storage.getDestillater(),50,LocalDate.now());
        Storage.addAftapning(aftapning1);
        Storage.addAftapning(aftapning2);

        Produkt produkt1 = new Produkt(new ArrayList<Aftapning>(List.of(aftapning1)),40, 10);
        Storage.addProdukt(produkt1);

        Lager lager1 = new Lager("Hjemme", 10);
        Lager lager2 = new Lager("Ude", 20);
        lager2.addFad(fad);
        Storage.addLager(lager1);
        Storage.addLager(lager2);

    }

}
