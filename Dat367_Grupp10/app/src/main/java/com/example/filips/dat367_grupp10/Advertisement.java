package com.example.filips.dat367_grupp10;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
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

    public Advertisement() { super();}

    /*
    public Advertisement(Profile profile, String description, Category category){
        setProfile(profile);
        setCategory(category);
        setDescription(description);
    }
    */


    /*
    public Advertisement(String firstName, String lastName, String email, String phone, LatLng position,
                         Category category, String description){
        setProfile(new Profile(firstName, lastName, email, phone, position));
        setCategory(category);
        setDescription(description);
        setPosition(position);
    }
*/

    public ParseGeoPoint getPosition(){
        return getParseGeoPoint("Position");
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
        put("Description", description);
    }

    public String getDescription() {
        return getString("Description");
    }
    public void setPosition(ParseGeoPoint pos){
        put("Position", pos);
    }

    public void printAd(){

        System.out.println(this.getDescription());
    }

    public static void fetchList(){
        Database tempDatabase = new Database();
        adList = tempDatabase.getAdList();
    }

    public static List<Advertisement> getAdList(){
        return adList;
    }

    public void setDetails(String firstName, String lastName, String email, String phone, LatLng position,
                         Category category, String description){
        setProfile(new Profile(firstName, lastName, email, phone, position));
        setCategory(category);
        setDescription(description);
        setPosition(new ParseGeoPoint(position.latitude,position.longitude));
    }
}
