package com.mygdx.bauncynova.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Assets
{
    private AssetManager assetManager;
    public static String badlogic = "badlogic.jpg";
    public static String bouncy = "bouncy.png";
    public static String platform = "platform.png";
    public static String background = "background.png";
    public static String wind = "wind.png";
    public static String wind_sound = "wind_sound.ogg";
    public static String bounce_sound = "bounce_sound.wav";

    public Assets()
    {
        assetManager = new AssetManager();
    }

    public void load()
    {
        assetManager.load(badlogic, Texture.class);
        assetManager.load(bouncy, Texture.class);
        assetManager.load(platform, Texture.class);
        assetManager.load(background, Texture.class);
        assetManager.load(wind, Texture.class);
        assetManager.load(wind_sound, Sound.class);
        assetManager.load(bounce_sound, Sound.class);
    }
    public void dispose()
    {
        assetManager.dispose();
    }

    public AssetManager getAssetManager(){return assetManager;}
}
