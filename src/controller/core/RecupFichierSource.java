package controller.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RecupFichierSource {

    private Double G;
    private int dt;
    private int fa;
    private int rayon;

    public static void donneeFichier(String cheminFichier){
        try{
            InputStream flux=new FileInputStream("././ressource/" + cheminFichier);
            InputStreamReader lecture=new InputStreamReader(flux);
            BufferedReader buff=new BufferedReader(lecture);
            String ligne;
            while ((ligne=buff.readLine())!=null){


            }
            buff.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void affectationDonné(String fichier){
        if(!fichier.startsWith("#")){
            if(fichier.startsWith("PARAMS")){

            }
        }
    }

//    public static void main(String[] args) {
  //      System.out.println(donneeFichier("source.txt"));
    //}
}
