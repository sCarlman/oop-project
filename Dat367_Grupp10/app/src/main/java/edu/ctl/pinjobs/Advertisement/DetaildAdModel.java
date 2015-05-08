package edu.ctl.pinjobs.Advertisement;

import edu.ctl.pinjobs.Advertisement.Advertisement;
import edu.ctl.pinjobs.Advertisement.Category;
/**
 * Created by filiplarsson on 15-05-07.
 */
public class DetaildAdModel implements IDetaildAdModel {

    Advertisement hej = ClickedAd.getClickedAd();

    Advertisement clickedAd = new Advertisement(hej.getAdvertiser(),
            hej.getTitle(),
            hej.getDescription(),
            hej.getCategory());

    public String getClickedAdTitle(){
        return clickedAd.getTitle().toString().trim();
    }
    public String getClickedAdCategory(){
        return clickedAd.getCategory().toString().trim();
    }
    public String getClickedAdDescription(){
        return clickedAd.getDescription().toString().trim();
    }
    public String getClickedAdName(){
        return clickedAd.getAdvertiser().getFirstName().toString().trim() + " " +
                clickedAd.getAdvertiser().getLastName().toString().trim();
    }
    public String getClickedAdAddress(){
        return clickedAd.getLocation().toString().trim();
    }
    public String getClickedAdPhone(){
        return clickedAd.getAdvertiser().getPhone().toString().trim();
    }
    public String getClickedAdEmail(){
        return clickedAd.getAdvertiser().getEmail().toString().trim();
    }


}
