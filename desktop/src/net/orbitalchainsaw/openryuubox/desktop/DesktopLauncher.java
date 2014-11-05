package net.orbitalchainsaw.openryuubox.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.orbitalchainsaw.openryuubox.OpenRyuuBox;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title="OpenRyuuBox";
        config.width=800;
        config.height=480;
		new LwjglApplication(new OpenRyuuBox(), config);
	}
}
