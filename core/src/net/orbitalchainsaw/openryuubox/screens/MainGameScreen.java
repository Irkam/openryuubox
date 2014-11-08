package net.orbitalchainsaw.openryuubox.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import net.orbitalchainsaw.openryuubox.BoxesBar;
import net.orbitalchainsaw.openryuubox.OpenRyuuBox;
import net.orbitalchainsaw.openryuubox.Panel;
import net.orbitalchainsaw.openryuubox.PanelContainer;
import net.orbitalchainsaw.openryuubox.boxes.BoxContainer;
import net.orbitalchainsaw.openryuubox.boxes.LiteralBox;
import net.orbitalchainsaw.openryuubox.boxes.NumericBox;

/**
 * Created by Jean-Vincent on 13/10/2014.
 */
public class MainGameScreen implements Screen{
    final OpenRyuuBox game;

    private Stage stage;
    private PanelContainer panelContainer;

    DragAndDrop dragAndDrop;

    public MainGameScreen(final OpenRyuuBox game){
        this.game = game;

        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        dragAndDrop = new DragAndDrop();

        this.panelContainer = new PanelContainer(new Panel(stage, dragAndDrop, 0, 64, 800/2, 480),
                new Panel(stage, dragAndDrop, 800/2, 64, 800/2, 480),
                new BoxesBar(stage, dragAndDrop,
                        new LiteralBox(LiteralBox.ALPHA),
                        new NumericBox(2),
                        new NumericBox(-1)));

        BoxContainer firstContainer = new BoxContainer(200, 180);
        firstContainer.addBox(new NumericBox(1));
        this.panelContainer.leftPanel.addContainer(firstContainer);

        BoxContainer secondContainer = new BoxContainer(550, 280);
        secondContainer.addBox(new NumericBox(3));
        this.panelContainer.rightPanel.addContainer(secondContainer);
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
    }
}
