package net.orbitalchainsaw.openryuubox.draganddrop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

import net.orbitalchainsaw.openryuubox.boxes.Box;
import net.orbitalchainsaw.openryuubox.boxes.BoxContainer;
import net.orbitalchainsaw.openryuubox.boxes.NumericBox;

/**
 * Created by jivay on 05/11/14.
 */
public class InnerBoxTarget extends Target{
    Box innerBox;
    BoxContainer boxContainer;

    public InnerBoxTarget(Box box, BoxContainer container) {
        super(box);
        innerBox = box;
        boxContainer = container;
    }

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

    @Override
    public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
        getActor().setColor(Color.WHITE);
    }

    @Override
    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        if(!((NumericBox) innerBox).mergeBoxes((NumericBox) payload.getObject()))
            boxContainer.removeBox(innerBox);
    }
}
