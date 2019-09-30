package controller.core;

import model.entity.Etoile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    private Etoile etoile = new Etoile();

    public void donneeFichier(String cheminFichier){
        try{
            InputStream flux=new FileInputStream("././ressource/" + cheminFichier);
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

    public void affectationDonnee(String fichier){
        if(!fichier.startsWith("#")){
            String [] tab = fichier.split(" ");
            //Ligne PARAMS
            if(tab[0].equals("PARAMS")){
                for(int i = 1; i < tab.length - 1; i++){
                    if(tab[i].startsWith("G")){
                        this.G = tab[i].substring(2);
                    }
                    if(tab[i].startsWith("dt")){
                        this.dt = tab[i].substring(3);
                    }
                    if(tab[i].startsWith("fa")){
                        this.fa = tab[i].substring(3);
                    }
                    if(tab[i].startsWith("rayon")){
                        this.rayon = tab[i].substring(6);
                    }
                }
            }
            else{
                if(tab[1].equals("Fixe")){
                    etoile.setNom(tab[0].substring(0, tab[0].length() - 1));

                }
            }
        }
    }

    public static void main(String[] args) {
//        System.out.println(this.donneeFichier("source.txt"));
        String s = "test:";
    }
}
