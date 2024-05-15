package application.model;

import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Destillering {
    private LocalDate startDato;
    private LocalDate slutDato;
    private int maltbatchIGram;
    private String kornsort;
    private int væskeMængdeIMl;
    private double alkoholprocent;
    private String kommentar;
    private String rygemateriale;
    private ArrayList<Destillat> destillater = new ArrayList<>();

    public Destillering(LocalDate startDato, LocalDate slutDato, int maltbatchIGram, String kornsort, int væskeMængdeIMl, double alkoholprocent, String kommentar, String rygemateriale) {
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.maltbatchIGram = maltbatchIGram;
        this.kornsort = kornsort;
        this.væskeMængdeIMl = væskeMængdeIMl;
        this.alkoholprocent = alkoholprocent;
        this.kommentar = kommentar;
        this.rygemateriale = rygemateriale;

    }

    @Override
    public String toString() {
        return "Destillering: " + "Start dato: " + startDato + ", Slut dato: " +
                slutDato + ", Maltbatch i gram: " + maltbatchIGram +
                ", Kornsort: '" + kornsort + '\'' + ", Væskemængde i ml: " + væskeMængdeIMl
                + ", Alkoholprocent: " + alkoholprocent + ", Kommentar: '" + kommentar + '\''
                + ", Rygemateriale: '" + rygemateriale + '\'';
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public int getMaltbatchIGram() {
        return maltbatchIGram;
    }

    public String getKornsort() {
        return kornsort;
    }

    public int getVæskeMængdeIMl() {
        return væskeMængdeIMl;
    }

    public double getAlkoholprocent() {
        return alkoholprocent;
    }

    public String getKommentar() {
        return kommentar;
    }

    public String getRygemateriale() {
        return rygemateriale;
    }

    public ArrayList<Destillat> getDestillater() {
        return destillater;
    }
    public void addDestillat(Destillat destillat){
        if (!destillater.contains(destillat)){
            destillater.add(destillat);
        }
    }
    public Destillat createDestillat (double alkoholProcent, Mængde mængde){
        Destillat destillat = new Destillat(alkoholProcent, mængde);
        destillater.add(destillat);
        Storage.addDestillat(destillat);
        return destillat;
    }
}
