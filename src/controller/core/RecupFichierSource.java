package controller.core;

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

    public static String donneeFichier(String cheminFichier){
        String res = "";
        try{
            InputStream flux=new FileInputStream("././ressource/" + cheminFichier);
            InputStreamReader lecture=new InputStreamReader(flux);
            BufferedReader buff=new BufferedReader(lecture);
            String ligne;
            while ((ligne=buff.readLine())!=null){
                res += ligne + "\n";
            }
            buff.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(donneeFichier("source.txt"));
    }
}
