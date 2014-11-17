package net.orbitalchainsaw.openryuubox.boxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Boîte représentant une variable littérale.
 * @author Jean-Vincent
 *
 */
public class LiteralBox extends Box{
    public final static int ALPHA = 1;
    public final static int BETA = 2;
    public final static int GAMMA = 3;
    public final static int DELTA = 4;
    protected final static String BOX_ALPHA_POS = BOX_TEXTURE_FOLDER + "alpha-pos.png";
    protected final static String BOX_ALPHA_NEG = BOX_TEXTURE_FOLDER + "alpha-neg.png";
    protected final static String BOX_BETA_POS = BOX_TEXTURE_FOLDER + "beta-pos.png";
    protected final static String BOX_BETA_NEG = BOX_TEXTURE_FOLDER + "beta-neg.png";
    protected final static String BOX_GAMMA_POS = BOX_TEXTURE_FOLDER + "gamma-pos.png";
    protected final static String BOX_GAMMA_NEG = BOX_TEXTURE_FOLDER + "gamma-neg.png";
    protected final static String BOX_DELTA_POS = BOX_TEXTURE_FOLDER + "delta-pos.png";
    protected final static String BOX_DELTA_NEG = BOX_TEXTURE_FOLDER + "delta-neg.png";

    public String name;

    /**
     * Crée une Box représentant une variable littérale et lui applique la texture correspondante.
     * @param value valeur parmi ALPHA, BETA, GAMMA ou DELTA.
     */
    public LiteralBox(int value){
        super();
        type = Box.LITERAL;
        this.value = value;
        updateTextureByValue();
    }

    @Override
    public void updateTextureByValue(){
        Texture texture;
        switch(value){
            case ALPHA:
                texture = new Texture(BOX_ALPHA_POS);
                break;
            case BETA:
                texture = new Texture(BOX_BETA_POS);
                break;
            case GAMMA:
                texture = new Texture(BOX_GAMMA_POS);
                break;
            case DELTA:
                texture = new Texture(BOX_DELTA_POS);
                break;
            default:
                texture = new Texture(BOX_TEXTURE_DEFAULT);
        }
        region = new TextureRegion(texture);
    }


}
