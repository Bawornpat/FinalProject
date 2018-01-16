package com.ssru.afinal.ldlearning.model;

/**
 * Created by namevermine on 14/12/2560.
 */

public class Alphabet {


    private int pic;
    private int sound;
    private String name;

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Alphabet(int pic, int sound, String name) {
        this.pic = pic;
        this.sound = sound;
        this.name = name;
    }
}



