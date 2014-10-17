package net.orbitalchainsaw.openryuubox;

/**
 * Created by jivay on 16/10/14.
 */
public class PanelContainer {
    public Panel leftPanel;
    public Panel rightPanel;

    public PanelContainer(){
        this.leftPanel = new Panel(0, 0, 800/2, 480/2);
        this.rightPanel = new Panel(800/2, 0, 800/2, 480/2);
    }
}
