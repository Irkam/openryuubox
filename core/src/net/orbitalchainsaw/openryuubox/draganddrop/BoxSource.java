package net.orbitalchainsaw.openryuubox.draganddrop;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;

import net.orbitalchainsaw.openryuubox.boxes.Box;
import net.orbitalchainsaw.openryuubox.boxes.LiteralBox;
import net.orbitalchainsaw.openryuubox.boxes.NumericBox;
import net.orbitalchainsaw.openryuubox.boxes.UnknownBox;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by jivay on 05/11/14.
 */
public class BoxSource extends Source {
    Box box;

    public BoxSource(Box box) {
        super(box);
        this.box = box;

        /*
        type = box.type;

        if(type == Box.NUMERIC)
            value = ((NumericBox) box).value;

        if(type == Box.LITERAL)
            name = ((LiteralBox) box).name;
            */

    }

    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        Payload payload = new Payload();
        payload.setObject(getActor());

        Box validBox, invalidBox;

        if(box.type == Box.LITERAL){
            payload.setDragActor(new LiteralBox(((LiteralBox)box).value));

            validBox = new LiteralBox(((LiteralBox)box).value);
            validBox.setColor(0, 1, 0, 1);
            payload.setValidDragActor(validBox);

            invalidBox = new LiteralBox(((LiteralBox)box).value);
            invalidBox.setColor(1, 0, 0, 1);
            payload.setInvalidDragActor(invalidBox);
        }else{
            payload.setDragActor(new NumericBox(((NumericBox)box).value));

            validBox = new NumericBox(((NumericBox)box).value);
            validBox.setColor(0, 1, 0, 1);
            payload.setValidDragActor(validBox);

            invalidBox = new NumericBox(((NumericBox)box).value);
            invalidBox.setColor(1, 0, 0, 1);
            payload.setInvalidDragActor(invalidBox);
        }

        return payload;
    }
}
