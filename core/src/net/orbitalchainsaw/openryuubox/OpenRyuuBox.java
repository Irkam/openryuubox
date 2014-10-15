package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.orbitalchainsaw.openryuubox.screens.MenuScreen;

public class OpenRyuuBox extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();

        this.setScreen(new MenuScreen(this));
	}


	public void render () {
        super.render();
	}

    public void dispose(){
        batch.dispose();
        font.dispose();
    }
}
