package net.orbitalchainsaw.openryuubox.boxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Boîte de valeur inconnue, à isoler pour en connaître la valeur. Correspond dans le jeu original à la DragonBox.
 * @author Jean-Vincent
 *
 */
public class UnknownBox extends Box{
    public UnknownBox(){
        super();
        type = Box.UNKNOWN;
        updateTextureByValue();
    }

	@Override
	public void updateTextureByValue() {
		region = new TextureRegion(new Texture("boxes/default.png"));
	}
}
