package net.orbitalchainsaw.openryuubox.boxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Jean-Vincent on 14/10/2014.
 */
public class UnknownBox extends Box{
    public UnknownBox(){
        super();
        type = Box.UNKNOWN;
        region = new TextureRegion(new Texture("boxes/default.png"));
    }
}
