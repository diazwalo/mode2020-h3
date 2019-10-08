package controller.fileprocessor;

import model.entity.*;
import model.movement.Position;
import model.movement.VecteurVitesse;

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
            System.out.println(e.toString());
        }
        return 0;
    }

    public int affectationDonnee(String fichier) {
        if (!fichier.startsWith("#") || fichier.startsWith(" ")) {
            String[] tab = fichier.split(" ");
            /**
             * Ligne de parametres
             */

            /**
             * Si la premiere ligne (ormis les commentaires) n'est pas les ligne de PARAMS
             */
            if(premierPassage){
                if(!tab[0].equals("PARAMS")){
                    System.out.println("La première ligne n'est pas PARAMS");
                    return 1;
                }
            }
            if (tab[0].equals("PARAMS")) {
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
                    System.out.println("Problème dans les paramtètre");
                    return 1;
                }
                premierPassage = false;
            }
            else {
                /**
                 * Objet fixe
                 */
                if (tab[1].equals("Fixe")) {
                    ObjetFixe of = new ObjetFixe();
                    of.setNom(tab[0].substring(0, tab[0].length() - 1));
                    Position position = new Position();

                    for (int i = 1; i <= tab.length - 1; i++) {

                        if (tab[i].startsWith("masse")) {
                            of.setMasse(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                        }

                        if (tab[i].startsWith("posx")) {
                            position.setPosX(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                        }
                        if (tab[i].startsWith("posy")) {
                            position.setPosY(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                        }
                        if (tab[i].startsWith("rayon")) {
                            of.setRayon(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                        }

                        of.setPosition(position);
                    }

                    /**
                     * Mauvaise declaration d'un objet fixe dans le fichier source
                     */
                    if(of.getMasse() == 0 || of.getRayon() == 0 || of.getPosition().getPosX() == 0 || of.getPosition().getPosY() == 0 || of.getNom() == null){
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
                        ObjetSimule os = new ObjetSimule();
                        os.setNom(tab[0].substring(0, tab[0].length() - 1));
                        Position position = new Position();
                        VecteurVitesse vecteur = new VecteurVitesse();

                        for (int i = 1; i <= tab.length - 1; i++) {

                            if (tab[i].startsWith("masse")) {
                                os.setMasse(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                            }

                            if (tab[i].startsWith("posx")) {
                                position.setPosX(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                            }
                            if (tab[i].startsWith("posy")) {
                                position.setPosY(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                            }
                            os.setPosition(position);

                            if(tab[i].startsWith("vitx")){
                                vecteur.setVitX(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                            }if(tab[i].startsWith("vity")){
                                vecteur.setVitY(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                            }

                            if (tab[i].startsWith("rayon")) {
                                os.setRayon(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                            }
                            os.setVecteurVitesse(vecteur);
                        }
                        listeCorpsCeleste.add(os);
                    }
                    else {
                        /**
                         * Objet ellipse
                         */
                        if (tab[1].equals("Ellipse")) {
                            ObjetEllipse oe = new ObjetEllipse();
                            oe.setNom(tab[0].substring(0, tab[0].length() - 1));
                            Position position = new Position();

                            for (int i = 1; i <= tab.length - 1; i++) {

                                if (tab[i].startsWith("masse")) {
                                    oe.setMasse(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                }

                                if (tab[i].startsWith("posx")) {
                                    position.setPosX(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                }
                                if (tab[i].startsWith("posy")) {
                                    position.setPosX(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                }
                                oe.setPosition(position);

                                if(tab[i].startsWith("periode")){
                                    oe.setPeriode(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
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
                                Position position = new Position();
                                VecteurVitesse vecteur = new VecteurVitesse();

                                for (int i = 1; i <= tab.length - 1; i++) {

                                    if (tab[i].startsWith("masse")) {
                                        vaisseau.setMasse(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                    }

                                    if (tab[i].startsWith("posx")) {
                                        position.setPosX(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                    }
                                    if (tab[i].startsWith("posy")) {
                                        position.setPosX(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                    }
                                    vaisseau.setPosition(position);

                                    if(tab[i].startsWith("vitx")){
                                        vecteur.setVitX(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                    }if(tab[i].startsWith("vity")){
                                        vecteur.setVitY(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                    }
                                    vaisseau.setVecteurVitesse(vecteur);

                                    if (tab[i].startsWith("pretro")) {
                                        vaisseau.setPretro(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                    }
                                    if (tab[i].startsWith("pprincipal")) {
                                        vaisseau.setPprincipal(Double.parseDouble(tab[i].substring(tab[i].indexOf('=')+1)));
                                    }

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
}
