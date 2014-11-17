package net.orbitalchainsaw.openryuubox.boxes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import net.orbitalchainsaw.openryuubox.Panel;

import java.awt.Rectangle;

/**
 * Classe des boîtes.
 * Abstraite pour ne pas créer de boîtes vides à partir de cette classe.
 * @author Jean-Vincent
 *
 */
public abstract class Box extends Actor{
    protected static final String BOX_TEXTURE_FOLDER = "boxes/";
    protected static final String BOX_TEXTURE_DEFAULT = BOX_TEXTURE_FOLDER + "default.png";
    public static final int UNKNOWN = 0;
    public static final int LITERAL = 1;
    public static final int NUMERIC = 2;

    public int type, value;
    public int width = 64, height = 64;
    protected TextureRegion region;
    protected Panel parentPanel = null;

    /**
     * Constructeur par défaut
     */
    public Box(){
        this.region = new TextureRegion(new Texture(BOX_TEXTURE_DEFAULT));
        setBounds(0, 0, this.width, this.height);
    }

    /**
     * Crée une boîte
     * @param x
     * @param y
     */
    public Box(int x, int y){
        this.region = new TextureRegion(new Texture("boxes/default.png"));
        setBounds(x, y, this.width, this.height);
    }

    public Box(int x, int y, TextureRegion region){
        this.region = region;
        setBounds(x, y, this.width, this.height);
    }
    
    /**
     * Crée une boîte en lui appliquant une texture
     * @param region région de texture (voir doc GDX sur les textures)
     */
    public Box(TextureRegion region){
        this.region = region;
        setBounds(0, 0, width, height);
    }

    @Override
    /**
     * Ajoute la Box au batch pour l'affichage, qui envoie toutes les images à OpenGL.
     * @param batch batch d'affichage
     * @param parentAlpha transparence du parent
     */
    public void draw(Batch batch, float parentAlpha){
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a*parentAlpha);
        batch.draw(region, getX(), getY());
    }

    public void setParentPanel(Panel panel){this.parentPanel = panel;}
    public Panel getParentPanel(){return this.parentPanel;}
    
    /**
     * Change les coordonées d'une Box.
     * @param x
     * @param y
     */
    public void setCoord(int x, int y){
        setBounds(x, y, width, height);
    }
    
    /**
     * applique la texture correspondant à la valeur de la Box.
     */
    public abstract void updateTextureByValue();
}
