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

    public Advertisement() {
        super();
    }

    public void setAdvertisement(String firstName, String lastName, String email, String phone, LatLng position,
                                 Category category, String description){
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhone(phone);
        setPosition(new ParseGeoPoint(position.latitude,position.longitude));
        setCategory(category);
        setDescription(description);
    }

    private void setFirstName(String firstName) {
        put("FirstName", firstName);
    }

    private void setLastName(String lastName) {
        put("LastName", lastName);
    }

    private void setEmail(String email) {
        put("Email", email);
    }

    private void setPhone(String phone) {
        put("Phone", phone);
    }

    public void setPosition(ParseGeoPoint pos){
        put("Position", pos);
    }

    public void setCategory(Category category){
        put("Category", category.toString());
    }

    public void setDescription(String description){
        put("Description", description);
    }


    public String getFirstName(){
        return getString("FirstName");
    }

    public String getLastName(){
        return getString("LastName");
    }

    public String getEmail(){
        return getString("Email");
    }

    public String getPhone(){
        return getString("Phone");
    }

    public ParseGeoPoint getPosition(){
        return getParseGeoPoint("Position");
    }

    public Category getCategory() {
        String categoryString = getString("Category");
        return Category.valueOf(categoryString);
    }

    public String getDescription() {
        return getString("Description");
    }
}
