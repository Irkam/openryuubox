package net.orbitalchainsaw.openryuubox.boxes;

/**
 * Created by Jean-Vincent on 14/10/2014.
 */
public class LiteralBox extends Box{
    String name;

    public LiteralBox(String name){
        super();
        this.name = name;
    }

    public LiteralBox(int x, int y, String name){
        super(x, y);
        this.name = name;
    }
}
