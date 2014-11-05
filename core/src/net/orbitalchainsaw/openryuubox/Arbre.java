package net.orbitalchainsaw.openryuubox;

import java.util.ArrayList;
import net.orbitalchainsaw.openryuubox.boxes.*;

/**
 * Created by sidediallo on 30/10/14.
 */
public class Arbre {
    Panel p_gauche, p_droit;
    ArrayList<Box>arbre_gauche;
    ArrayList<Box>arbre_droit;

    public Arbre(Panel pg, Panel pd) {
        p_gauche = pg;
        p_droit = pd;
        arbre_gauche = null;
        arbre_droit = null;
    }

    /**
     *
     * @param pg : contient tous les boxes de gauche
     */
    void ajoutGauche(Panel pg) {

    }

    /**
     *
     * @param pd : Contient tous les boxes de droite
     */
    void ajoutDroit(Panel pd) {

    }
}
