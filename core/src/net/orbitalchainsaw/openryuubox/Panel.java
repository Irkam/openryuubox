package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import net.orbitalchainsaw.openryuubox.boxes.Box;
import net.orbitalchainsaw.openryuubox.boxes.BoxContainer;
import net.orbitalchainsaw.openryuubox.boxes.EmptyBox;
import net.orbitalchainsaw.openryuubox.draganddrop.BottomBoxTarget;
import net.orbitalchainsaw.openryuubox.draganddrop.InnerBoxTarget;
import net.orbitalchainsaw.openryuubox.draganddrop.LeftBoxTarget;

import java.util.ArrayList;

/**
 * Panneau délimitant une moitié de la zone de jeu, contenant un ou plusieurs BoxContainers.
 * @author Jean-Vincent
 *
 */
public class Panel{
    protected int x, y, width, height;
    protected Actor panelTarget;
    public Stage stage;
    public DragAndDrop dragAndDrop;
    public ArrayList<BoxContainer> boxContainers, rootBoxContainers;
    public ArrayList<DragAndDrop.Target> boxDaDTargets;
    public PanelContainer parent;

    /**
     * Crée un Panel qui gèrera son affichage avec stage, son glisser-déposer avec dragAndDrop, une taille et une position définies et un PanelContainer parent
     * @param stage
     * @param dragAndDrop
     * @param x
     * @param y
     * @param width
     * @param height
     * @param parent
     */
    public Panel(final Stage stage, final DragAndDrop dragAndDrop,
                 int x, int y, int width, int height, PanelContainer parent){
        this.stage = stage;
        this.dragAndDrop = dragAndDrop;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.parent = parent;
        panelTarget = new Actor();
        panelTarget.setBounds(x, y, width, height);
        stage.addActor(panelTarget);

        boxContainers = new ArrayList<BoxContainer>();
        rootBoxContainers = new ArrayList<BoxContainer>();
        boxDaDTargets = new ArrayList<DragAndDrop.Target>();
    }

    /**
     * Ajoute un conteneur de Box au panneau. Lorsqu'il est ajouté, on lui ajoute aussi des cibles pour le glisser-déposer, en bas et à gauche, et on ajoute 
     * toutes les Box qu'il contient au stage pour qu'elles puissent être affichées. La cible du bas n'est pas ajoutée ici si le BoxContainer qu'on ajoute est
     * déjà placé en-dessous d'un autre BoxContainer. Dans le cas contraire c'est un BoxContainer racine, et il est ajouté dans un tableau correspondant à
     * son statut.
     * @param boxContainer BoxContainer à ajouter
     */
    public void addContainer(final BoxContainer boxContainer){
        boxContainer.setParentPanel(this);
        this.stage.addActor(boxContainer);
        for(Actor actor : boxContainer.boxes)
            this.stage.addActor(actor);
        this.boxContainers.add(boxContainer);

        for(final Box innerBox : boxContainer.boxes)
            dragAndDrop.addTarget(new InnerBoxTarget(innerBox, boxContainer));

        /*
        cible à gauche de la boite pour les produits
         */
        EmptyBox leftContainer = boxContainer.getLeftTarget();
        LeftBoxTarget leftBoxTarget = new LeftBoxTarget(leftContainer, boxContainer);
        boxDaDTargets.add(leftBoxTarget);
        stage.addActor(leftContainer);

        dragAndDrop.addTarget(leftBoxTarget);


        /*
        cible en-dessous de la boite pour les divisions
         */
        if(boxContainer.getParentBoxContainer() == null) {
            //Ne pas ajouter la cible pour créer une nouvelle fraction si le BoxContainer est déjà le dénominateur d'une fraction
            EmptyBox bottomContainer = boxContainer.getBottomTarget();
            BottomBoxTarget bottomBoxTarget = new BottomBoxTarget(bottomContainer, boxContainer);
            boxDaDTargets.add(bottomBoxTarget);
            stage.addActor(bottomContainer);

            dragAndDrop.addTarget(bottomBoxTarget);
        }else{
            rootBoxContainers.add(boxContainer);
        }

        if(boxContainer.getBottomBoxContainer() != null)
            addContainer(boxContainer.getBottomBoxContainer());
    }

    /**
     * Vérifie qu'un objet de taille et de coordonnées définies soit dans le Panel
     * @param x
     * @param y
     * @param width
     * @param height
     * @return true si les coordonnées sont comprises dans le Panel
     */
    public boolean isInBounds(int x, int y, int width, int height){
        return(x > this.x && x + width < this.width && y > this.y && y + height < this.height);
    }

    /**
     * Vérifie que ce panneau contienne un BoxContainer contenant la UnknownBox
     * @return true si un BoxContainer du Panel contient la UnknownBox. false sinon.
     */
    public boolean hasTheBox(){
        for(BoxContainer boxContainer : boxContainers){
            if(boxContainer.hasTheBox())
                return true;
        }
        return false;
    }

    /**
     * Vérifie que la condition de victoire soit atteinte
     * @return true si la condition de victoire est atteinte. false si la partie peut continuer.
     */
    public boolean gameOver(){
        for(BoxContainer boxContainer : boxContainers){
            if(boxContainer.gameOver())
                return true;
        }
        return false;
    }
}
