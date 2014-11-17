package net.orbitalchainsaw.openryuubox.boxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Boîte représentant une valeur numérique
 * @author Jean-Vincent
 *
 */
public class NumericBox extends Box{
    protected static final String BOX_ONE = BOX_TEXTURE_FOLDER + "d1.png";
    protected static final String BOX_MIN_ONE = BOX_TEXTURE_FOLDER + "d1.png";
    protected static final String BOX_TWO = BOX_TEXTURE_FOLDER + "d2.png";
    protected static final String BOX_MIN_TWO = BOX_TEXTURE_FOLDER + "d2.png";
    protected static final String BOX_THREE = BOX_TEXTURE_FOLDER + "d3.png";
    protected static final String BOX_MIN_THREE = BOX_TEXTURE_FOLDER + "d3.png";
    protected static final String BOX_FOUR = BOX_TEXTURE_FOLDER + "d4.png";
    protected static final String BOX_MIN_FOUR = BOX_TEXTURE_FOLDER + "d4.png";
    protected static final String BOX_FIVE = BOX_TEXTURE_FOLDER + "d5.png";
    protected static final String BOX_MIN_FIVE = BOX_TEXTURE_FOLDER + "d5.png";
    protected static final String BOX_SIX = BOX_TEXTURE_FOLDER + "d6.png";
    protected static final String BOX_MIN_SIX = BOX_TEXTURE_FOLDER + "d6.png";
    
    /**
     * Crée une Box représentant une variable numérique et lui applique la texture correspondante.
     * @param value entier relatif.
     */
    public NumericBox(int value){
        super();
        type = Box.NUMERIC;
        this.value = value;
        updateTextureByValue();
    }

    /**
     * additionne les valeurs de deux Box.
     * @param box Box dont la valeur doit être ajoutée.
     * @return false si la Box a été supprimée
     */
    public boolean mergeBoxes(NumericBox box){
    	if(value == (-box.value))
    		return false;
    	
        value += box.value;

        updateTextureByValue();
        return true;
    }

    @Override
    public void updateTextureByValue(){
        Texture texture;
        switch(value){
            case 1:
                texture = new Texture(BOX_ONE);
                break;
            case -1:
                texture = new Texture(BOX_MIN_ONE);
                break;
            case 2:
                texture = new Texture(BOX_TWO);
                break;
            case -2:
                texture = new Texture(BOX_MIN_TWO);
                break;
            case 3:
                texture = new Texture(BOX_THREE);
                break;
            case -3:
                texture = new Texture(BOX_MIN_THREE);
                break;
            case 4:
                texture = new Texture(BOX_FOUR);
                break;
            case -4:
                texture = new Texture(BOX_MIN_FOUR);
                break;
            case 5:
                texture = new Texture(BOX_FIVE);
                break;
            case -5:
                texture = new Texture(BOX_MIN_FIVE);
                break;
            case 6:
                texture = new Texture(BOX_SIX);
                break;
            case -6:
                texture = new Texture(BOX_MIN_SIX);
                break;
            default:
                texture = new Texture(BOX_TEXTURE_DEFAULT);
        }
        region = new TextureRegion(texture);
    }
}
