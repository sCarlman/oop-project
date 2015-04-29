package edu.ctl.pinjobs.model;

import com.parse.ParseGeoPoint;

/**
 * Created by Isaac on 2015-04-27.
 */
public interface IAdvertisement {

    void setFirstName(String firstName);
    void setLastName(String lastName);
    void setEmail(String email);
    void setPhone(String phone);
    void setLocation(ParseGeoPoint location);
    void setTitle(String title);
    void setDescription(String description);
    void setCategory(Category category);

    String getFirstName();
    String getLastName();
    String getEmail();
    String getPhone();
    ParseGeoPoint getLocation();
    String getTitle();
    String getDescription();
    Category getCategory();
}
