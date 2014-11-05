package net.orbitalchainsaw.openryuubox.boxes;

/**
 * Created by Jean-Vincent on 14/10/2014.
 */
public class NumericBox extends Box{
    public int value;

    public NumericBox(int value){
        super();
        type = Box.NUMERIC;
        this.value = value;
    }
}
