package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import net.orbitalchainsaw.openryuubox.boxes.Box;
import net.orbitalchainsaw.openryuubox.boxes.BoxContainer;
import net.orbitalchainsaw.openryuubox.boxes.EmptyBox;
import net.orbitalchainsaw.openryuubox.boxes.LiteralBox;
import net.orbitalchainsaw.openryuubox.boxes.NumericBox;
import net.orbitalchainsaw.openryuubox.draganddrop.InnerBoxTarget;

import java.util.ArrayList;

/**
 * Created by jivay on 16/10/14.
 */
public class Panel{
    protected int x, y, width, height;
    protected Actor panelTarget;
    public Stage stage;
    public DragAndDrop dragAndDrop;
    public ArrayList<Box> boxContainers, boxDaDTargets;

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

        boxContainers = new ArrayList<Box>();
        boxDaDTargets = new ArrayList<Box>();
    }

    public void addContainer(final BoxContainer boxContainer){
        boxContainer.setParentPanel(this);
        this.stage.addActor(boxContainer);
        for(Actor actor : boxContainer.boxes)
            this.stage.addActor(actor);
        this.boxContainers.add(boxContainer);

        for(final Box innerBox : boxContainer.boxes)
            dragAndDrop.addTarget(new InnerBoxTarget(innerBox, boxContainer));

        /*
        cible à gauche de la boite pour les produits
         */
        EmptyBox leftContainer = boxContainer.getLeftTarget();
        boxDaDTargets.add(leftContainer);
        stage.addActor(leftContainer);

        dragAndDrop.addTarget(new DragAndDrop.Target(leftContainer) {
            public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                getActor().setColor(Color.GREEN);
                return true;
            }
            public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
                getActor().setColor(Color.WHITE);
            }
            public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                Box payloadBox = (Box) payload.getObject();
                switch(payloadBox.type){
                    case Box.LITERAL:
                        boxContainer.addBox(new LiteralBox(payloadBox.value));
                        break;
                    case Box.NUMERIC:
                        boxContainer.addBox(new NumericBox(payloadBox.value));
                        break;
                    default:
                        System.out.println("ajout d'une boite merdeuse");
                }

            }
        });

        /*
        cible en-dessous de la boite pour les divisions
         */
        if(boxContainer.getParentBoxContainer() == null) {
            //Ne pas ajouter la cible pour créer une nouvelle fraction si le BoxContainer est déjà le dénominateur d'une fraction
            EmptyBox bottomContainer = boxContainer.getBottomTarget();
            boxDaDTargets.add(bottomContainer);
            stage.addActor(bottomContainer);

            dragAndDrop.addTarget(new DragAndDrop.Target(bottomContainer) {
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    getActor().setColor(Color.GREEN);
                    return true;
                }

                public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
                    getActor().setColor(Color.WHITE);
                }

                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    BoxContainer newBottom = new BoxContainer((int) boxContainer.getX(),
                            (int) boxContainer.getY() - 64);
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
                    newBottom.getParentPanel().addContainer(newBottom);
                }
            });
        }

        if(boxContainer.getBottomBoxContainer() != null)
            addContainer(boxContainer.getBottomBoxContainer());
    }

    public boolean isInBounds(int x, int y, int width, int height){
        return(x > this.x && x + width < this.width && y > this.y && y + height < this.height);
    }
}
