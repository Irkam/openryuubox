package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;

import net.orbitalchainsaw.openryuubox.boxes.Box;
import net.orbitalchainsaw.openryuubox.boxes.LiteralBox;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by jivay on 05/11/14.
 */
public class BoxSource extends DragAndDrop.Source {
    public BoxSource(Actor actor) {
        super(actor);
    }

    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        Payload payload = new Payload();
        payload.setObject(getActor());

        try {
            payload.setDragActor((Box) getActor().getClass().getConstructor(String.class).newInstance("omega"));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        LiteralBox validLabel = new LiteralBox("delta");
        validLabel.setColor(0, 1, 0, 1);
        payload.setValidDragActor(validLabel);

        LiteralBox invalidLabel = new LiteralBox("delta");
        invalidLabel.setColor(1, 0, 0, 1);
        payload.setInvalidDragActor(invalidLabel);

        return payload;
    }
}
