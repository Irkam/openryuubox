package net.orbitalchainsaw.openryuubox.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import net.orbitalchainsaw.openryuubox.BoxesBar;
import net.orbitalchainsaw.openryuubox.JSONLevelGenerator;
import net.orbitalchainsaw.openryuubox.OpenRyuuBox;
import net.orbitalchainsaw.openryuubox.Panel;
import net.orbitalchainsaw.openryuubox.PanelContainer;
import net.orbitalchainsaw.openryuubox.boxes.BoxContainer;
import net.orbitalchainsaw.openryuubox.boxes.LiteralBox;
import net.orbitalchainsaw.openryuubox.boxes.NumericBox;
import net.orbitalchainsaw.openryuubox.boxes.UnknownBox;

import javafx.scene.input.InputEvent;

/**
 * Created by Jean-Vincent on 13/10/2014.
 */
public class MainGameScreen implements Screen{
    final OpenRyuuBox game;

    private Stage stage;
    private PanelContainer panelContainer;
    private Skin skin = new Skin(Gdx.files.internal("mainscreenui/uiskin.json"));
    private Texture background;
    private TextButton back = new TextButton("Retour", skin);
    private Table table = new Table();

    DragAndDrop dragAndDrop;

    public MainGameScreen(final OpenRyuuBox game){
        this.game = game;
        this.stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        dragAndDrop = new DragAndDrop();
        game.font.setColor(Color.BLACK);
        game.font.setScale(2);

        JSONLevelGenerator.parseLevelJSON("levels/level0.json", stage, dragAndDrop);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        back.setPosition(660, 40);
        background = new Texture(Gdx.files.internal("boxes/NIVEAU1.png"));

        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());//corrige sans caméra !!
        game.font.draw(game.batch, "Niveau 1", 35, 85);
        game.batch.end();

        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        table.add(back).size(100,60).padBottom(10).row();

        back.addListener(new ClickListener() {
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
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
