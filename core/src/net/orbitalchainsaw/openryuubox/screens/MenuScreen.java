package net.orbitalchainsaw.openryuubox.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private Texture background;
    private Music sound ;

    private Skin skin = new Skin(Gdx.files.internal("mainscreenui/uiskin.json"));

    private TextButton buttonPlay = new TextButton("Play", skin);
    private TextButton buttonOptions = new TextButton("Regles du Jeu", skin);

    public MenuScreen(final OpenRyuuBox game) {
        this.game = game;
        sound = Gdx.audio.newMusic(Gdx.files.internal("sound/Luiz.mp3"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        buttonOptions.setPosition(600, 245);
        buttonPlay.setPosition(50, 245);


        sound.setLooping(true);
        sound.play();
        sound.setVolume(0.8f);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);


        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());//corrige sans caméra !!
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
        table.add(buttonPlay).size(100,60).padBottom(10).row();
        table.add(buttonOptions).size(100,60).padBottom(10).row();

        background = new Texture(Gdx.files.internal("boxes/fondDebut.jpg"));


        buttonPlay.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainGameScreen(game));
                dispose();
            }
        });


        buttonOptions.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new OptionsScreen(game));
                dispose();
            }
        });


        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        stage.dispose();
        sound.dispose();
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
