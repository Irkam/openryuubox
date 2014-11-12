package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;


import net.orbitalchainsaw.openryuubox.boxes.*;
import org.json.*;

/**
 * Created by sidediallo on 10/11/14.
 */
public class JSONLevelGenerator{
    public static PanelContainer parseLevelJSON(String filePath, Stage stage, DragAndDrop dragAndDrop) {
        Panel lPanel, rPanel;
        BoxesBar boxesBar;
        FileHandle jsonFile = Gdx.files.internal(filePath);

        try {
            JSONObject object = new JSONObject(jsonFile.readString());

            JSONArray dock = object.getJSONArray("dock");
            JSONArray lpanel = object.getJSONArray("lpanel");
            JSONArray rpanel = object.getJSONArray("rpanel");

            /* Charger la BoxesBar */
            boxesBar = new BoxesBar(stage, dragAndDrop);
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
            lPanel = new Panel(stage, dragAndDrop, 0, 64, 800/2, 480);
            for (int i = 0; i < lpanel.length(); i++) {
                lPanel.addContainer(createBoxContainer(lpanel.getJSONObject(i), null));
            }

            /* Charger rigthPanel */
            rPanel = new Panel(stage, dragAndDrop, 800/2, 64, 800/2, 480);
            for (int i = 0; i < rpanel.length(); i++) {
                rPanel.addContainer(createBoxContainer(rpanel.getJSONObject(i), null));

            }
        }
        catch(JSONException e) {
            e.printStackTrace();
            return null;
        }

        return new PanelContainer(lPanel, rPanel, boxesBar);
    }

    protected static BoxContainer createBoxContainer(JSONObject container, BoxContainer parent) throws JSONException{
        int x, y;
        JSONArray boxes = container.getJSONArray("boxes");
        JSONObject options;
        if(!container.isNull("options")) {
            options = container.getJSONObject("options");
            /* On charge options i et j */
            x = options.getInt("x");
            y = options.getInt("y");
        }else{
            if(parent != null) {
                x = (int)parent.getX();
                y = (int)parent.getY() - 64;
            }
            else {
                x = 0;
                y = 0;
            }
        }

        BoxContainer bc = new BoxContainer(x, y);
        for (int i = 0; i < boxes.length(); i++) {
            JSONObject box = boxes.getJSONObject(i);
            String boxType = box.getString("type");
            int boxValue = box.getInt("value");

            if (boxType.compareTo("literal") == 0)
                bc.addBox(new LiteralBox(boxValue));

            else if (boxType.compareTo("numeric") == 0)
                bc.addBox(new NumericBox(boxValue));

            else if (boxType.compareTo("unknown") == 0)
                bc.addBox(new UnknownBox());
        }

        if(!container.isNull("child"))
            bc.addBottom(createBoxContainer(container.getJSONObject("child"), bc));

        return bc;
    }
}
