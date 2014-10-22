package net.orbitalchainsaw.openryuubox.boxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by jivay on 22/10/14.
 */
public class BoxContainer extends Box {
    Box box;

    public BoxContainer(Box box, int x, int y){
        super(x, y, new TextureRegion(new Texture("boxes/empty.png")));
        this.box = box;
    }
}
