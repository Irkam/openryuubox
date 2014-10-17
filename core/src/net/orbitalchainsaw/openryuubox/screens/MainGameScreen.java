package net.orbitalchainsaw.openryuubox.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import net.orbitalchainsaw.openryuubox.OpenRyuuBox;
import net.orbitalchainsaw.openryuubox.PanelContainer;
import net.orbitalchainsaw.openryuubox.boxes.LitteralBox;

/**
 * Created by Jean-Vincent on 13/10/2014.
 */
public class MainGameScreen implements Screen{
    final OpenRyuuBox game;

    private Stage stage;
    private PanelContainer panelContainer;

    public MainGameScreen(final OpenRyuuBox game){
        this.game = game;

        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        this.panelContainer = new PanelContainer();
        for(int i = 0; i < 12; i++){
            LitteralBox newBox = new LitteralBox(i * 64, i * 128);
            newBox.setParentPanel(this.panelContainer.leftPanel);
            newBox.setTouchable(Touchable.enabled);
            this.panelContainer.leftPanel.add(newBox);
            this.stage.addActor(this.panelContainer.leftPanel.getBox(i));
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
    }
}
