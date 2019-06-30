package com.mygdx.bauncynova.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.bauncynova.BouncyNova;

public abstract class GameObject extends Image {
    protected World world;
    protected BouncyNova bouncyNova;
    protected Body body;

    public GameObject(Texture texture, World world, BouncyNova bouncyNova, float x, float y,
                      float width, float height) {
        super(texture);
        this.setBounds(x, y, width, height);
        this.setOrigin(x, y);
        this.world = world;
        this.bouncyNova = bouncyNova;
        initBody();
    }

    protected abstract void initBody();

    @Override
    public void act(float delta) {
        this.setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        this.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
    }
}


