package edu.ctl.pinjobs.Advertisement;

import android.widget.TextView;

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

    public DetailedAdView(TextView titleTextView, TextView categoryTextView, TextView distanceTextView,
                          TextView descriptionTextView, TextView nameTextView, TextView addressTextView,
                          TextView phoneTextView, TextView mailTextView, IAdvertisement ad, String distance){

        this.titleTextView = titleTextView;
        this.categoryTextView = categoryTextView;
        this.distanceTextView = distanceTextView;
        this.descriptionTextView = descriptionTextView;
        this.nameTextView = nameTextView;
        this.addressTextView = addressTextView;
        this.phoneTextView = phoneTextView;
        this.mailTextView = mailTextView;

        this.titleTextView.setText(ad.getTitle());
        this.categoryTextView.setText(ad.getCategory().toString().trim());
        this.distanceTextView.setText(distance + " kilometer bort");
        this.descriptionTextView.setText(ad.getDescription());
        this.nameTextView.setText(ad.getAdvertiser().getFirstName() + " " + ad.getAdvertiser().getLastName());
        this.addressTextView.setText(ad.getAdvertiser().getAddress());
        this.phoneTextView.setText(ad.getAdvertiser().getPhone());
        this.mailTextView.setText(ad.getAdvertiser().getEmail());

    }
}
