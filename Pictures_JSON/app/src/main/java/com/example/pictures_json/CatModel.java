package com.example.pictures_json;

public class CatModel {
    private String name, race, sound, imgURL;

    String getImgURL(){
        return imgURL;
    }

    void setImgURL(String imgURL){
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getRace() {
        return race;
    }

    void setRace(String race) {
        this.race = race;
    }

    String getSound() {
        return sound;
    }

    void setSound(String sound) {
        this.sound = sound;
    }
}
