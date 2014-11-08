package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import net.orbitalchainsaw.openryuubox.boxes.Box;

/**
 * Created by jivay on 16/10/14.
 */
public class PanelContainer {
    public Panel leftPanel;
    public Panel rightPanel;
    public BoxesBar boxesBar;

    public PanelContainer(Stage stage, DragAndDrop dragAndDrop, int leftX, int leftY, int leftW, int leftH,
                          int rightX, int rightY, int rightW, int rightH, Box... boxes){
        this.leftPanel = new Panel(stage, dragAndDrop, leftX, leftY, leftW, leftH);
        this.rightPanel = new Panel(stage, dragAndDrop, rightX, rightY, rightW, rightH);
        this.boxesBar = new BoxesBar(stage, dragAndDrop, boxes);
    }

    public PanelContainer(Panel leftPanel, Panel rightPanel, BoxesBar boxesBar){
        this.leftPanel = leftPanel;
        this.rightPanel = rightPanel;
        this.boxesBar = boxesBar;
    }

    public PanelContainer(){
        this(null, null, 0, 64, 800/2, 480, 800/2, 64, 800/2, 480);
    }

    public PanelContainer(Box ... boxes){
        this(null, null, 0, 64, 800/2, 480, 800/2, 64, 800/2, 480, boxes);
    }

    public PanelContainer(Stage stage, DragAndDrop dragAndDrop, Box ... boxes){
        this(stage, dragAndDrop, 0, 64, 800/2, 480, 800/2, 64, 800/2, 480, boxes);
    }
}
