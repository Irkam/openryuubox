package net.orbitalchainsaw.openryuubox.draganddrop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import net.orbitalchainsaw.openryuubox.boxes.Box;
import net.orbitalchainsaw.openryuubox.boxes.BoxContainer;
import net.orbitalchainsaw.openryuubox.boxes.LiteralBox;
import net.orbitalchainsaw.openryuubox.boxes.NumericBox;

/**
 * Created by jivay on 05/11/14.
 */
public class BottomBoxTarget extends DragAndDrop.Target{
    Box innerBox;
    BoxContainer boxContainer;

    public BottomBoxTarget(Box box, BoxContainer container) {
        super(box);
        innerBox = box;
        boxContainer = container;
    }

    @Override
    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        getActor().setColor(Color.GREEN);
        return true;
    }

    public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
        getActor().setColor(Color.WHITE);
    }

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
