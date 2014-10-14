package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.awt.Rectangle;

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
