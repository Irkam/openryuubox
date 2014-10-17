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
    public int x, y;
    public int width = 64, height = 64;
    protected TextureRegion region;
    protected Panel parentPanel = null;

    protected class BoxInputListener extends InputListener{
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
            Box box = ((Box)event.getTarget());
            box.x += (int) x - box.width/2;
            box.y += (int) y - box.height/2;
            box.setBounds(box.x, box.y, box.width, box.height);
            return true;
        }
    }

    public Box(){
        this.x = 0;
        this.y = 0;
        this.region = new TextureRegion(new Texture("boxes/default.png"));
        setBounds(this.x, this.y, this.width, this.height);
        addListener(new BoxInputListener());
    }

    public Box(int x, int y){
        this.x = x;
        this.y = y;
        this.region = new TextureRegion(new Texture("boxes/default.png"));
        setBounds(this.x, this.y, this.width, this.height);
        addListener(new BoxInputListener());
    }

    public Box(int x, int y, TextureRegion region){
        this.x = x;
        this.y = y;
        this.region = region;
        setBounds(this.x, this.y, this.width, this.height);
        addListener(new BoxInputListener());
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a*parentAlpha);
        batch.draw(region, this.x, this.y);
    }

    public void setParentPanel(Panel panel){this.parentPanel = panel;}
    public Panel getParentPanel(){return this.parentPanel;}
}
