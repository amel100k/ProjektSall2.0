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

        destillering.createDestillat(50,new Mængde(500));
        destillering.createDestillat(68,new Mængde(100));

        Lager lager1 = new Lager("Hjemme", 10);
        Lager lager2 = new Lager("Ude", 20);
        Storage.addLager(lager1);
        Storage.addLager(lager2);

        Fad fad = new Fad("Fra 2. Verdenskrig","Tidligere fyldt med Jægermeister",39,"Købt i Esbjerg","Jæger101",130, lager1);
        Fad fad2 = new Fad("Poggers", "ingen",10,"Søborg","Vodka101",70, lager2);
        Fad fad3 = new Fad("Importeret fra Viby","Tidligere brugt som fad til cherry-vin",20,"Madrid","Cherry101",200,lager2);
        Storage.addFad(fad);
        Storage.addFad(fad2);
        Storage.addFad(fad3);
        lager1.addFad(fad);
        lager2.addFad(fad2);
        lager2.addFad(fad3);

        Aftapning aftapning1 = new Aftapning(fad,Storage.getDestillater(),50,LocalDate.of(2012,2,2));
        Aftapning aftapning2 = new Aftapning(fad2,Storage.getDestillater(),50,LocalDate.now());
        Storage.addAftapning(aftapning1);
        Storage.addAftapning(aftapning2);

        Produkt produkt1 = new Produkt(aftapning1,40, 10);
        Storage.addProdukt(produkt1);

    }

}
