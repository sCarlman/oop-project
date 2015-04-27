package edu.ctl.pinjobs.model;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseGeoPoint;

/**
 * Created by Albertsson on 15-04-01.
 */

public class Advertisement implements IAdvertisement{

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private ParseGeoPoint location;
    private String title;
    private String description;
    private Category category;

    public Advertisement(IProfile advertiser, String title, String description, Category category) {
        setFirstName(advertiser.getFirstName());
        setLastName(advertiser.getLastName());
        setEmail(advertiser.getEmail());
        setPhone(advertiser.getPhone());
        setLocation(advertiser.getAddress());
        setTitle(title);
        setDescription(description);
        setCategory(category);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation(ParseGeoPoint location){
        this.location = location;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getPhone(){
        return phone;
    }

    public ParseGeoPoint getLocation(){
        return location;
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
