package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.Period;

public class Aftapning {
    private Fad fad;
    private ArrayList<Destillat> destillat;
    private int liter;
    private LocalDate startDato;

    public Aftapning(Fad fad, ArrayList<Destillat> destillat, int literDerFyldesPåFad, LocalDate startDato) {
        this.fad = fad;
        this.destillat = destillat;
        this.liter = literDerFyldesPåFad;
        this.startDato = startDato;
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
    public void flytTilFad(int antalLiter){
        if (antalLiter > liter){
            throw new IllegalArgumentException("Mængde overskrider tilgængelig plads i fad");
        }
        liter -= antalLiter;
    }

    public boolean erTreAarSidenStartDato() {
        Period period = Period.between(startDato, LocalDate.now());
        return period.getYears() >= 3;
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

    public Fad getFad() {
        return fad;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    @Override
    public String toString() {
        return  "Aftap fra fad: " + fad.getFadNavn() + ", " +
                "Antal liter klar til aftapning: " + liter;
    }
}
