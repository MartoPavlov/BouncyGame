package com.mygdx.bauncynova.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bauncynova.BouncyNova;
import com.mygdx.bauncynova.assets.Assets;

public class Winds extends GameObject {
    public Winds(World world, BouncyNova bouncyNova, float x, float y)
    {
        super(bouncyNova.assets.getAssetManager().get(Assets.wind, Texture.class), world, bouncyNova,
                x, y, 0.5f, 0.5f);
        this.setVisible(false);
    }
    @Override
    protected void initBody() {

    }

    @Override
    public void act(float delta) {
        if(Platform.speedUp != 0) this.setVisible(true);
        else this.setVisible(false);
    }
}
