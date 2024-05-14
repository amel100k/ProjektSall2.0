package application.model;

import java.util.ArrayList;

public class Destillat {
    private double alkoholProcent;
    private Mængde mængde;

    public Destillat(double alkoholProcent, Mængde mængde) {
        this.alkoholProcent = alkoholProcent;
        this.mængde = mængde;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public Mængde getMængde() {
        return mængde;
    }

    @Override
    public String toString() {
        return "Destillat: " +
                "alkoholProcent: " + alkoholProcent +
                ", mængde: " + mængde;
    }
}
