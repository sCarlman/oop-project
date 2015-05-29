package edu.ctl.pinjobs.advertisement.view;

import android.app.Activity;
import android.widget.TextView;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.advertisement.model.IAdvertisement;

/**
 * Created by filiplarsson on 15-05-07.
 */
public class DetailedAdView {

    private TextView titleTextView;
    private TextView categoryTextView;
    private TextView distanceTextView;
    private TextView descriptionTextView;
    private TextView nameTextView;
    private TextView addressTextView;
    private TextView phoneTextView;
    private TextView mailTextView;
    private TextView cityTextView;

    public DetailedAdView(Activity activity, IAdvertisement ad, String distance){

        this.titleTextView = (TextView)activity.findViewById(R.id.titleTextView);
        this.categoryTextView = (TextView)activity.findViewById(R.id.categoryTextView);
        this.distanceTextView = (TextView)activity.findViewById(R.id.distanceTextView);
        this.descriptionTextView = (TextView)activity.findViewById(R.id.descriptionTextView);
        this.nameTextView = (TextView)activity.findViewById(R.id.nameTextView);
        this.addressTextView = (TextView)activity.findViewById(R.id.myProfileAddressTextView);
        this.phoneTextView = (TextView)activity.findViewById(R.id.myProfilePhoneTextView);
        this.mailTextView = (TextView)activity.findViewById(R.id.emailTextView);
        this.cityTextView = (TextView)activity.findViewById(R.id.detailedAdCityTextView);


        this.titleTextView.setText(ad.getTitle());
        this.categoryTextView.setText(ad.getCategory().toString().trim());
        this.descriptionTextView.setText(ad.getDescription());
        this.nameTextView.setText(ad.getAdvertiser().getFirstName() + " " + ad.getAdvertiser().getLastName());
        this.addressTextView.setText(ad.getAdvertiser().getAddress().split(",")[0]);
        this.phoneTextView.setText(ad.getAdvertiser().getPhone());
        this.mailTextView.setText(ad.getAdvertiser().getEmail());
        this.cityTextView.setText(ad.getAdvertiser().getAddress().split(",")[1]);

        int index = distance.indexOf('.');
        distance = distance.substring(0,index+2);
        this.distanceTextView.setText(distance + " kilometer bort");

    }
}
