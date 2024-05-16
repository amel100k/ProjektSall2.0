package application.model;

import application.controller.Controller;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Aftapning {
    private Fad fad;
    private ArrayList<Destillat> destillat;
    private int liter;
    private LocalDate dato;

    public Aftapning(Fad fad, ArrayList<Destillat> destillat, int literDerFyldesPåFad, LocalDate dato) {
        this.fad = fad;
        this.destillat = destillat;
        this.liter = literDerFyldesPåFad;
        this.dato = dato;
    }
    public void fyldPaaFlaske(int literVand, int literAftap, Aftapning aftapning){
       int literVæskeIAlt = 0;
        literVæskeIAlt = aftapning.getLiter();
        if(literVæskeIAlt < literAftap){
            throw new IllegalArgumentException("Du kan ikke tappe mere end der er væske");
        }
        int antalLiterIAlt = 0;
        antalLiterIAlt = literVand;
        setLiter(literVand - literAftap);
    }

    public ArrayList<Destillat> getDestillat() {
        return destillat;
    }

    public void setLiter(int liter) {
        this.liter = liter;
    }

    public int getLiter() {
        return liter;
    }

    @Override
    public String toString() {
        return  "fad: " + fad.getFadNavn() +
                " liter aftap: " + liter;
    }
}
