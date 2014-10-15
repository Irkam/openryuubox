package net.orbitalchainsaw.openryuubox.boxes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.awt.Rectangle;

/**
 * Created by Jean-Vincent on 13/10/2014.
 */
public class Box extends Actor{
    protected Rectangle rectangle;
    protected TextureRegion region;

    protected class BoxInputListener extends InputListener{
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
            ((Box)event.getTarget()).rectangle.x = (int) x;
            ((Box)event.getTarget()).rectangle.y = (int) y;
            return true;
        }
    }

    public Box(){
        this.rectangle = new Rectangle(64, 64);
        this.region = new TextureRegion(new Texture("boxes/default.png"));
        setBounds(this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height);
        addListener(new BoxInputListener());
    }

    public Box(int x, int y){
        this.rectangle = new Rectangle(x, y, 64, 64);
        this.region = new TextureRegion(new Texture("boxes/default.png"));
        setBounds(this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height);
        addListener(new BoxInputListener());
    }

    public Box(Rectangle rectangle, TextureRegion region){
        this.rectangle = rectangle;
        this.region = region;
        setBounds(this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height);
        addListener(new BoxInputListener());
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a*parentAlpha);
        batch.draw(region, this.rectangle.x, this.rectangle.y);
    }
}
