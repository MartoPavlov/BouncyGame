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
import com.mygdx.bauncynova.BouncyNova;
import com.mygdx.bauncynova.assets.Assets;

public class MenuScreen implements Screen {
    BouncyNova bouncyNova;
    Texture background;
    SpriteBatch spriteBatch;
    OrthographicCamera camera;
    BitmapFont font;
    public MenuScreen(BouncyNova bouncyNova)
    {
        this.bouncyNova = bouncyNova;
    }
    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        background = bouncyNova.assets.getAssetManager().get(Assets.background);
        font = new BitmapFont();
        font.getData().scale(20);
        font.setColor(Color.BLACK);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, BouncyNova.WIDTH, BouncyNova.HEIGHT);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, 0,0, camera.viewportWidth, camera.viewportHeight);
        spriteBatch.setColor(Color.DARK_GRAY);
        drawText("Tap to play", camera.position.x,  camera.position.y);
        drawText("Record: "+BouncyNova.highscore, camera.position.x+camera.viewportWidth/6,
                camera.position.y+camera.viewportHeight/2);
        spriteBatch.end();
        if(Gdx.input.justTouched())
        {
            bouncyNova.setScreen(new GameScreen(bouncyNova));
        }
    }
    private void drawText(String text, float x, float y)
    {
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, text);
        float wid = glyphLayout.width;
        font.draw(spriteBatch, glyphLayout, x-wid/2, y);
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
