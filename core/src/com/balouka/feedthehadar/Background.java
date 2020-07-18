package com.balouka.feedthehadar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by nirbl on 27/11/2016.
 */

public class Background
{
    private Texture texture;
    private Batch batch;

    public Background()
    {
        texture = new Texture(Gdx.files.internal("wallBackground.jpg"));
        batch = new SpriteBatch();

    }

    public void drawBackground()
    {
        batch.begin();
        batch.draw(texture, 0,0, Gdx.graphics.getWidth()+20, Gdx.graphics.getHeight());
        batch.end();

    }

    public Texture getTexture() {
        return texture;
    }

    public Batch getBatch() {
        return batch;
    }
}