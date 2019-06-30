package com.mygdx.bauncynova.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.bauncynova.BouncyNova;
import com.mygdx.bauncynova.assets.Assets;
import com.mygdx.bauncynova.collision.B2dContactListener;
import com.mygdx.bauncynova.units.Bouncy;
import com.mygdx.bauncynova.units.Platform;
import com.mygdx.bauncynova.units.Winds;

import java.util.Random;

import static java.lang.Math.abs;

public class GameWorld
{
    private final int MAX_PLATFORMS = 5;
    private Stage stage;
    public World world;
    private BouncyNova bouncyNova;
    private Bouncy player;
    private Queue<Platform> platforms;
    private Sound windSound;
    private boolean playing = false;
    private int score;

    public GameWorld(BouncyNova bouncyNova)
    {
        this.bouncyNova = bouncyNova;
        this.world = new World(new Vector2(0, -9.8f),false);
        float worldHeight = 20;
        float ratio = BouncyNova.HEIGHT / worldHeight;
        float worldWidth = BouncyNova.WIDTH / ratio;
        this.stage = new Stage(new StretchViewport(worldWidth, worldHeight));

        this.player = new Bouncy(world, bouncyNova);
        this.stage.addActor(player);
        generateWind();
        platforms = new Queue<Platform>(MAX_PLATFORMS);
        Platform first = new Platform(world, bouncyNova, player.getX(), player.getY()-10);
        platforms.addFirst(first);
        initPlatforms();
        this.stage.addActor(first);
        windSound = bouncyNova.assets.getAssetManager().get(Assets.wind_sound, Sound.class);
        score = 0;
        this.world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                player.playSound();
                score++;
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    public void draw()
    {
        this.stage.draw();
        this.world.step(Gdx.graphics.getDeltaTime(),6,2);
        if (Gdx.input.isTouched())
        {
            player.fall();
        }
    }
    private  void initPlatforms()
    {
        while(platforms.size<MAX_PLATFORMS)
        {
            addPlatform();
        }
    }
    private void updatePlatforms(float delta)
    {
        if (player.getY() > 20)
        {
            Platform.speedUp = -3f;
            if(!playing)
            {
                windSound.play();
                playing = true;
            }
        }
        else
        {
            Platform.speedUp = 0;
            windSound.stop();
            playing = false;
        }

        for(Platform p: platforms)
        {
            p.act(delta);
        }
        while(platforms.get(platforms.size-1).getX() <= -platforms.get(platforms.size-1).getWidth())
        {
            platforms.removeLast();
            Platform.velocityX -= 0.04f;
            addPlatform();
        }
    }
    private void addPlatform()
    {
        Random random = new Random();
        int seed = random.nextInt(100);
        Platform last = platforms.get(0);
        int minHeight = Math.max(0, (int)last.getY()-2);
        int maxHeight = Math.min((int)this.stage.getHeight()-4, (int)last.getY()+2);
        int newHeight = (seed  % (maxHeight + 1 - minHeight )) + minHeight;

        Platform newPlatform = new Platform(world, bouncyNova,
                last.getX() + 5f + abs(Platform.velocityX), newHeight);
        this.stage.addActor(newPlatform);
        this.platforms.addFirst(newPlatform);
    }

    public void act(float delta)
    {
        player.fixVelocity();
        if(player.getY() < 0)
        {
            if(score>BouncyNova.highscore)bouncyNova.updateHighscore(score);
            player.die();
        }
        this.stage.act(delta);
        updatePlatforms(delta);
    }

    private void createWinds(float x ,float y)
    {
        Winds wind = new Winds(world, bouncyNova, x, y);
        stage.addActor(wind);
    }
    private void generateWind()
    {
        createWinds(2,7);
        createWinds(5,12);
        createWinds(7,2);
        createWinds(9, 8);
        createWinds(11, 15);
        createWinds(4, 15);
    }
    public int getScore(){return score;}
}
