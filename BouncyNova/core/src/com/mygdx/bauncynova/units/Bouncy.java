package com.mygdx.bauncynova.units;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bauncynova.BouncyNova;
import com.mygdx.bauncynova.assets.Assets;
import com.mygdx.bauncynova.screen.MenuScreen;

public class Bouncy extends GameObject
{
    private Sound sound;
    public Bouncy(World world, BouncyNova bouncyNova)
    {
        super(bouncyNova.assets.getAssetManager().get(Assets.bouncy, Texture.class), world,
                bouncyNova,2,18, 1, 1);
        sound = bouncyNova.assets.getAssetManager().get(Assets.bounce_sound, Sound.class);
    }

    @Override
    protected void initBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(),getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        CircleShape bodyShape = new CircleShape();
        bodyShape.setRadius(this.getWidth()/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density =  6f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 1f;

        body.createFixture(fixtureDef);

        bodyShape.dispose();
        body.setFixedRotation(true);
    }
    public void fall()
    {
        body.applyForce(new Vector2(0, -100f),new Vector2(getX()+getWidth()/2,
                getY()+getHeight()/2), true);
    }
    public void fixVelocity()
    {
        if (body.getLinearVelocity().y < -30) body.setLinearVelocity(0, -30);
        if (body.getLinearVelocity().y > 30) body.setLinearVelocity(0, 30);
        if (body.getLinearVelocity().x != 0) body.setLinearVelocity(0,
                body.getLinearVelocity().y);

        if(body.getPosition().x < 2)
        {
            body.getPosition().x = 2;
        }
    }
    public void die()
    {
        bouncyNova.setScreen(new MenuScreen(bouncyNova));
    }

    public void playSound()
    {
        sound.play(1f, 0.4f, 0);
    }
}
