package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;

import net.orbitalchainsaw.openryuubox.boxes.Box;
import net.orbitalchainsaw.openryuubox.boxes.LiteralBox;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by jivay on 22/10/14.
 */
public class BoxesBar extends ArrayList<Box> {
    protected int x, y;
    int width, height;
    Stage stage;
    DragAndDrop dragAndDrop;

    public BoxesBar(Stage stage, DragAndDrop dragAndDrop,
                    int x, int y, int width, int height, Box ... boxes){
        this.stage = stage;
        this.dragAndDrop = dragAndDrop;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        for(Box box : boxes) {
            this.add(box);
            this.stage.addActor(box);
        }
    }

    public BoxesBar(){
        this(null, null, 0, 0, 800, 64);
    }

    public BoxesBar(Box ... boxes){
        this(null, null, 0, 0, 800, 64, boxes);
    }

    public BoxesBar(Stage stage, DragAndDrop dragAndDrop, Box ... boxes){
        this(stage, dragAndDrop, 0, 0, 800, 64, boxes);
    }

    public int getBoxX(int i){return this.width / (1+this.size()) - 64/2 + this.x + i * 64;}
    public int getBoxY(int i){return this.height / (1+this.size()) - 64/2 + this.y + i * 64;}


    @Override
    public boolean add(final Box newBox){
        this.stage.addActor(newBox);
        this.dragAndDrop.addSource(new BoxSource(newBox));

        super.add(newBox);

        int i=0;
        for(Box box : this){
            box.setCoord(getBoxX(i++), 0);
        }

        return true;
    }
}
