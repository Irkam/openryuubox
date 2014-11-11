package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.Files;

import net.orbitalchainsaw.openryuubox.boxes.*;
import org.json.*;
import java.io.*;
import java.util.*;
import javax.naming.*;

/**
 * Created by sidediallo on 10/11/14.
 */
public class JsonFileRead {
    public Box box;
    public PanelContainer panelContainer;
    public Panel leftPanel;
    public Panel rightPanel;
    public BoxesBar boxesBar;

    public JsonFileRead() {
        panelContainer = new PanelContainer();
        leftPanel = null;
        rightPanel = null;
        boxesBar = new BoxesBar();
    }

    public void parser(String file) {
        FileHandle jsonFile = Gdx.files.internal(file);
        JSONObject object = new JSONObject(jsonFile);

        try {
            JSONArray dock = object.getJSONArray("dock");
            JSONArray lpanel = object.getJSONArray("lpanel");
            JSONArray rpanel = object.getJSONArray("rpanel");

            /* Charger la BoxesBar */
            for (int i = 0; i < dock.length(); i++) {
                JSONObject io = dock.getJSONObject(i);
                String boxType = io.getString("type");
                int value = io.getInt("value");

                if (boxType.compareTo("literal") == 0)
                    boxesBar.add(new LiteralBox(value));
                else if (boxType.compareTo("numeric") == 0)
                    boxesBar.add(new NumericBox(value));
            }

            /* Charger leftPanel */
            for (int i = 0; i < lpanel.length(); i++) {
                JSONObject io = lpanel.getJSONObject(i);

                JSONArray options = io.getJSONArray("options");
                JSONArray boxes = io.getJSONArray("boxes");
                JSONArray childBoxes = io.getJSONObject("child").getJSONArray("boxes");

                /* On charge options i et j */
                int x, y;
                JSONObject io1 = options.getJSONObject(i);
                x = io1.getInt("x");
                y = io1.getInt("y");
                BoxContainer bc = new BoxContainer(x, y);
                /* On charge les boxes */
                for (int j = 0; j < boxes.length(); j++) {
                    JSONObject io2 = boxes.getJSONObject(j);
                    String boxType = io2.getString("type");
                    int value = io2.getInt("value");


                    if (boxType.compareTo("literal") == 0)
                        bc.addBox(new LiteralBox(value));

                    else if (boxType.compareTo("numeric") == 0)
                        bc.addBox(new NumericBox(value));

                    else if (boxType.compareTo("unknown") == 0)
                        bc.addBox(new UnknownBox());
                }

                /* On charge les childs */
                for (int k = 0; k < childBoxes.length(); k++) {

                }

                leftPanel.addContainer(bc);
            }

            /* Charger rigthPanel */
            for (int i = 0; i < rpanel.length(); i++) {

            }
        }
        catch(JSONException e) {

        }
    }
}
