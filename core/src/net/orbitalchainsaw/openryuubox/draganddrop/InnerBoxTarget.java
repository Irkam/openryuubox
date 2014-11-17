package net.orbitalchainsaw.openryuubox.draganddrop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

import net.orbitalchainsaw.openryuubox.boxes.Box;
import net.orbitalchainsaw.openryuubox.boxes.BoxContainer;
import net.orbitalchainsaw.openryuubox.boxes.NumericBox;

/**
 * 
 * @author Jean-Vincent
 *
 */
public class InnerBoxTarget extends Target{
    Box innerBox;
    BoxContainer boxContainer;

    public InnerBoxTarget(Box box, BoxContainer container) {
        super(box);
        innerBox = box;
        boxContainer = container;
    }

    /**
     * Appelée lorsque le glisser-déposer survole la cible. Autorise uniquement le dépôt de NumericBox
     * @return true si cette cible est valide et qu'on peut y déposer l'objet. false sinon.
     */
    @Override
    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        Box payloadBox = (Box) payload.getObject();
        if(innerBox.type == Box.NUMERIC && payloadBox.type == Box.NUMERIC) {
            getActor().setColor(Color.GREEN);
            return true;
        }
        getActor().setColor(Color.RED);
        return false;
    }

    /**
     * le glisser-déposer ne survole plus la cible.
     */
    @Override
    public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
        getActor().setColor(Color.WHITE);
    }

    /**
     * le draganddrop largue l'objet sur la cible. La copie de l'objet se fait ici. Si la copie ne se fait pas, la source est supprimée.
     * @param source source de l'objet transporté
     * @param payload objet transporté
     */
    @Override
    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        if(!((NumericBox) innerBox).mergeBoxes((NumericBox) payload.getObject()))
            boxContainer.removeBox(innerBox);
        else
            boxContainer.simplify();

        boxContainer.parentPanel.parent.gameOver();
    }
}
