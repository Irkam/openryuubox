package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import net.orbitalchainsaw.openryuubox.boxes.Box;

/**
 * Conteneur de panneaux délimitant chacun une moitié de la zone de jeu et le dock de Box à déplacer vers les panneaux.
 * @author Jean-Vincent
 *
 */
public class PanelContainer {
    public Panel leftPanel;
    public Panel rightPanel;
    public BoxesBar boxesBar;
    public Stage stage;

    /**
     * Crée un PanelContainer avec de nouveaux Panel et une BoxesBar avec les paramètres renseignés, un Stage et un DragAndDrop pour les trois objets.
     * @param stage Stage pour l'affichage du jeu et des boîtes de dialogue
     * @param dragAndDrop DragAndDrop pour gérer le glisser-déposer des Box.
     * @param leftX Origine X du Panel gauche
     * @param leftY Origine Y du Panel gauche
     * @param leftW Largeur du Panel gauche
     * @param leftH Hauteur du Panel gauche
     * @param rightX Origine X du Panel droit
     * @param rightY Origine Y du Panel droit
     * @param rightW Largeur du Panel droit
     * @param rightH Hauteur du Panel droit
     */
    public PanelContainer(Stage stage, DragAndDrop dragAndDrop, int leftX, int leftY, int leftW, int leftH,
                          int rightX, int rightY, int rightW, int rightH){
        this.leftPanel = new Panel(stage, dragAndDrop, leftX, leftY, leftW, leftH, this);
        this.rightPanel = new Panel(stage, dragAndDrop, rightX, rightY, rightW, rightH, this);
        this.boxesBar = new BoxesBar(stage, dragAndDrop, this);
        this.stage = stage;
    }

    /**
     * Crée un PanelContainer avec des panneaux et un dock déjà existants.
     * @param stage Stage pour l'affichage du jeu et des boîtes de dialogue
     * @param leftPanel
     * @param rightPanel
     * @param boxesBar
     */
    public PanelContainer(Stage stage, Panel leftPanel, Panel rightPanel, BoxesBar boxesBar){
        this.leftPanel = leftPanel;
        this.rightPanel = rightPanel;
        this.boxesBar = boxesBar;
        this.stage = stage;
    }

    /**
     * Callback servant à vérifier si la condition de victoire a été remplie.
     */
    public void gameOver(){
        if(leftPanel.rootBoxContainers.size() == 1)
            if(leftPanel.gameOver())
                displayGameOver();
        if(rightPanel.rootBoxContainers.size() == 1)
            if(rightPanel.gameOver())
                displayGameOver();
    }
    
    /**
     * Affiche le dialogue de fin de partie
     */
    public void displayGameOver(){
        Skin skin = new Skin(Gdx.files.internal("mainscreenui/uiskin.json"));
        Dialog dialog = new Dialog("WE ARE THE CHAMPIONS", skin, "dialog") {}
                .text("A BLOODY WINRAR IS YOU!!!").key(Input.Keys.ENTER, true)
                .key(Input.Keys.ESCAPE, false).show(stage);
    }
}
