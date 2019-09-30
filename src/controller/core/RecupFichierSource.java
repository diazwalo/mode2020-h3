package controller.core;

import model.entity.CorpsCeleste;
import model.entity.ObjetFixe;
import model.movement.Position;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * @author cleme
 *	Classe récupérant le fichier source et le lit.
 *	Elle repère et récupère les variables pour les transmettre.
 */

public class RecupFichierSource {

    private String G;
    private String dt;
    private String fa;
    private String rayon;

    private ArrayList<CorpsCeleste> listeCorpsCeleste = new ArrayList<>();

    public ArrayList<CorpsCeleste> getListeCorpsCeleste() {
        return listeCorpsCeleste;
    }

    public void setListeCorpsCeleste(ArrayList<CorpsCeleste> listeObjet) {
        this.listeCorpsCeleste = listeObjet;
    }

    public String getG() {
        return G;
    }

    public void setG(String g) {
        G = g;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public RecupFichierSource(String chemin){
        donneeFichier(chemin);
    }

    public void donneeFichier(String cheminFichier){
        try{
            InputStream flux=new FileInputStream("././ressource/astro/" + cheminFichier);
            InputStreamReader lecture=new InputStreamReader(flux);
            BufferedReader buff=new BufferedReader(lecture);
            String ligne;
            while ((ligne=buff.readLine())!=null){
                this.affectationDonnee(ligne);
            }
            buff.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void affectationDonnee(String fichier) {
        if (!fichier.startsWith("#")) {
            String[] tab = fichier.split(" ");
            //Ligne PARAMS
            if (tab[0].equals("PARAMS")) {
                for (int i = 1; i < tab.length - 1; i++) {
                    if (tab[i].startsWith("G")) {
                        this.G = tab[i].substring(2);
                    }
                    if (tab[i].startsWith("dt")) {
                        this.dt = tab[i].substring(3);
                    }
                    if (tab[i].startsWith("fa")) {
                        this.fa = tab[i].substring(3);
                    }
                    if (tab[i].startsWith("rayon")) {
                        this.rayon = tab[i].substring(6);
                    }
                }
            } else {
                if (tab[1].equals("Fixe")) {
                    ObjetFixe eto = new ObjetFixe();
                    eto.setNom(tab[0].substring(0, tab[0].length() - 1));
                    Position position = new Position();
                    for (int i = 1; i < tab.length - 1; i++) {

                        if (tab[i].startsWith("masse")) {
                            eto.setMasse(Double.parseDouble(tab[i].substring(tab[i].indexOf('='))));
                        }

                        if (tab[i].startsWith("posx")) {
                            position.setPosX(Double.parseDouble(tab[i].substring(tab[i].indexOf('='))));
                        }
                        if (tab[i].startsWith("posy")) {
                            position.setPosX(Double.parseDouble(tab[i].substring(tab[i].indexOf('='))));
                        }
                        eto.setPosition(position);
                    }
                    listeCorpsCeleste.add(eto);
                } else {

                }
            }
        }
    }
}
