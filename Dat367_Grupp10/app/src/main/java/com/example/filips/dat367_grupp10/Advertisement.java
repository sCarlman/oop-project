package com.example.filips.dat367_grupp10;

/**
 * Created by Albertsson on 15-04-01.
 */
public class Advertisement {

    Profile profile;
    Category category;
    String description;



    public Advertisement(){
    }

    public Advertisement(Profile profile, String description, Category category){
        this.setProfile(profile);
        this.setCategory(category);
        this.setDescription(description);
    }

    public Advertisement(String firstName, String lastName, String email, String phone, String preferredLocation,
                         Category category, String description){
        this.setProfile(new Profile(firstName, lastName, email, phone, preferredLocation));
        this.setCategory(category);
        this.setDescription(description);
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
}
