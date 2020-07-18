package com.balouka.feedthehadar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Hadar
{
    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Animation animation;


    private int pos;       //o the X position of Hadar
    private int width;     //o the width
    private int height;     //o the height

    public Hadar()
    {
        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("AtlasHadar.atlas"));    //creates the animation of the left boat
        animation = new Animation(1 / 3f, atlas.getRegions());

        batch = new SpriteBatch();;
        width = Gdx.graphics.getWidth() / 6;
        height = width - width/4;

    }


    public void drawHadar (int position, float timePast)
    {
        this.pos = position;
        batch.begin();
        batch.draw((TextureRegion) animation.getKeyFrame(timePast, true), pos, 0, width, height);
        batch.end();
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public Animation getAnimation() {
        return animation;
    }

    public Batch getBatch() {
        return batch;
    }

    public int getPos() {
        return pos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
