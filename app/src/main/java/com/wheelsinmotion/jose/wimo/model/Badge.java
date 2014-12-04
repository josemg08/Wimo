package com.wheelsinmotion.jose.wimo.model;

/**
 * Created by jose on 11/29/14.
 */
public class Badge {
    private long id;
    private String name;
    private int value, drawableValue;

    public Badge(long id, String name, int value, int drawableValue){
        this.id = id;
        this.name = name;
        this.value = value;
        this.drawableValue = drawableValue;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return value;
    }

    public int getDrawableValue(){
        return drawableValue;
    }

}
