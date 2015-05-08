package edu.ctl.pinjobs.Advertisement;


import edu.ctl.pinjobs.Advertisement.Category;
import edu.ctl.pinjobs.profile.IProfile;

/**
 * Created by Isaac on 2015-04-27.
 */
public interface IAdvertisement {

    public void setAdvertiser(IProfile advertiser);
    public void setLocation(String location);
    public void setTitle(String title);
    public void setDescription(String description);
    public void setCategory(Category category);

    public IProfile getAdvertiser();
    public String getLocation();
    public String getTitle();
    public String getDescription();
    public Category getCategory();
}
