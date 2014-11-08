package net.orbitalchainsaw.openryuubox.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.orbitalchainsaw.openryuubox.GamePreferences;
import net.orbitalchainsaw.openryuubox.OpenRyuuBox;

/**
 * Created by Romain on 05/11/2014.
 */
public class OptionsScreen implements Screen {
    final OpenRyuuBox game;
    OrthographicCamera camera;

    Skin skinLibgdx = new Skin(Gdx.files.internal("mainscreenui/uiskin.json"));


    CheckBox chkSound = new CheckBox("Son", skinLibgdx);
    TextButton save = new TextButton("Sauvegarder", skinLibgdx);
    TextButton quit = new TextButton("Quitter", skinLibgdx);


    private Stage stage = new Stage();
    private Table table = new Table();

    public OptionsScreen(final OpenRyuuBox game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    private void saveSettings(){
        GamePreferences prefs = GamePreferences.instance;
        prefs.sound = chkSound.isChecked();
    }

    private void loadSettings(){
        GamePreferences prefs = GamePreferences.instance;
        prefs.load();
        chkSound.setChecked(prefs.sound);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Options", 100, 150);
        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height) {

    }

    public void show() {
        table.add(chkSound).size(40,40).padBottom(10).row();
        table.add(save).size(150,60).padTop(50).row();
        table.add(quit).size(150,60).padBottom(50).row();


        save.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                saveSettings();
                onQuitClicked();
            }
        });

        quit.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
            onQuitClicked();
            }
        });



        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    public void onQuitClicked(){
        loadSettings();
        game.setScreen(new MenuScreen(game));
        dispose();
    }

    public void hide() {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}
