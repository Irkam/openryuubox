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
public class Panel{
    protected int x, y, width, height;
    protected Actor panelTarget;
    public Stage stage;
    public DragAndDrop dragAndDrop;
    public ArrayList<BoxContainer> boxContainers, boxDaDTargets;

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
        boxDaDTargets = new ArrayList<BoxContainer>();


        for(Box boxContainer : boxContainers){
            BoxContainer leftContainer = new BoxContainer((int) boxContainer.getX() - 64, (int) boxContainer.getY());
            BoxContainer bottomContainer = new BoxContainer((int) boxContainer.getX(), (int) boxContainer.getY() - 64);

            boxDaDTargets.add(leftContainer);
            boxDaDTargets.add(bottomContainer);
            stage.addActor(leftContainer);
            stage.addActor(bottomContainer);

            /*
            cible sur la boite pour les sommes
             */
            dragAndDrop.addTarget(new DragAndDrop.Target(boxContainer) {
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

            /*
            cible à gauche de la boite pour les produits
             */
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

            /*
            cible en-dessous de la boite pour les divisions
             */
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
    }

    public Panel(Stage stage, DragAndDrop dragAndDrop,
                 int x, int y, int width, int height, ArrayList<Box> boxes){
        /*
        for(Box box : boxes){
            this.add(box);
        }
        */
        this(stage, dragAndDrop, x, y, width, height);
    }

    public void addContainer(BoxContainer boxContainer){
        boxContainer.setParentPanel(this);
        this.stage.addActor(boxContainer);
        for(Actor actor : boxContainer.boxes)
            this.stage.addActor(actor);
        this.boxContainers.add(boxContainer);

        BoxContainer leftContainer = new BoxContainer((int) boxContainer.getX() - 64, (int) boxContainer.getY());
        BoxContainer bottomContainer = new BoxContainer((int) boxContainer.getX(), (int) boxContainer.getY() - 64);

        boxDaDTargets.add(leftContainer);
        boxDaDTargets.add(bottomContainer);
        stage.addActor(leftContainer);
        stage.addActor(bottomContainer);

        for(Box innerBox : boxContainer.boxes)
            dragAndDrop.addTarget(new DragAndDrop.Target(innerBox) {
                /*
                cible sur la boite pour les sommes
                 */
                public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    getActor().setColor(Color.GREEN);
                    return true;
                }
                public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
                    getActor().setColor(Color.WHITE);
                }
                public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    System.out.println("toast");
                }
            });

            /*
            cible à gauche de la boite pour les produits
             */
        dragAndDrop.addTarget(new DragAndDrop.Target(leftContainer) {
            public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                getActor().setColor(Color.GREEN);
                return true;
            }
            public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
                getActor().setColor(Color.WHITE);
            }
            public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                System.out.println("toast");
            }
        });

            /*
            cible en-dessous de la boite pour les divisions
             */
        dragAndDrop.addTarget(new DragAndDrop.Target(bottomContainer) {
            public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                getActor().setColor(Color.GREEN);
                return true;
            }
            public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
                getActor().setColor(Color.WHITE);
            }
            public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                System.out.println("toast");
            }
        });
    }

    public boolean isInBounds(int x, int y, int width, int height){
        return(x > this.x && x + width < this.width && y > this.y && y + height < this.height);
    }
}
