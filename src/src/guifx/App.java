package guifx;

import application.model.*;
import javafx.application.Application;
import storage.Storage;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        initStorage();
        Application.launch(Gui.class);

    }
    private static void initStorage(){
        Destillering destillering = new Destillering(LocalDate.of(2024,6,20),LocalDate.of(2024,7,20),
                500,"Byg",750,67.9,"Ingen kommentar","Tørv");
        Storage.addDestillering(destillering);
        destillering.createDestillat(66.3,new Mængde(750));
        Fad fad = new Fad("Fra 2. Verdenskrig","Tidligere fyldt med Jægermeister",39,"Købt i Esbjerg","Jæger101",130);
        Fad fad2 = new Fad("Poggers", "ingen",10,"Søborg","sejsej",70);
        Storage.addFad(fad);
        Storage.addFad(fad2);

        Aftapning aftapning1 = new Aftapning(fad,Storage.getDestillater(),50,LocalDate.now());
        Aftapning aftapning2 = new Aftapning(fad2,Storage.getDestillater(),25,LocalDate.now());
        Storage.addAftapning(aftapning1);
        Storage.addAftapning(aftapning2);
    }

}
