package controller.fileprocessor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.entity.Entity;
import model.entity.ObjetEllipse;
import model.entity.ObjetFixe;
import model.entity.ObjetSimule;
import model.entity.Vaisseau;
import model.movement.Vecteur;

/**
 * 
 * @author cleme
 *	Classe récupérant le fichier source et le lit.
 *	Elle repère et récupère les variables pour les transmettre.
 */

public class RecupFichierSource {

    private double G = -1;
    private double dt = -1;
    private double fa = -1;
    private int rayon = -1;

    private boolean premierPassage = true;
    private boolean vaisseauUnique = true;

    private ArrayList<Entity> listeCorpsCeleste = new ArrayList<Entity>();

    public ArrayList<Entity> getListeCorpsCeleste() {
        return listeCorpsCeleste;
    }

    public void setListeCorpsCeleste(ArrayList<Entity> listeObjet) {
        this.listeCorpsCeleste = listeObjet;
    }

    public double getG() {
        return G;
    }

    public void setG(double g) {
        G = g;
    }

    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public double getFa() {
        return fa;
    }

    public void setFa(double fa) {
        this.fa = fa;
    }

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public RecupFichierSource(){

    }

    @SuppressWarnings("resource")
	public int donneeFichier(String cheminFichier){
        int i;
        try{
            InputStream flux=new FileInputStream("././ressource/astro/" + cheminFichier);
            InputStreamReader lecture=new InputStreamReader(flux);
            BufferedReader buff=new BufferedReader(lecture);
            String ligne;
            while ((ligne=buff.readLine())!=null){
                i = this.affectationDonnee(ligne);
                if(i != 0){
                    return 1;
                }
            }
            buff.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public int affectationDonnee(String fichier) {
        if(fichier.charAt(0) != '#' && fichier.charAt(0) != ' ' && !fichier.isEmpty() && fichier.charAt(0) != 65279) {
            String[] tab =  fichier.split(" ");
            /**
             * Ligne de parametres
             */

            /**
             * Si la premiere ligne (ormis les commentaires) n'est pas les ligne de PARAMS
             */
            if(premierPassage) {
                if(!tab[0].equals("PARAMS")) {
                    System.out.println("La première ligne n'est pas PARAMS");
                    return 1;
                }
            }
            if(tab[0].equals("PARAMS")) {
                for (int i = 1; i <= tab.length - 1; i++) {
                    if (tab[i].startsWith("G")) {
                        this.G = Double.parseDouble(tab[i].substring(2));
                    }
                    if (tab[i].startsWith("dt")) {
                        this.dt = Double.parseDouble(tab[i].substring(3));
                    }
                    if (tab[i].startsWith("fa")) {
                        this.fa = Double.parseDouble(tab[i].substring(3));
                    }
                    if (tab[i].startsWith("rayon")) {
                        this.rayon = Integer.parseInt(tab[i].substring(6));
                    }
                }

                /**
                 * Mauvaise declaration des parametre dans le fichier source
                 */
                if(G == -1 || dt == -1 || fa == -1 || rayon == -1){
                    System.out.println("Problème dans les paramtètres");
                    return 1;
                }
                premierPassage = false;
            }
            else {
                /**
                 * Objet fixe
                 */
            	double masse=0;
            	double rayon=0.0;
            	Vecteur position = new Vecteur(0, 0);
            	Color c = null;
            	double red = -1;
            	double green = -1;
            	double blue = -1;
            	double vx=0;
            	double vy=0;

                Image sprite;
                if (tab[1].equals("Fixe")) {
                    ObjetFixe of;
                    String nomf=tab[0].substring(0, tab[0].length() - 1);

                    for (int i = 1; i <= tab.length - 1; i++) {

                        if (tab[i].startsWith("masse")) {
                            masse=(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                        }

                        if (tab[i].startsWith("posx")) {
                            position.setx(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                        }
                        if (tab[i].startsWith("posy")) {
                            position.sety(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                        }

                        if(tab[i].startsWith("couleurRed")) {
                        	red = Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1));
                        }
                        if(tab[i].startsWith("couleurGreen")) {
                        	green = Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1));
                        }
                        if(tab[i].startsWith("couleurBlue")) {
                        	blue = Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1));
                        }
                    }
                    
                    if(red >= 0.0 && red <=1.0 && green >= 0.0 && green <=1.0 && blue >= 0.0 && blue <=1.0) {
                    	c = new Color(red, green, blue, 1.0);
                    }
                    
                    rayon = this.rayonGraceMasse(masse, true);

                    of= new ObjetFixe(nomf, masse, rayon, position, 0, 0, null, c);


                    /**
                     * Mauvaise declaration d'un objet fixe dans le fichier source
                     */
                    if(masse == 0 || rayon == 0 || nomf == null){
                        System.out.println("Problème de déclaration d'un objet fixe");
                        return 1;
                    }

                    listeCorpsCeleste.add(of);
                }
                else {
                    /**
                     * Objet simulé
                     */
                    if (tab[1].equals("Simulé")) {
                    	String noms=tab[0].substring(0, tab[0].length()-1);
                    	

                        for (int i = 1; i <= tab.length - 1; i++) {

                            if (tab[i].startsWith("masse")) {
                                masse =Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1));
                            }

                            if (tab[i].startsWith("posx")) {
                                position.setx(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                
                            }
                            if (tab[i].startsWith("posy")) {
                            	position.sety(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                            }

                            if(tab[i].startsWith("vitx")){
                               vx=Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1));
                            }if(tab[i].startsWith("vity")){
                                vy=Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1));
                            }

                            if(tab[i].startsWith("couleurRed")) {
                            	red = Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1));
                            }
                            if(tab[i].startsWith("couleurGreen")) {
                            	green = Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1));
                            }
                            if(tab[i].startsWith("couleurBlue")) {
                            	blue = Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1));
                            }
                        }
                        
                        if(red >= 0.0 && red <=1.0 && green >= 0.0 && green <=1.0 && blue >= 0.0 && blue <=1.0) {
                        	c = new Color(red, green, blue, 1.0);
                        }

                        rayon = this.rayonGraceMasse(masse, true);

                        ObjetSimule os = new ObjetSimule(noms, masse, rayon, position, vx, vy, null, c);
                        listeCorpsCeleste.add(os);
                    }
                    else {
                        /**
                         * Objet ellipse
                         */
                        if (tab[1].equals("Ellipse")) {
                            ObjetEllipse oe= new ObjetEllipse();
                            oe.setNom(tab[0].substring(0, tab[0].length() - 1));

                            for (int i = 1; i <= tab.length - 1; i++) {

                                if (tab[i].startsWith("masse")) {
                                    oe.setMasse(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                }

                                if (tab[i].startsWith("posx")) {
                                    position.setx(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                }
                                if (tab[i].startsWith("posy")) {
                                    position.sety(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                }
                                oe.setPosition(position);

                                if(tab[i].startsWith("periode")){
                                    oe.setPeriode(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                }
                                
                                if(tab[i].startsWith("couleurRed")) {
                                	red = Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1));
                                }
                                if(tab[i].startsWith("couleurGreen")) {
                                	green = Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1));
                                }
                                if(tab[i].startsWith("couleurBlue")) {
                                	blue = Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1));
                                }

                                if(tab[i].startsWith("f1")){
                                    String nom = tab[i].substring(tab[i].indexOf('=')+1);
                                    Entity objetFixe = new ObjetFixe();
                                    for(Entity o : listeCorpsCeleste){
                                        if(o.getNom().equals(nom)){
                                            objetFixe = o;
                                        }
                                    }
                                    oe.setObjetFixe1((ObjetFixe) objetFixe);
                                }

                                if(tab[i].startsWith("f2")){
                                    String nom = tab[i].substring(tab[i].indexOf('=')+1);
                                    Entity objetFixe = new ObjetFixe();
                                    for(Entity o : listeCorpsCeleste){
                                        if(o.getNom().equals(nom)){
                                            objetFixe = o;
                                        }
                                    }
                                    oe.setObjetFixe2((ObjetFixe) objetFixe);
                                }
                                
                            }
                            if(red >= 0.0 && red <= 1.0 && green >= 0.0 && green <=1.0 && blue >= 0.0 && blue <=1.0) {
                            	c = new Color(red, green, blue, 1.0);
                            }
                            listeCorpsCeleste.add(oe);
                        }
                        else{
                            /**
                             * Le vaisseau
                             */
                            if (tab[1].equals("Vaisseau")) {
                                if(!vaisseauUnique){
                                    System.out.println("Plusieurs vaisseaux définis");
                                    return 1;
                                }
                                vaisseauUnique = false;
                                Vaisseau vaisseau = Vaisseau.getInstance();
                                vaisseau.setNom(tab[0].substring(0, tab[0].length() - 1));
                                Vecteur vecteur = new Vecteur(0,0);

                                for (int i = 1; i <= tab.length - 1; i++) {

                                    if (tab[i].startsWith("masse")) {
                                        vaisseau.setMasse(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                    }
 
                                    if (tab[i].startsWith("posx")) {
                                        position.setx(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                    }
                                    if (tab[i].startsWith("posy")) {
                                        position.sety(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                    }
                                    vaisseau.setPosition(position);

                                    if(tab[i].startsWith("vitx")){
                                        vecteur.setx(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                    }if(tab[i].startsWith("vity")){
                                        vecteur.sety(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                    }
                                    vaisseau.setVitesse(vecteur.getx(), vecteur.gety());

                                    if (tab[i].startsWith("pretro")) {
                                        vaisseau.setPretro(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                    }
                                    if (tab[i].startsWith("pprincipal")) {
                                        vaisseau.setPprincipal(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                    }
                                    
                                    if(tab[i].startsWith("couleurRed")) {
                                    	red = Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1));
                                    }
                                    if(tab[i].startsWith("couleurGreen")) {
                                    	green = Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1));
                                    }
                                    if(tab[i].startsWith("couleurBlue")) {
                                    	blue = Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1));
                                    }
                                }
                                
                                if(red >= 0.0 && red <=1.0 && green >= 0.0 && green <=1.0 && blue >= 0.0 && blue <=1.0) {
                                	c = new Color(red, green, blue, 1.0);
                                }
                                listeCorpsCeleste.add(vaisseau);
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    public double rayonGraceMasse(double masse, boolean etoile){
    	double p;
    	if(etoile) {
    		p = 1;
    	}else {
    		p = 5;
    	}
    	double newRayon = Math.pow((masse/p)/((4*Math.PI)/3), 1.0/3.0);
    	return newRayon;

    }
}
