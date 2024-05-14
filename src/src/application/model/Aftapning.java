package application.model;

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

    public void setLiter(int liter) {
        this.liter = liter;
    }

    public int getLiter() {
        return liter;
    }

    @Override
    public String toString() {
        return "Aftapning{" +
                "fad=" + fad +
                ", destillat=" + destillat +
                ", literDerFyldesPåFad=" + liter +
                ", dato=" + dato +
                '}';
    }
}
