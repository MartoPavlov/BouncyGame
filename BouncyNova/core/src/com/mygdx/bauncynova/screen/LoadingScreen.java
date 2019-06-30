package com.mygdx.bauncynova.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.bauncynova.BouncyNova;
import com.mygdx.bauncynova.units.Bouncy;


public class LoadingScreen implements Screen
{
    private BouncyNova bouncyNova;
    private SpriteBatch spriteBatch;
    private BitmapFont font;
    private OrthographicCamera camera;
    public LoadingScreen(BouncyNova bouncyNova)
    {
        this.bouncyNova = bouncyNova;
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, BouncyNova.WIDTH, BouncyNova.HEIGHT);

    }
    @Override
    public void show() {

        font = new BitmapFont();
        font.getData().scale(20);
        font.setColor(Color.BLACK);
        render(Gdx.graphics.getDeltaTime());
        bouncyNova.assets.load();
        render(Gdx.graphics.getDeltaTime());
        while(!bouncyNova.assets.getAssetManager().update())
        {
            System.out.println("Loading: " + bouncyNova.assets.getAssetManager().getLoadedAssets());
        }
        bouncyNova.setScreen(new MenuScreen(bouncyNova));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255f, 255f, 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
        System.out.println("I was here");
        spriteBatch.begin();
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, "Loading...");
        float fontWidth = glyphLayout.width;
        font.draw(spriteBatch, glyphLayout, camera.position.x - fontWidth/2,camera.position.y);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
