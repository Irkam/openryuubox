package net.orbitalchainsaw.openryuubox.boxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Jean-Vincent on 08/11/2014.
 */
public class EmptyBox extends Box{
    public EmptyBox(int x, int y){
        super(x, y, new TextureRegion(new Texture("boxes/empty.png")));
    }
}
