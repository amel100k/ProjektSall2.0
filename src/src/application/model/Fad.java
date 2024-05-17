package application.model;

public class Fad {
    private String fadHistore;
    private String tidligereBrug;
    private int placering;
    private String koebssted;
    private String fadNavn;
    private int fadKapacitet;
    private int mængdePåFad;
    private Produkt produkt;
    private Lager lager;

    public Fad(String fadHistore, String tidligereBrug, int placering, String koebssted, String fadNavn, int fadKapacitet, Lager lager) {
        this.fadHistore = fadHistore;
        this.tidligereBrug = tidligereBrug;
        this.placering = placering;
        this.koebssted = koebssted;
        this.fadNavn = fadNavn;
        this.fadKapacitet = fadKapacitet;
        this.mængdePåFad = 0;
        this.lager = lager;
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

    public void setFadKapacitet(int fadKapacitet) {
        this.fadKapacitet = fadKapacitet;
    }

    public int getMængdePåFad() {
        return mængdePåFad;
    }
    public int getLedigPlads(){
        return fadKapacitet-mængdePåFad;
    }

    public void setMængdePåFad(int mængdePåFad) {
        this.mængdePåFad = mængdePåFad;
    }

    public Lager getLager() {
        return lager;
    }

    public void setLager(Lager lager) {
        this.lager = lager;
    }

    public void fyldPåFad(int mængde) {
        if (mængde > getLedigPlads()) {
            throw new IllegalArgumentException("Mængde overskrider tilgængelig plads i fad");
        }
        mængdePåFad += mængde;
    }

    public void fyldPaaFlaske(Fad fad, int mængde){
        if(mængde < fad.getMængdePåFad()){
            fad.setMængdePåFad(fad.getFadKapacitet() - mængde);
        }
    }

    @Override
    public String toString() {
        return
                "Fadnavn: " + fadNavn  +
                ", Fadkapacitet: " + fadKapacitet;
    }
}
