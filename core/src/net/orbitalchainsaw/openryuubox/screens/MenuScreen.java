package net.orbitalchainsaw.openryuubox.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.orbitalchainsaw.openryuubox.OpenRyuuBox;

/**
 * Created by Jean-Vincent on 13/10/2014.
 */
public class MenuScreen implements Screen{
    final OpenRyuuBox game;

    OrthographicCamera camera;
    private Stage stage = new Stage();
    private Table table = new Table();

    private Skin skin = new Skin(Gdx.files.internal("mainscreenui/uiskin.json"));

    private TextButton buttonPlay = new TextButton("Play", skin);
    private TextButton buttonOptions = new TextButton("Options", skin);

    public MenuScreen(final OpenRyuuBox game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "OpenRyuuBox", 100, 150);
        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    private void onPlayClicked () {
        game.setScreen(new MainGameScreen(game));
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        table.add(buttonPlay).size(150,60).padBottom(10).row();
        table.add(buttonOptions).size(150,60).padBottom(20).row();

        buttonPlay.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainGameScreen(game));
                dispose();
            }
        });

        buttonOptions.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
            }
        });


        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
