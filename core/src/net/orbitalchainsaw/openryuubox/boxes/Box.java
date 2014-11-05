package net.orbitalchainsaw.openryuubox.boxes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import net.orbitalchainsaw.openryuubox.Panel;

import java.awt.Rectangle;

/**
 * Created by Jean-Vincent on 13/10/2014.
 */
public abstract class Box extends Actor{
    public static final int UNKNOWN = 0;
    public static final int LITERAL = 1;
    public static final int NUMERIC = 2;

    public int type;
    public int width = 64, height = 64;
    protected TextureRegion region;
    protected Panel parentPanel = null;
<<<<<<< HEAD
    protected class BoxInputListener extends InputListener{
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
            Box box = ((Box)event.getTarget());
            box.x += (int) x - box.width/2;
            box.y += (int) y - box.height/2;
            box.setBounds(box.x, box.y, box.width, box.height);
            return true;
        }
    }
=======
>>>>>>> origin/master

    public Box(){
        this.region = new TextureRegion(new Texture("boxes/default.png"));
        setBounds(0, 0, this.width, this.height);
    }

    public Box(int x, int y){
        this.region = new TextureRegion(new Texture("boxes/default.png"));
        setBounds(x, y, this.width, this.height);
    }

    public Box(int x, int y, TextureRegion region){
        this.region = region;
        setBounds(x, y, this.width, this.height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a*parentAlpha);
        batch.draw(region, getX(), getY());
    }

    public void setParentPanel(Panel panel){this.parentPanel = panel;}
    public Panel getParentPanel(){return this.parentPanel;}

    public void setCoord(int x, int y){
        setBounds(x, y, width, height);
    }
}
