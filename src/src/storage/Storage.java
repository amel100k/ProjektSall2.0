package storage;

import application.model.*;

import java.util.ArrayList;


public class Storage {
    private static ArrayList<Fad> fade = new ArrayList<>();
    private static ArrayList<Destillering> destilleringer = new ArrayList<>();
    private static ArrayList<Destillat> destillater = new ArrayList<>();
    private static ArrayList<Aftapning> aftapninger = new ArrayList<>();
    private static ArrayList<Produkt> flasker = new ArrayList<>();
    private static ArrayList<Lager> lagere = new ArrayList<>();
    public static void addDestillering(Destillering destillering) {
        destilleringer.add(destillering);
    }
    public static void addFad(Fad fad) {
        fade.add(fad);
    }
    public static void addDestillat(Destillat destillat){
        destillater.add(destillat);
    }
    public static void addAftapning(Aftapning aftapning){
        aftapninger.add(aftapning);
    }
    public static void addProdukt(Produkt flakse){
        flasker.add(flakse);
    }
    public static void addLager(Lager lager){
        lagere.add(lager);
    }
    public static ArrayList<Lager> getLagere() {
        return lagere;
    }

    public static ArrayList<Fad> getFade() {
        return fade;
    }

    public static ArrayList<Destillering> getDestilleringer() {
        return destilleringer;
    }

    public static ArrayList<Destillat> getDestillater() {
        return destillater;
    }

    public static ArrayList<Aftapning> getAftapninger() {
        return aftapninger;
    }

    public static ArrayList<Produkt> getFlasker() {
        return flasker;
    }
}
