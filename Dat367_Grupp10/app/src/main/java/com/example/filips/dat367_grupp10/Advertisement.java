package com.example.filips.dat367_grupp10;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Albertsson on 15-04-01.
 */
public class Advertisement {

    LatLng position;
    Profile profile;
    Category category;
    String description;

    public Advertisement(Profile profile, String description, Category category){
        this.setProfile(profile);
        this.setCategory(category);
        this.setDescription(description);
    }

    public Advertisement(String firstName, String lastName, String email, String phone, LatLng position,
                         Category category, String description){
        this.setProfile(new Profile(firstName, lastName, email, phone, position));
        this.setCategory(category);
        this.setDescription(description);
        this.setPosition(position);
    }

    public LatLng getPosition(){
        return position;
    }

    public void setProfile(Profile profile){
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setCategory(Category category){
        this.category = category;
    }
    
    public Category getCategory() {
        return category;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void setPosition(LatLng pos){
        position = pos;
    }

    public void printAd(){

        System.out.println(this.getDescription());
    }
}
