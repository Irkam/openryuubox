package net.orbitalchainsaw.openryuubox;
import com.badlogic.gdx.utils.Json;
import net.orbitalchainsaw.openryuubox.*;
import java.io.File;
import java.util.*;

import javax.swing.Box;
//import sun.org.mozilla.javascript.json.JsonParser;

/**
 * Created by sidediallo on 05/11/14.
 */
public class JsonFileCreat {
    public int type;

    public File jsonFile;
    //public JsonParser jp = null;
    public ArrayList <Integer> boxListType;

    public JsonFileCreat(String name, File jsonFile) {
        this.jsonFile  = jsonFile;
    }

    /* json.writeValue(name, number); */
    void jsonWrite() {
    }
}
