package application.controller;

import application.model.Destillat;
import application.model.Destillering;
import application.model.Fad;
import application.model.Aftapning;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
public class Controller {
    public static Destillering createDestillering(LocalDate startDato, LocalDate slutDato, int maltbatchIGram, String kornsort, int væskeMængdeIMl, double alkoholprocent, String kommentar, String rygemateriale){
        if (startDato.isAfter(slutDato)) {
            throw new IllegalArgumentException("Startdatoen kan ikke være efter slutdatoen");
        }
        Destillering destillering = new Destillering(startDato, slutDato, maltbatchIGram, kornsort, væskeMængdeIMl, alkoholprocent, kommentar, rygemateriale);
        Storage.addDestillering(destillering);
        return destillering;
    }
    public static Fad createFad(String fadHistore, String tidligereBrug, int placering, String koebssted, String fadNavn, int fadKapacitet){
        if (fadKapacitet < 0) {
            throw new IllegalArgumentException("Kapaciteten kan ikke være negativ");
        }
        Fad fad = new Fad(fadHistore, tidligereBrug, placering, koebssted, fadNavn, fadKapacitet);
        Storage.addFad(fad);
        return fad;
    }
    public static Aftapning createAftapning(Fad fad, ArrayList<Destillat> destillat, int literDerFyldesPåFad, LocalDate dato){
        if (literDerFyldesPåFad > fad.getFadKapacitet()) {
            throw new IllegalArgumentException("Mængden af liter må ikke overskride kapaciteten af fadet");
        }
        Aftapning aftapning = new Aftapning(fad,destillat,fad.getMængdePåFad(),dato);
        Storage.addAftapning(aftapning);
        return aftapning;
    }
}