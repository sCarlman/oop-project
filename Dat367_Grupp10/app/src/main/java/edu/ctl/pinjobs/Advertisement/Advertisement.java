package edu.ctl.pinjobs.Advertisement;


import edu.ctl.pinjobs.profile.IProfile;

/**
 * Created by Albertsson on 15-04-01.
 */

public class Advertisement implements IAdvertisement {

    private IProfile advertiser;
    private String location;
    private String title;
    private String description;
    private Category category;

    public Advertisement(IProfile advertiser, String title, String description, Category category) {
        setAdvertiser(advertiser);
        setLocation(advertiser.getAddress());
        setTitle(title);
        setDescription(description);
        setCategory(category);
    }

    public void setAdvertiser(IProfile advertiser) {
        this.advertiser = advertiser;
    }

    public void setLocation(String location){
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

    public IProfile getAdvertiser() {
        return advertiser;
    }

    public String getLocation(){
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
