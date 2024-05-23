package application.model;

import java.util.ArrayList;

public class Produkt {
    private Aftapning aftapning;
    private int kapacitetILiter;
    private double alkoholprocent;
    public Produkt(Aftapning aftapning, double alkoholprocent, int kapacitetILiter) {
        this.aftapning = aftapning;
        this.kapacitetILiter = kapacitetILiter;
        this.alkoholprocent = alkoholprocent;
    }

    public Aftapning getAftapning() {
        return aftapning;
    }

    public int getKapacitetILiter() {
        return kapacitetILiter;
    }


    public double getAlkoholprocent() {
        return alkoholprocent;
    }

    public int beregnLiterProdukt(int liter){
        int antalLiterAfProdukt = 0;
        antalLiterAfProdukt = liter/kapacitetILiter;

        return antalLiterAfProdukt;
    }
    @Override
    public String toString() {
        return "Alkohol%:" + alkoholprocent +
                " Mængde" + kapacitetILiter + "L";
    }
}
