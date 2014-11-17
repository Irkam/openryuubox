package net.orbitalchainsaw.openryuubox.boxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.orbitalchainsaw.openryuubox.Panel;
import net.orbitalchainsaw.openryuubox.draganddrop.InnerBoxTarget;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Conteneur de Box.
 * Contient une liste de Box représentant le numérateur, et un BoxContainer représentant le dénominateur, ayant lui aussi une liste de Box.
 * @author Jean-Vincent
 *
 */
public class BoxContainer extends Box {
    public ArrayList<Box> boxes = new ArrayList<Box>();
    BoxContainer bottomBoxContainer;
    EmptyBox leftTarget, bottomTarget;
    BoxContainer parentBoxContainer = null;
    public Panel parentPanel = null;

    /**
     * Crée un BoxContainer et l'ajoute à un panneau.
     * @param x
     * @param y
     * @param parentPanel panneau auquel sera attaché le BoxContainer
     */
    public BoxContainer(int x, int y, Panel parentPanel){
        super(x, y, new TextureRegion(new Texture("boxes/empty.png")));
        leftTarget = new EmptyBox((int) this.getX() - 64, (int) this.getY());
        bottomTarget = new EmptyBox((int) this.getX(), (int) this.getY() - 64);
        this.parentPanel = parentPanel;
    }
    
    /**
     * Met à jour les coordonées des Box dans le numérateur, généralement en cas d'ajout/suppression de Box.
     */
    private void updateBoxes(){
        int boxesLength = (boxes.size()+1) * 64;    //on compte le conteneur libre à gauche
        for(Box box : boxes) {
            int boxIndex = boxes.indexOf(box);
            box.setCoord((int) this.getX() + ((boxIndex+1) * box.width) - boxesLength/2, (int) this.getY());
        }
        leftTarget.setCoord((int) this.getX() - boxesLength / 2, (int) this.getY());
    }
    
    /**
     * Ajoute une Box et met à jour l'affichage
     * @param newBox Box à ajouter
     */
    public void addBox(Box newBox){
        if(parentPanel != null) {
            parentPanel.stage.addActor(newBox);
            parentPanel.dragAndDrop.addTarget(new InnerBoxTarget(newBox, this));
        }
        this.boxes.add(newBox);
        simplify();
        updateBoxes();
        parentPanel.parent.gameOver();
    }
    
    /**
     * Retire une Box de la liste et de l'affichage, si elle existe. Le BoxContainer est supprimé si la liste est vide.
     * @param box
     */
    public void removeBox(Box box){
        box.remove();
        boxes.remove(box);

        if(boxes.size() > 0)
            updateBoxes();
        else
            this.delete();
    }

    /**
     * Supprime le BoxContainer de l'affichage et de la liste des BoxContainer. Supprime aussi les BoxContainer enfants.
     */
    public void delete(){
        leftTarget.remove(); bottomTarget.remove();remove();
        for(Box box : boxes){
            box.remove();
        }
        boxes.clear();
        if(bottomBoxContainer != null)
            bottomBoxContainer.delete();
    }

    /**
     * Ajoute un BoxContainer comme dénominateur.
     * @param bc
     */
    public void addBottom(BoxContainer bc){
        if(parentPanel != null) {
            bc.setParentPanel(parentPanel);
            parentPanel.stage.addActor(bc);
        }
        this.bottomBoxContainer = bc;
        bc.setParentBoxContainer(this);
    }
    
    /**
     * Simplifie la fraction.
     * TODO: changer en void, simplifier les multiplications comme les divisions (lire la liste des Box et simplifier si possible)
     * @return true si une simplification a été faite.
     */
    public boolean simplify(){
        for(Box box : boxes){
            if(bottomBoxContainer != null)
                for(Box otherBox : bottomBoxContainer.boxes)
                    if(otherBox.type == box.type && otherBox.value == box.value){
                        bottomBoxContainer.removeBox(otherBox);
                        removeBox(box);
                        //addBox(new NumericBox(1));
                        return true;
                    }
            if(parentBoxContainer != null){
                for(Box otherBox : parentBoxContainer.boxes)
                    if(otherBox.type == box.type && otherBox.value == box.value){
                        parentBoxContainer.removeBox(otherBox);
                        removeBox(box);
                        //parentBoxContainer.addBox(new NumericBox(1));
                        return true;
                    }
            }
        }

        return false;
    }
    
    public void setParentBoxContainer(BoxContainer parent){parentBoxContainer = parent;}

    /**
     * 
     * @return true si le BoxContainer ou un BoxContainer enfant contient la UnknownBox. false sinon.
     */
    public boolean hasTheBox(){
        for(Box box : boxes)
            if(box.type == Box.UNKNOWN)
                return true;

        if(bottomBoxContainer != null)
            return bottomBoxContainer.hasTheBox();

        return false;
    }
    
    /**
     * appelée pour déterminer si la condition de victoire est atteinte, si la UnknownBox a été isolée.
     * @return true s'il ne reste que la UnknownBox dans tout le BoxContainer. false sinon.
     */
    public boolean gameOver(){
        if(boxes.size() == 1)
            if(boxes.get(0).type == Box.UNKNOWN)
                return true;
        return false;
    }
    
    /**
     * Renvoie la EmptyBox servant à afficher la cible à gauche pour le draganddrop
     * @return EmptyBox box cible draganddrop gauche
     */
    public EmptyBox getLeftTarget(){return leftTarget;}
    
    /**
     * Renvoie la EmptyBox servant à afficher la cible en-bas pour le draganddrop
     * @return EmptyBox box cible draganddrop droite
     */
    public EmptyBox getBottomTarget(){return bottomTarget;}
    
    /**
     * Renvoie le BoxContainer enfant
     * @return BoxContainer BoxContainer enfant. null si inexistant
     */
    public BoxContainer getBottomBoxContainer(){return bottomBoxContainer;}
    
    /**
     * Renvoie le BoxContainer parent
     * @return BoxContainer BoxContainer parent. null si inexistant (BoxContainer de plus haut niveau)
     */
    public BoxContainer getParentBoxContainer(){return parentBoxContainer;}

	@Override
	public void updateTextureByValue() {
		// TODO Auto-generated method stub
		
	}
}
