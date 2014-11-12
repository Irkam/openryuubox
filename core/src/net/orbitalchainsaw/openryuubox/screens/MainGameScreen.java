package net.orbitalchainsaw.openryuubox.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import net.orbitalchainsaw.openryuubox.BoxesBar;
import net.orbitalchainsaw.openryuubox.OpenRyuuBox;
import net.orbitalchainsaw.openryuubox.Panel;
import net.orbitalchainsaw.openryuubox.PanelContainer;
import net.orbitalchainsaw.openryuubox.boxes.BoxContainer;
import net.orbitalchainsaw.openryuubox.boxes.LiteralBox;
import net.orbitalchainsaw.openryuubox.boxes.NumericBox;
import net.orbitalchainsaw.openryuubox.boxes.UnknownBox;

/**
 * Created by Jean-Vincent on 13/10/2014.
 */
public class MainGameScreen implements Screen{
    final OpenRyuuBox game;
    DragAndDrop dragAndDrop;

    private Stage stage;
    private Texture perso;
    private String joueur;
    private Texture background;
    private Table table = new Table();
    private PanelContainer panelContainer;

    private Skin skin = new Skin(Gdx.files.internal("mainscreenui/uiskin.json"));
    private TextButton back = new TextButton("Retour", skin);


    public MainGameScreen(final OpenRyuuBox game, String joueur){
        this.joueur = joueur;
        this.game = game;
        this.stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        dragAndDrop = new DragAndDrop();
        game.font.setColor(Color.BLACK);
        game.font.setScale(2);

        this.panelContainer = new PanelContainer(new Panel(stage, dragAndDrop, 0, 64, 800/2, 480),
                new Panel(stage, dragAndDrop, 800/2, 64, 800/2, 480),
                new BoxesBar(stage, dragAndDrop,
                        new LiteralBox(LiteralBox.ALPHA),
                        new NumericBox(2),
                        new NumericBox(-1)));

        BoxContainer firstContainer = new BoxContainer(200, 180);
        firstContainer.addBox(new NumericBox(1));
        firstContainer.addBox(new UnknownBox());
        this.panelContainer.leftPanel.addContainer(firstContainer);

        BoxContainer secondContainer = new BoxContainer(550, 280);
        secondContainer.addBox(new NumericBox(3));
        this.panelContainer.rightPanel.addContainer(secondContainer);

        BoxContainer thirdContainer = new BoxContainer(700, 380);
        thirdContainer.addBox(new NumericBox(4));
        this.panelContainer.rightPanel.addContainer(thirdContainer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        back.setPosition(660, 40);

        background = new Texture(Gdx.files.internal("boxes/NIVEAU1.png"));
        perso = new Texture(Gdx.files.internal("boxes/"+joueur+".png"));

        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(perso, 30, 40, 128, 128);
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