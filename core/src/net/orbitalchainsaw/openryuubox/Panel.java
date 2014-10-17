package net.orbitalchainsaw.openryuubox;

import net.orbitalchainsaw.openryuubox.boxes.Box;

import java.util.ArrayList;

/**
 * Created by jivay on 16/10/14.
 */
public class Panel extends ArrayList<Box>{
    protected int x, y;
    int width, height;

    public Panel(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Panel(int x, int y, int width, int height, ArrayList<Box> boxes){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        for(Box box : boxes){
            this.add(box);
        }
    }

    public void addBoxes(Box ... boxes){
        for(Box box : boxes){
            this.add(box);
        }
    }

    public boolean isInBounds(int x, int y, int width, int height){
        return(x > this.x && x + width < this.width && y > this.y && y + height < this.height);
    }

    public boolean isInBounds(Box box){
        return isInBounds(box.x, box.y, box.width, box.height);
    }

    public Box getBox(int i){return this.get(i);}
}
