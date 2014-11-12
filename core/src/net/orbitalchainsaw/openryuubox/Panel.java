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
 * Created by jivay on 16/10/14.
 */
public class Panel{
    protected int x, y, width, height;
    protected Actor panelTarget;
    public Stage stage;
    public DragAndDrop dragAndDrop;
    public ArrayList<BoxContainer> boxContainers, rootBoxContainers;
    public ArrayList<DragAndDrop.Target> boxDaDTargets;
    public PanelContainer parent;

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

    public boolean isInBounds(int x, int y, int width, int height){
        return(x > this.x && x + width < this.width && y > this.y && y + height < this.height);
    }

    public boolean hasTheBox(){
        for(BoxContainer boxContainer : boxContainers){
            if(boxContainer.hasTheBox())
                return true;
        }
        return false;
    }

    public boolean gameOver(){
        for(BoxContainer boxContainer : boxContainers){
            if(boxContainer.gameOver())
                return true;
        }
        return false;
    }
}
