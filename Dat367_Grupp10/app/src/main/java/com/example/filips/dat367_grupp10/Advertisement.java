package com.example.filips.dat367_grupp10;

/**
 * Created by Albertsson on 15-04-01.
 */
public class Advertisement {

    TestProfile testProfile;
    Category category;
    String description;



    public Advertisement(){
    }

    public Advertisement(TestProfile profile, String description, Category category){
        this.testProfile = profile;
        this.category = category;
        this.description = description;
    }

    public String getFirstName(){
        return testProfile.getfName();
    }

    public String getLastName(){
        return testProfile.getlName();
    }

    public String getEMail(){
        return testProfile.geteMail();
    }

    public String getPhone(){
        return testProfile.getPhone();
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
