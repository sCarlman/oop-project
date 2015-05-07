package edu.ctl.pinjobs.Advertisement;

import edu.ctl.pinjobs.Advertisement.Advertisement;
import edu.ctl.pinjobs.Advertisement.Category;
/**
 * Created by filiplarsson on 15-05-07.
 */
public class DetaildAdModel {

    static ClickedAd hej = new ClickedAd();
    static Advertisement clickedAd = new Advertisement(hej.clickedAd.getAdvertiser(),
            hej.clickedAd.getTitle(),
            hej.clickedAd.getDescription(),
            hej.clickedAd.getCategory());

    public static String getClickedAdTitle(){
        return clickedAd.getTitle().toString().trim();
    }
    public static String getClickedAdCategory(){
        return clickedAd.getCategory().toString().trim();
    }
    public static String getClickedAdDescription(){
        return clickedAd.getDescription().toString().trim();
    }
    public static String getClickedAdName(){
        return clickedAd.getAdvertiser().getFirstName().toString().trim() + " " +
                clickedAd.getAdvertiser().getLastName().toString().trim();
    }
    public static String getClickedAdAddress(){
        return clickedAd.getLocation().toString().trim();
    }
    public static String getClickedAdPhone(){
        return clickedAd.getAdvertiser().getPhone().toString().trim();
    }
    public static String getClickedAdEmail(){
        return clickedAd.getAdvertiser().getEmail().toString().trim();
    }


}
