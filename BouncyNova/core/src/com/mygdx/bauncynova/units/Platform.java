package com.mygdx.bauncynova.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bauncynova.BouncyNova;
import com.mygdx.bauncynova.assets.Assets;



public class Platform extends GameObject {
    public static float velocityX = -1f;
    public static float speedUp = 0;
    public Platform(World world, BouncyNova bouncyNova, float x, float y)
    {
        super(bouncyNova.assets.getAssetManager().get(Assets.platform, Texture.class), world,
                bouncyNova,x,y,3,1);

    }
    @Override
    protected void initBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(this.getX(),this.getY());
        bodyDef.type = BodyDef.BodyType.KinematicBody;

        body = world.createBody(bodyDef);
        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox( this.getWidth()/2, this.getHeight()/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 10f;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);

        bodyShape.dispose();
    }
    @Override
    public void act(float delta)
    {
        body.setLinearVelocity(Platform.velocityX + Platform.speedUp, 0);
        super.act(delta);
    }
}
