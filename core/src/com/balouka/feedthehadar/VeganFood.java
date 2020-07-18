package com.balouka.feedthehadar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class VeganFood
{
    private Texture food;
    private Batch batch;


    private int foodPosX;       //o the X position of the food
    private int foodPosY;       //o the Y position of food
    private int width;      //o the width
    private int height;     //o the height
    private int speed;      // the speed of the food

    private boolean foodType;   // true is vegan false is meat

    private Random rnd;     // random X location for the food

    private Texture hadar = new Texture("badlogic.jpg");
    private Texture [] vegan = new Texture[3];
    private Texture [] meat = new Texture[3];


    public VeganFood()
    {
        // vegan food texture
        vegan [0] = new Texture("carrot.png");
        vegan [1] = new Texture("lettuce.png");
        vegan [2] = new Texture("toamto.png");

        // meat food texture
        meat [0] = new Texture("meat.png");
        meat [1] = new Texture("hotdog.png");
        meat [2] = new Texture("chicken.png");

        batch = new SpriteBatch();

        // setting the food image specifics
        width = 100;
        height = 100;

        // setting the place for the image
        foodPosX = randomFoodLocaion();
        foodPosY = Gdx.graphics.getHeight() + height;   // setting the image in the top of the screen

        // setting the food's speed
        speed = 6;

        // random food type
        rnd = new Random();
        foodType = rnd.nextBoolean();

        food = setFoodType(foodType, rnd.nextInt(3));
    }


    public int drawVeganFood (int hadarPos, int score)
    {
        int score2 = score;
        // drawing the food
        batch.begin();
        batch.draw(food, foodPosX, foodPosY, width, height);
        batch.end();

        // updating the location
        foodPosY = foodPosY - speed;

        // if food has touched floor
        if (foodPosY <= 0)
            resetVeganFood();



        if (((foodPosX >= hadarPos && foodPosX <= hadarPos+hadar.getWidth()) || (foodPosX+width >= hadarPos && foodPosX+width <= hadarPos+hadar.getWidth())) && (foodPosY <= hadar.getHeight()))
        // if food was eaten
        {
            // if the food is vegan or not
            if (isVegan())
            {
                resetVeganFood();
                score2 ++;
            }

            else {
                resetVeganFood();
                return -999; // loose
            }
        }
        return score2;
    }

    public void resetVeganFood ()
    {
        foodPosY = Gdx.graphics.getHeight() + height;   // setting the image in the top of the screen
        foodPosX = randomFoodLocaion();
        foodType = rnd.nextBoolean();
        food = setFoodType(foodType, rnd.nextInt(3));       // setting the type and size of the food
        int foodNum = rnd.nextInt(3);

        // if this is vegan food choose vegan array
        if (foodType)
            food = vegan[foodNum];
        else
            food = meat[foodNum];

       // setFoodSize(foofNum);
    }


    private void setFoodSize (int random)
    {
        if (isVegan())
        {
            if (random == 0)
                System.out.println("onion");
            if (random == 1)
                System.out.println("lettuce");
            if (random == 2)
                System.out.println("tomato");
        }
        else
        {
            if (random == 0)
                System.out.println("meat");
            if (random == 1)
                System.out.println("hotdog");
            if (random == 2)
                System.out.println("chicken");
        }
    }



    public int randomFoodLocaion()
    {
        rnd = new Random();
        return rnd.nextInt(Gdx.graphics.getWidth() - width);
    }

    public Texture getVeganFood() {
        return food;
    }

    public Batch getBatch() {
        return batch;
    }

    public int getPosX() {
        return foodPosX;
    }

    public int getPosY() {
        return foodPosY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }



    private boolean isVegan()
    {
        return foodType;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }



    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }



    // setting the kind of food meat / vegan and it's size
    private Texture setFoodType(boolean foodType, int random)
    {
        Texture type;


        // setting the type of food and it's size
        if (foodType)
        {
            type = vegan[random];
            switch (random)
            {
                case 0: {this.width = 2;
                    this.height = 2;
                }

                case 1: {width = Gdx.graphics.getWidth() / 25;
                    height = width;}

                case 2: {width = Gdx.graphics.getWidth() / 15;
                    height = (int)(width* 0.9) ;}
            }
        }
        // setting the type of food and it's size
        else
        {
            type = meat[random];
            switch (random)
            {
                case 0: {width = Gdx.graphics.getWidth() / 35;
                    height = width * 2;}

                case 1: {width = Gdx.graphics.getWidth() / 25;
                    height = width;}

                case 2: {width = Gdx.graphics.getWidth() / 12;
                    height = (int)(width* 0.6) ;}
            }
        }
        return type;
    }

}