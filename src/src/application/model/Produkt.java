package application.model;

import java.util.ArrayList;

public class Produkt {
    private ArrayList<Aftapning> aftapninger;
    private int kapacitetILiter;
    private double alkoholprocent;

    public Produkt(ArrayList<Aftapning> aftapninger, double alkoholprocent) {
        this.aftapninger = aftapninger;
        this.kapacitetILiter = 1;
        this.alkoholprocent = alkoholprocent;
    }

    public ArrayList<Aftapning> getAftapninger() {
        return aftapninger;
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
        return "Flaske{" +
                "aftapninger=" + aftapninger +
                ", kapacitetILiter=" + kapacitetILiter +
                ", alkoholprocent=" + alkoholprocent +
                '}';
    }
}
