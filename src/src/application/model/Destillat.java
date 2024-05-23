package application.model;

public class Destillat {
    private double alkoholProcent;
    private Mængde mængde;
    private Destillering destillering;

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

    public Destillering getDestillering() {
        return destillering;
    }

    @Override
    public String toString() {
        return "Alkoholprocent: " + alkoholProcent +
                ", Liter: " + mængde.getMængdePåDestillat() + "L";
    }
}
