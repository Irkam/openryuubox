package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import net.orbitalchainsaw.openryuubox.boxes.Box;
import net.orbitalchainsaw.openryuubox.draganddrop.BottomBoxTarget;
import net.orbitalchainsaw.openryuubox.draganddrop.BoxSource;
import net.orbitalchainsaw.openryuubox.draganddrop.LeftBoxTarget;

import java.util.ArrayList;

/**
 * Created by jivay on 22/10/14.
 */
public class BoxesBar extends ArrayList<Box> {
    protected int x, y;
    int width, height;
    Stage stage;
    DragAndDrop dragAndDrop;
    PanelContainer parent;
    public ArrayList<DragAndDrop.Target> blockingTargets;
    public Box authorizedBox = null;

    public BoxesBar(Stage stage, DragAndDrop dragAndDrop, PanelContainer parent,
                    int x, int y, int width, int height, Box ... boxes){
        this.stage = stage;
        this.dragAndDrop = dragAndDrop;
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        blockingTargets = new ArrayList<DragAndDrop.Target>();

        for(Box box : boxes) {
            this.add(box);
            this.stage.addActor(box);
        }
    }

    public BoxesBar(Stage stage, DragAndDrop dragAndDrop, PanelContainer parent){
        this(stage, dragAndDrop, parent, 0, 0, 800, 64);
    }

    public int getBoxX(int i){return this.width / (1+this.size()) - 64/2 + this.x + i * 64;}
    public int getBoxY(int i){return 32;}


    @Override
    public boolean add(final Box newBox){
        this.stage.addActor(newBox);
        this.dragAndDrop.addSource(new BoxSource(newBox, this));

        super.add(newBox);

        int i=0;
        for(Box box : this){
            box.setCoord(getBoxX(i), getBoxY(i));
            ++i;
        }

        return true;
    }

    public Box getAuthorizedBox(){return authorizedBox;}
    public void setAuthorizedBox(Box box){this.authorizedBox = box;}

    public void setBlockingTargets(DragAndDrop.Target target){
        for(DragAndDrop.Target t : parent.leftPanel.boxDaDTargets){
            if(t instanceof BottomBoxTarget && target instanceof  BottomBoxTarget)
                blockingTargets.add(t);
            if(t instanceof LeftBoxTarget && target instanceof LeftBoxTarget)
                blockingTargets.add(t);
        }
    }

    public void notifyBoxAddedToTarget(DragAndDrop.Target target){
        blockingTargets.remove(target);


        if(blockingTargets.size() == 0)
            setAuthorizedBox(null);
    }
}
