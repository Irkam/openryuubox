package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.awt.Rectangle;

/**
 * Created by Jean-Vincent on 13/10/2014.
 */
public class MainGameScreen implements Screen{
    final OpenRyuuBox game;

    private OrthographicCamera camera;
    private Rectangle rekt;
    Texture img;
    Vector3 touchpos;

    public MainGameScreen(final OpenRyuuBox game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        game.batch = new SpriteBatch();

        img = new Texture("badlogic.jpg");

        rekt = new Rectangle();
        rekt.x = 800/2 - 64/2;
        rekt.y = 20;
        rekt.width = 64;
        rekt.height = 64;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(img, rekt.x, rekt.y);
        game.batch.end();

        if(Gdx.input.isTouched()){
            touchpos = new Vector3();
            touchpos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchpos);
            rekt.x = (int)touchpos.x - 64/2;
            if(rekt.x < 0) rekt.x = 0;
            if(rekt.x > 800 - 64) rekt.x = 800 - 64;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        img.dispose();
    }
}
