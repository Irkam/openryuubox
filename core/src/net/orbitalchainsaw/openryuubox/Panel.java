package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import net.orbitalchainsaw.openryuubox.boxes.Box;

import java.util.ArrayList;

/**
 * Created by jivay on 16/10/14.
 */
public class Panel extends ArrayList<Box>{
    protected int x, y;
    int width, height;
    protected Stage stage;
    protected DragAndDrop dragAndDrop;

    public Panel(Stage stage, DragAndDrop dragAndDrop,
                 int x, int y, int width, int height){
        this.stage = stage;
        this.dragAndDrop = dragAndDrop;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Panel(Stage stage, DragAndDrop dragAndDrop,
                 int x, int y, int width, int height, ArrayList<Box> boxes){
        this(stage, dragAndDrop, x, y, width, height);

        for(Box box : boxes){
            this.add(box);
        }
    }

    public boolean isInBounds(int x, int y, int width, int height){
        return(x > this.x && x + width < this.width && y > this.y && y + height < this.height);
    }

    public boolean isInBounds(Box box){
        return isInBounds((int) box.getX(), (int) box.getY(), box.width, box.height);
    }

    public Box getBox(int i){return this.get(i);}

    @Override
    public boolean add(Box box){
        this.stage.addActor(box);

        return super.add(box);
    }
}
