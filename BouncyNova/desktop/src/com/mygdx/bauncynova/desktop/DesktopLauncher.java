package com.mygdx.bauncynova.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.bauncynova.BouncyNova;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 2520/4;
		config.height = 4160/4;
		config.addIcon("bouncy.png", Files.FileType.Internal);
		config.addIcon("icon.png", Files.FileType.Internal);
		new LwjglApplication(new BouncyNova(), config);
	}
}
