package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import net.orbitalchainsaw.openryuubox.boxes.Box;

/**
 * Created by jivay on 16/10/14.
 */
public class PanelContainer {
    public Panel leftPanel;
    public Panel rightPanel;
    public BoxesBar boxesBar;
    Stage stage;

    public PanelContainer(Stage stage, DragAndDrop dragAndDrop, int leftX, int leftY, int leftW, int leftH,
                          int rightX, int rightY, int rightW, int rightH){
        this.leftPanel = new Panel(stage, dragAndDrop, leftX, leftY, leftW, leftH, this);
        this.rightPanel = new Panel(stage, dragAndDrop, rightX, rightY, rightW, rightH, this);
        this.boxesBar = new BoxesBar(stage, dragAndDrop, this);
        this.stage = stage;
    }

    public PanelContainer(Panel leftPanel, Panel rightPanel, BoxesBar boxesBar){
        this.leftPanel = leftPanel;
        this.rightPanel = rightPanel;
        this.boxesBar = boxesBar;
    }

    public void gameOver(){
        if(leftPanel.rootBoxContainers.size() == 1)
            if(leftPanel.gameOver())
                displayGameOver();
        if(rightPanel.rootBoxContainers.size() == 1)
            if(rightPanel.gameOver())
                displayGameOver();
    }

    public void displayGameOver(){
        Skin skin = new Skin(Gdx.files.internal("mainscreenui/uiskin.json"));
        Dialog dialog = new Dialog("WE ARE THE CHAMPIONS", skin, "dialog") {}
                .text("A BLOODY WINRAR IS YOU!!!").key(Input.Keys.ENTER, true)
                .key(Input.Keys.ESCAPE, false).show(stage);
    }
}
