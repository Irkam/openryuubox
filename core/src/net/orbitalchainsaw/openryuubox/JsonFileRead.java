package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.Files;

import net.orbitalchainsaw.openryuubox.boxes.Box;

import org.json.*;
import java.io.*;
import java.util.*;
import javax.naming.*;


/**
 * Created by sidediallo on 10/11/14.
 */
public class JsonFileRead {
    public ArrayList<Box> box;
    public PanelContainer panelContainer;
    public Panel leftPanel;
    public Panel rightPanel;
    public BoxesBar boxesBar;

    public JsonFileRead() {
        box = new ArrayList<Box>();
        panelContainer = new PanelContainer();
        leftPanel = null;
        rightPanel = null;
        boxesBar = new BoxesBar();
    }

    public void parser(String file) {
        FileHandle jsonFile = Gdx.files.internal(file);
        String text = jsonFile.readString();

        System.out.print(text);

        boolean isExt = Gdx.files.isExternalStorageAvailable();

    }
    /*
    public PanelContainer parser(Context context, int fichier) throws JSONException {

        InputStream is = context.getRessources().openRawRessource(fichier);
        String rawJson = stream2String(is);

        if (!rawJson.equals("")) {
            JSONObject ob = new JSONObject(rawJson);

            // On charge le tableau de boxes qui se trouve dans le fichier JSON
            JSONArray dock = ob.getJSONArray("dock");
            JSONArray lpanel = ob.getJSONArray("lpanel");
            JSONArray rpanel = ob.getJSONArray("rpanel");

            // on instancie une nouvelle liste de personne

        }


        return panelContainer;
    }

    private String stream2String(InputStream stream) {
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader buffer = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();

    }
    */
}
