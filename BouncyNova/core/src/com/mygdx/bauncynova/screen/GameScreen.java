package com.mygdx.bauncynova.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mygdx.bauncynova.BouncyNova;
import com.mygdx.bauncynova.assets.Assets;
import com.mygdx.bauncynova.world.GameWorld;

public class GameScreen implements Screen {
    private GameWorld gameWorld;
    private BouncyNova bouncyNova;
    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;
    private Texture background;
    private BitmapFont font;


    public GameScreen(BouncyNova bouncyNova)
    {
        this.bouncyNova = bouncyNova;

    }
    @Override
    public void show() {
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, BouncyNova.WIDTH, BouncyNova.HEIGHT);
        this.spriteBatch = new SpriteBatch();
        this.background = bouncyNova.assets.getAssetManager().get(Assets.background, Texture.class);
        this.gameWorld = new GameWorld(bouncyNova);
        this.font = new BitmapFont();
        font.getData().scale(10);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0 , 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,0,0, camera.viewportWidth, camera.viewportHeight);

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, "Score: "+gameWorld.getScore());
        font.draw(spriteBatch, glyphLayout, camera.position.x+glyphLayout.width/2,
                camera.position.y+camera.viewportHeight/2);

        spriteBatch.end();
        gameWorld.draw();

        gameWorld.act(delta);
        camera.update();
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
