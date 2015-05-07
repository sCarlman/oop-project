package edu.ctl.pinjobs.Advertisement;

import edu.ctl.pinjobs.Advertisement.Advertisement;
import edu.ctl.pinjobs.Advertisement.Category;
import edu.ctl.pinjobs.profile.IProfile;

/**
 * Created by filiplarsson on 15-05-07.
 */
public class ClickedAd {

    Advertisement clickedAd;

    public void setClickedAd(IProfile advertiser, String title, String description, Category category){

        clickedAd.setAdvertiser(advertiser);
        clickedAd.setTitle(title);
        clickedAd.setDescription(description);
        clickedAd.setCategory(category);
        clickedAd.setLocation(advertiser.getAddress());

    }
    public Advertisement getClickedAd(){
        return clickedAd;
    }

}
