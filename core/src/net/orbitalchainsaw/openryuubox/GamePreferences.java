package net.orbitalchainsaw.openryuubox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Romain on 06/11/2014.
 */
public class GamePreferences {
    public static GamePreferences instance = new GamePreferences();

    public boolean sound;

    public Preferences prefs;

    public GamePreferences(){
        prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
    }

    public void load(){
        sound = prefs.getBoolean("sound", true);
    }

    public void save(){
        prefs.putBoolean("sound", sound);
        prefs.flush();
    }
}
