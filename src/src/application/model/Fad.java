package application.model;

import java.util.ArrayList;

public class Fad {
    private String fadHistore;
    private String tidligereBrug;
    private int placering;
    private String koebssted;
    private String fadNavn;
    private int fadKapacitet;
    private int mængdePåFad;
    ArrayList<Destillat> destillater = new ArrayList<>();
    private Flaske flaske;
    public Fad(String fadHistore, String tidligereBrug, int placering, String koebssted, String fadNavn, int fadKapacitet) {
        this.fadHistore = fadHistore;
        this.tidligereBrug = tidligereBrug;
        this.placering = placering;
        this.koebssted = koebssted;
        this.fadNavn = fadNavn;
        this.fadKapacitet = fadKapacitet;
        this.mængdePåFad = 0;
    }

    public String getFadHistore() {
        return fadHistore;
    }

    public String getTidligereBrug() {
        return tidligereBrug;
    }

    public int getPlacering() {
        return placering;
    }

    public String getKoebssted() {
        return koebssted;
    }

    public String getFadNavn() {
        return fadNavn;
    }

    public int getFadKapacitet() {
        return fadKapacitet;
    }

    public int getMængdePåFad() {
        return mængdePåFad;
    }
    public int getLedigPlads(){
        return fadKapacitet-mængdePåFad;
    }
    public ArrayList<Destillat> getDestillater() {
        return destillater;
    }

    public void setMængdePåFad(int mængdePåFad) {
        this.mængdePåFad = mængdePåFad;
    }

    public void fyldPåFad(int mængde) {
        if (mængde <= getLedigPlads()) {
            mængdePåFad += mængde;
        }
    }
    public void fyldPaaFlaske(Fad fad, int mængde){
        if(mængde < fad.getMængdePåFad()){
            fad.setMængdePåFad(fad.getFadKapacitet() - mængde);
        }
    }

    @Override
    public String toString() {
        return "Fad: " +
                "Fadhistore: '" + fadHistore + '\'' +
                ", Tidligere brug: '" + tidligereBrug + '\'' +
                ", Placering: " + placering +
                ", Koebssted: '" + koebssted + '\'' +
                ", Fadnavn: '" + fadNavn + '\'' +
                ", Fadkapacitet: " + fadKapacitet;
    }
}
