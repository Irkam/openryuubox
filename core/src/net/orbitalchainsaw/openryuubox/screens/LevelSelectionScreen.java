package net.orbitalchainsaw.openryuubox.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.orbitalchainsaw.openryuubox.OpenRyuuBox;

/**
 * Created by Dalal.Z on 12/11/2014.
 */
public class LevelSelectionScreen implements Screen{
    String joueur;
    final OpenRyuuBox game;
    private Stage stage = new Stage();
    private Table table = new Table();
    private Skin skin = new Skin(Gdx.files.internal("mainscreenui/uiskin.json"));
    private TextButton niveau1 = new TextButton("niveau 1", skin);
    private TextButton niveau2 = new TextButton("niveau 2", skin);
    private OrthographicCamera camera;
    private Texture background;

    public LevelSelectionScreen(OpenRyuuBox game, String joueur){
        this.joueur = joueur;
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        niveau1.setPosition(400, 300);
        niveau2.setPosition(10, 300);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);


        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        table.add(niveau1).size(100, 60).padBottom(10).row();
        table.add(niveau2).size(100, 60).padBottom(10).row();
        background = new Texture(Gdx.files.internal("boxes/niveaux.png"));

        niveau1.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainGameScreen(game, joueur, 1));
                dispose();
            }
        });

        niveau2.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainGameScreen(game, joueur, 2));
                dispose();
            }
        });
        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
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

    }
}
