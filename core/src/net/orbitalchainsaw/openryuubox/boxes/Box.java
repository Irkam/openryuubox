package net.orbitalchainsaw.openryuubox.boxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.Rectangle;

/**
 * Created by Jean-Vincent on 13/10/2014.
 */
public class Box extends Actor{
    protected Rectangle rectangle;
    protected Texture texture;

    public Box(){

    }

    public Box(Rectangle rectangle, Texture texture){
        this.rectangle = rectangle;
        this.texture = texture;
    }
}
