package net.orbitalchainsaw.openryuubox.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import net.orbitalchainsaw.openryuubox.OpenRyuuBox;
import net.orbitalchainsaw.openryuubox.boxes.Box;
import net.orbitalchainsaw.openryuubox.boxes.BoxesVector;

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

    private Stage stage;
    private BoxesVector boxesVector;

    public MainGameScreen(final OpenRyuuBox game){
        this.game = game;

        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        this.boxesVector = new BoxesVector();
        for(int i = 0; i < 12; i++){
            this.boxesVector.add(new Box(i * 64, i * 64));
            this.boxesVector.get(i).setTouchable(Touchable.enabled);
            this.stage.addActor(this.boxesVector.get(i));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.stage.act(Gdx.graphics.getDeltaTime());
        this.stage.draw();

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
        //img.dispose();
    }
}
