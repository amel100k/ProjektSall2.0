package application.model;

public class Mængde {
    private int mængdePåDestillat;
    private int mængdeBrugt = 0;

    public Mængde(int mængdePåDestillat) {
        this.mængdePåDestillat = mængdePåDestillat;
    }
    public void opdaterMængde(int mængde){
        mængdeBrugt += mængde;
    }
    public int opdaterMængde2(){
        return mængdePåDestillat -= mængdeBrugt;
    }
    public void resetMængdeBrugt(){
        mængdeBrugt = 0;
    }

    public int getMængdePåDestillat() {
        return mængdePåDestillat;
    }

    @Override
    public String toString() {
        return "" + mængdePåDestillat;
    }
}
