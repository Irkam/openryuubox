package net.orbitalchainsaw.openryuubox.boxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.orbitalchainsaw.openryuubox.Panel;
import net.orbitalchainsaw.openryuubox.draganddrop.InnerBoxTarget;

import java.util.ArrayList;

/**
 * Created by jivay on 22/10/14.
 */
public class BoxContainer extends Box {
    public ArrayList<Box> boxes = new ArrayList<Box>();
    BoxContainer bottomBoxContainer;
    EmptyBox leftTarget, bottomTarget;
    BoxContainer parentBoxContainer = null;

    public BoxContainer(int x, int y){
        super(x, y, new TextureRegion(new Texture("boxes/empty.png")));
        leftTarget = new EmptyBox((int) this.getX() - 64, (int) this.getY());
        bottomTarget = new EmptyBox((int) this.getX(), (int) this.getY() - 64);
    }

    private void updateBoxes(){
        int boxesLength = (boxes.size()+1) * 64;    //on compte le conteneur libre à gauche
        for(Box box : boxes) {
            int boxIndex = boxes.indexOf(box);
            box.setCoord((int) this.getX() + ((boxIndex+1) * box.width) - boxesLength/2, (int) this.getY());
        }
        leftTarget.setCoord((int) this.getX() - boxesLength / 2, (int) this.getY());
    }

    public void addBox(Box newBox){
        if(parentPanel != null) {
            parentPanel.stage.addActor(newBox);
            parentPanel.dragAndDrop.addTarget(new InnerBoxTarget(newBox, this));
        }
        this.boxes.add(newBox);
        updateBoxes();
    }

    public void removeBox(Box box){
        box.remove();
        boxes.remove(box);

        if(boxes.size() > 0)
            updateBoxes();
        else {
            leftTarget.remove(); bottomTarget.remove();
            if(bottomBoxContainer != null){
                /*
                plante lamentablement lorsqu'on supprime un BoxContainer ayant un numérateur vide et un dénomniateur non-vide
                 */
                for(Box bottomBox : bottomBoxContainer.boxes){
                    bottomBoxContainer.removeBox(bottomBox);
                }
            }

            this.remove();
        }
    }

    public void addBottom(BoxContainer bc){
        if(parentPanel != null) {
            bc.setParentPanel(parentPanel);
            parentPanel.stage.addActor(bc);
        }
        this.bottomBoxContainer = bc;
        bc.setParentBoxContainer(this);
    }

    public void setParentBoxContainer(BoxContainer parent){parentBoxContainer = parent;}

    public EmptyBox getLeftTarget(){return leftTarget;}
    public EmptyBox getBottomTarget(){return bottomTarget;}
    public BoxContainer getBottomBoxContainer(){return bottomBoxContainer;}
    public BoxContainer getParentBoxContainer(){return parentBoxContainer;}
}
