package com.example.filips.dat367_grupp10;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Albertsson on 15-04-01.
 */
@ParseClassName("Advertisement")
public class Advertisement extends ParseObject{
    private static List<Advertisement> adList;
    private String objectId;
    private LatLng position;
    private Profile profile;
    private Category category;
    private String description;

    public Advertisement() {}

    public Advertisement(Profile profile, String description, Category category){
        setProfile(profile);
        setCategory(category);
        setDescription(description);
    }

    public Advertisement(String firstName, String lastName, String email, String phone, LatLng position,
                         Category category, String description){
        setProfile(new Profile(firstName, lastName, email, phone, position));
        setCategory(category);
        setDescription(description);
        setPosition(position);
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

    public void setObjectId(String objectId){
        this.objectId = objectId;
    }

    public String getObjectId(){
        return this.objectId;
    }

    public static void fetchList(){
        Database tempDatabase = new Database();
        adList = tempDatabase.getAdList();
    }

    public static List<Advertisement> getAdList(){
        return adList;
    }
}
