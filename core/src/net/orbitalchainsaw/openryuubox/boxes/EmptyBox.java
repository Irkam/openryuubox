package net.orbitalchainsaw.openryuubox.boxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Boîte vide, uniquement pour de l'affichage ou être utilisée pour le glisser-déposer.
 * @author Jean-Vincent
 *
 */
public class EmptyBox extends Box{
    public EmptyBox(int x, int y){
        super(x, y);
        updateTextureByValue();
    }

	@Override
	public void updateTextureByValue() {
		region = new TextureRegion(new Texture("boxes/empty.png"));
	}
}
