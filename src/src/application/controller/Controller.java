package application.controller;

import application.model.*;
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
    public static Fad createFad(String fadHistore, String tidligereBrug, int placering, String koebssted, String fadNavn, int fadKapacitet, Lager lager){
        if (fadKapacitet < 0) {
            throw new IllegalArgumentException("Kapaciteten kan ikke være negativ");
        }
        Fad fad = new Fad(fadHistore, tidligereBrug, placering, koebssted, fadNavn, fadKapacitet, lager);
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

    public static Produkt createProdukt(Aftapning aftapning, double i, int liter) {
        Produkt produkt = new Produkt(aftapning,i,liter);
        Storage.addProdukt(produkt);
        return produkt;
    }

    public static double testBeregn(Aftapning aftapning, int literAlkohol, int literVand){
        double mængdeAlkohol = 0;
        aftapning.skabProdukt(aftapning.getLiter(),literAlkohol, aftapning);

        int antalLiterVæske = literAlkohol + literVand;
        if(literVand == 0){
            return aftapning.getDestillat().getFirst().getAlkoholProcent();
        }
        mængdeAlkohol = literAlkohol * aftapning.getDestillat().getFirst().getAlkoholProcent() / 100;
        return mængdeAlkohol / antalLiterVæske * 100;
    }

    public static Lager createLager(String adresse, int maxAntalFad){
        Lager lager = new Lager(adresse, maxAntalFad);
        Storage.addLager(lager);
        return lager;
    }

}