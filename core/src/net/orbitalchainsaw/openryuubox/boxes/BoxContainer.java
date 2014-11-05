package net.orbitalchainsaw.openryuubox.boxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import net.orbitalchainsaw.openryuubox.Panel;

import java.util.ArrayList;

/**
 * Created by jivay on 22/10/14.
 */
public class BoxContainer extends Box {
    public ArrayList<Box> boxes = new ArrayList<Box>();
    BoxContainer bottomBoxContainer;
    Panel parentPanel;

    public BoxContainer(int x, int y){
        super(x, y, new TextureRegion(new Texture("boxes/empty.png")));
    }

    public void addBox(Box newbox){
        if(parentPanel != null)
            parentPanel.stage.addActor(newbox);
        this.boxes.add(newbox);
        for(Box box : boxes) {
            int boxIndex = boxes.indexOf(box);
            box.setCoord((int) this.getX() * (boxIndex+1), (int) this.getY());
        }
    }

    public void addBottom(BoxContainer bc){
        this.bottomBoxContainer = bc;
    }

    public void setParentPanel(Panel panel){
        parentPanel = panel;
    }
}
