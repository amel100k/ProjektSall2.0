package application.model;

import java.util.ArrayList;

public class Lager {
    private String adresse;
    private int maxAntalFad;
    private int antalNuværendeFade;
    private ArrayList<Fad> fade = new ArrayList<>();

    public Lager(String adresse, int maxAntalFad) {
        this.adresse = adresse;
        this.maxAntalFad = maxAntalFad;

    }

    public void addFad(Fad fad){
        if (antalNuværendeFade < maxAntalFad){
        if (!fade.contains(fad)){
            fade.add(fad);
            antalNuværendeFade++;
        }
        }
        else {
            throw new IllegalArgumentException("Der er ikke plads til flere fade på lageret!");
        }
    }
    public void removeFad(Fad fad){
        if (fade.contains(fad)){
            fade.remove(fad);
            antalNuværendeFade--;
        }
    }

    public String getAdresse() {
        return adresse;
    }

    public int getMaxAntalFad() {
        return maxAntalFad;
    }

    public ArrayList<Fad> getFade() {
        return fade;
    }
}
