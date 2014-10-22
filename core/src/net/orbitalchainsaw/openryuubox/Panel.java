package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import net.orbitalchainsaw.openryuubox.boxes.Box;
import net.orbitalchainsaw.openryuubox.boxes.BoxContainer;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by jivay on 16/10/14.
 */
public class Panel extends ArrayList<Box>{
    protected int x, y;
    int width, height;
    protected Stage stage;
    protected DragAndDrop dragAndDrop;
    protected Actor panelTarget;
    protected ArrayList<BoxContainer> boxContainers;

    public Panel(final Stage stage, final DragAndDrop dragAndDrop,
                 int x, int y, int width, int height){
        this.stage = stage;
        this.dragAndDrop = dragAndDrop;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        panelTarget = new Actor();
        panelTarget.setBounds(x, y, width, height);
        stage.addActor(panelTarget);

        /*
        TODO: ajouter les actions drag&drop pour ajouter les Target à côté de chaque Box
         */
        boxContainers = new ArrayList<BoxContainer>();
        dragAndDrop.addTarget(new DragAndDrop.Target(panelTarget) {
            public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                Panel parentPanel;

                try{
                    parentPanel = (Panel) getClass().getDeclaredField("this$0").get(this);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    return false;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return false;
                }

                for(Box box : parentPanel){
                    BoxContainer leftContainer = new BoxContainer(null, (int) box.getX() - 64, (int) box.getY());
                    BoxContainer bottomContainer = new BoxContainer(null, (int) box.getX(), (int) box.getY() - 64);

                    boxContainers.add(leftContainer);
                    boxContainers.add(bottomContainer);
                    stage.addActor(leftContainer);
                    stage.addActor(bottomContainer);
                    dragAndDrop.addTarget(new DragAndDrop.Target(box) {
                        public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                            getActor().setColor(Color.GREEN);
                            return false;
                        }
                        public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
                            getActor().setColor(Color.WHITE);
                        }
                        public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        }
                    });
                    dragAndDrop.addTarget(new DragAndDrop.Target(leftContainer) {
                        public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                            getActor().setColor(Color.GREEN);
                            return false;
                        }
                        public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
                            getActor().setColor(Color.WHITE);
                        }
                        public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        }
                    });
                    dragAndDrop.addTarget(new DragAndDrop.Target(bottomContainer) {
                        public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                            getActor().setColor(Color.GREEN);
                            return false;
                        }
                        public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
                            getActor().setColor(Color.WHITE);
                        }
                        public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        }
                    });
                }

                return false;
            }
            public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
                for(BoxContainer boxContainer : boxContainers)
                    boxContainer.remove();
                boxContainers.clear();
            }
            public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
            }
        });
    }

    public Panel(Stage stage, DragAndDrop dragAndDrop,
                 int x, int y, int width, int height, ArrayList<Box> boxes){
        this(stage, dragAndDrop, x, y, width, height);

        for(Box box : boxes){
            this.add(box);
        }
    }

    public boolean isInBounds(int x, int y, int width, int height){
        return(x > this.x && x + width < this.width && y > this.y && y + height < this.height);
    }

    public boolean isInBounds(Box box){
        return isInBounds((int) box.getX(), (int) box.getY(), box.width, box.height);
    }

    public Box getBox(int i){return this.get(i);}

    @Override
    public boolean add(Box box) {
        this.stage.addActor(box);

        return super.add(box);
    }
}
