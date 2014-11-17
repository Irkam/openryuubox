package net.orbitalchainsaw.openryuubox.draganddrop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import net.orbitalchainsaw.openryuubox.boxes.Box;
import net.orbitalchainsaw.openryuubox.boxes.BoxContainer;
import net.orbitalchainsaw.openryuubox.boxes.LiteralBox;
import net.orbitalchainsaw.openryuubox.boxes.NumericBox;

/**
 * 
 * @author Jean-Vincent
 *
 */
public class BottomBoxTarget extends DragAndDrop.Target{
    Box innerBox;
    BoxContainer boxContainer;

    public BottomBoxTarget(Box box, BoxContainer container) {
        super(box);
        innerBox = box;
        boxContainer = container;
    }
    
    /**
     * Appelée lorsque le glisser-déposer survole la cible
     * @return true si cette cible est valide et qu'on peut y déposer l'objet. false sinon. Ici tout peut être déposé.
     */
    @Override
    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        getActor().setColor(Color.GREEN);
        return true;
    }

    /**
     * le glisser-déposer ne survole plus la cible.
     */
    public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
        getActor().setColor(Color.WHITE);
    }

    /**
     * le draganddrop largue l'objet sur la cible. La copie de l'objet se fait ici. Si la copie ne se fait pas, la source est supprimée.
     * @param source source de l'objet transporté
     * @param payload objet transporté
     */
    @Override
    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        BoxContainer newBottom = new BoxContainer((int) boxContainer.getX(),
                (int) boxContainer.getY() - 64, boxContainer.parentPanel);
        boxContainer.addBottom(newBottom);

        Box payloadBox = (Box) payload.getObject();
        switch(payloadBox.type){
            case Box.LITERAL:
                newBottom.addBox(new LiteralBox(payloadBox.value));
                break;
            case Box.NUMERIC:
                newBottom.addBox(new NumericBox(payloadBox.value));
                break;
            default:
                System.out.println("ajout d'une boite merdeuse");
        }
        if(newBottom.boxes.size() > 0)
            newBottom.getParentPanel().addContainer(newBottom);
    }
}
