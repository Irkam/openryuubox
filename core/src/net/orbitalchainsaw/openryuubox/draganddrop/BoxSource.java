package net.orbitalchainsaw.openryuubox.draganddrop;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;

import net.orbitalchainsaw.openryuubox.BoxesBar;
import net.orbitalchainsaw.openryuubox.boxes.Box;
import net.orbitalchainsaw.openryuubox.boxes.LiteralBox;
import net.orbitalchainsaw.openryuubox.boxes.NumericBox;
import net.orbitalchainsaw.openryuubox.boxes.UnknownBox;

import java.lang.reflect.InvocationTargetException;

/**
 * Source de DragAndDrop. Objet cliquable qui donnera lieu au glisser-déposer. Cet objet est le point de départ du DragAndDrop, étant donné que c'est
 * ce sur quoi le joueur va devoir cliquer pour déplacer/créer un objet.
 * Dans notre cas on crée un objet, plutôt que de déplacer un objet existant.
 * @author Jean-Vincent
 *
 */
public class BoxSource extends Source {
    Box box;
    BoxesBar parent;

    /**
     * un objet de type Box peut être utilisé dans cette fonction étant donné que Box étend la classe Actor.
     * @param box Box à copier
     * @param parent BoxesBar pour les appels ultérieurs.
     */
    public BoxSource(Box box, BoxesBar parent) {
        super(box);
        this.box = box;
        this.parent = parent;
    }

    /**
     * Appelé quand le glisser-déposer commence, c'est-à-dire quand l'objet a été cliqué et que le pointeur s'est déplacé tout en gardant le clic enfoncé.
     * Crée une nouvelle Box de type et de valeurs correspondantes à celles de la Box choisie. Si on ne crée pas de Box, on peut directement récupérer la Box
     * que l'on a choisie et elle sera alors déplacée au-lieu d'être copiée. Cette Box est le Payload (charge utile).
     * Lorsqu'on crée cette nouvelle Box, on ajoute aussi les Box qui s'afficheront pour indiquer que la cible sur laquelle on pointe est valide ou non.
     * Note : le Payload est un objet qui contient des objets de types génériques. Il est donc possible d'ajouter tout ce que l'on souhaite à un DragAndDrop.
     */
    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        Box authorizedBox = parent.getAuthorizedBox();
        if(authorizedBox != null)
            if(authorizedBox != this.box)
                return null;

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

    /**
     * Lorsque le clic du glisser-déposé est relâché, effectuer ces actions.
     * @param target cible sur laquelle le glisser-déposé a été relâché. null si aucune Target ou Target invalide (target.drag == false)
     */
    @Override
    public void dragStop (InputEvent event, float x, float y, int pointer, Payload payload, DragAndDrop.Target target) {
        if(target != null){
            /*
            Si la cible sur laquelle l'objet a été posé est valide (non-null)
             */
            if(parent.getAuthorizedBox() == null) {
                parent.setAuthorizedBox(this.box);
                parent.setBlockingTargets(target);
            }else{
                parent.notifyBoxAddedToTarget(target);
            }
        }
    }
}
