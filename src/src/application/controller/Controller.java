package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    public static Destillering createDestillering(LocalDate startDato, LocalDate slutDato, int maltbatchIGram, String kornsort, int væskeMængdeIMl, double alkoholprocent, String kommentar, String rygemateriale){
        Destillering destillering = new Destillering(startDato, slutDato, maltbatchIGram, kornsort, væskeMængdeIMl, alkoholprocent, kommentar, rygemateriale);
        Storage.addDestillering(destillering);
        return destillering;
    }
    public static Fad createFad(String fadHistore, String tidligereBrug, int placering, String koebssted, String fadNavn, int fadKapacitet){
        Fad fad = new Fad(fadHistore, tidligereBrug, placering, koebssted, fadNavn, fadKapacitet);
        Storage.addFad(fad);
        return fad;
    }
    public static Aftapning createAftapning(Fad fad, ArrayList<Destillat> destillat, int literDerFyldesPåFad, LocalDate dato){
        Aftapning aftapning = new Aftapning(fad,destillat,fad.getMængdePåFad(),dato);
        Storage.addAftapning(aftapning);
        return aftapning;
    }
    public static Flaske createFlaske(ArrayList<Aftapning> aftapninger, double alkoholprocent){
        Flaske flaske = new Flaske(aftapninger,alkoholprocent);
        Storage.addFlaske(flaske);
        return flaske;
    }

}
