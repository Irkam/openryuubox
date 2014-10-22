package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

import net.orbitalchainsaw.openryuubox.boxes.Box;
import net.orbitalchainsaw.openryuubox.boxes.LiteralBox;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by jivay on 22/10/14.
 */
public class BoxesBar extends ArrayList<Box> {
    protected int x, y;
    int width, height;
    Stage stage;
    DragAndDrop dragAndDrop;
    final Skin skin = new Skin();

    public BoxesBar(Stage stage, DragAndDrop dragAndDrop,
                    int x, int y, int width, int height, Box ... boxes){
        this.stage = stage;
        this.dragAndDrop = dragAndDrop;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        skin.add("empty", new Texture("boxes/empty.png"));


        Image validTargetImage = new Image(skin, "empty");
        validTargetImage.setBounds(200, 50, 64, 64);
        stage.addActor(validTargetImage);
        Image invalidTargetImage = new Image(skin, "empty");
        invalidTargetImage.setBounds(200, 200, 64, 64);
        stage.addActor(invalidTargetImage);

        dragAndDrop.addTarget(new Target(validTargetImage) {
            public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
                getActor().setColor(Color.GREEN);
                return true;
            }
            public void reset (Source source, Payload payload) {
                getActor().setColor(Color.WHITE);
            }
            public void drop (Source source, Payload payload, float x, float y, int pointer) {
                System.out.println("Accepted: " + payload.getObject() + " " + x + ", " + y);
            }
        });
        dragAndDrop.addTarget(new Target(invalidTargetImage) {
            public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
                getActor().setColor(Color.RED);
                return false;
            }
            public void reset (Source source, Payload payload) {
                getActor().setColor(Color.WHITE);
            }
            public void drop (Source source, Payload payload, float x, float y, int pointer) {
            }
        });

        for(Box box : boxes) {
            this.add(box);
            this.stage.addActor(box);
        }
    }

    public BoxesBar(){
        this(null, null, 0, 0, 800, 64);
    }

    public BoxesBar(Box ... boxes){
        this(null, null, 0, 0, 800, 64, boxes);
    }

    public BoxesBar(Stage stage, DragAndDrop dragAndDrop, Box ... boxes){
        this(stage, dragAndDrop, 0, 0, 800, 64, boxes);
    }

    public int getBoxX(int i){return this.width / (1+this.size()) - 64/2 + this.x + i * 64;}
    public int getBoxY(int i){return this.height / (1+this.size()) - 64/2 + this.y + i * 64;}


    @Override
    public boolean add(final Box newBox){
        this.stage.addActor(newBox);
        this.dragAndDrop.addSource(new DragAndDrop.Source(newBox) {
            Box boxActor = newBox;
            @Override
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                Payload payload = new Payload();
                payload.setObject(getActor());

                try {
                    boxActor = (Box) getActor().getClass().getConstructor(String.class).newInstance("omega");
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

                payload.setDragActor(boxActor);

                Label validLabel = new Label("Some payload!", skin);
                validLabel.setColor(0, 1, 0, 1);
                payload.setValidDragActor(validLabel);

                Label invalidLabel = new Label("Some payload!", skin);
                invalidLabel.setColor(1, 0, 0, 1);
                payload.setInvalidDragActor(invalidLabel);

                return payload;
            }
        });

        super.add(newBox);

        int i=0;
        for(Box box : this){
            box.setCoord(getBoxX(i++), 0);
        }

        return true;
    }
}