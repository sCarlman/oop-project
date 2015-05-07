package edu.ctl.pinjobs.Advertisement;

import edu.ctl.pinjobs.Advertisement.Advertisement;
import edu.ctl.pinjobs.Advertisement.Category;
import edu.ctl.pinjobs.profile.IProfile;

/**
 * Created by filiplarsson on 15-05-07.
 */

//Ett sätt att spara den ad-infon som från den ad man klickar på
//så att man kan visa detaljerad info om den ad'en
public class ClickedAd {

    static Advertisement clickedAd;

    //setmetod för att sätta en ad som vald
    public void setClickedAd(IProfile advertiser, String title, String description, Category category){

        clickedAd.setAdvertiser(advertiser);
        clickedAd.setTitle(title);
        clickedAd.setDescription(description);
        clickedAd.setCategory(category);
        clickedAd.setLocation(advertiser.getAddress());

    }
    //getmetod för att hämta den ad man klickat på
    public static Advertisement getClickedAd(){
        return clickedAd;
    }

}
