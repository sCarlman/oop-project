package edu.ctl.pinjobs.Advertisement;

import android.widget.TextView;

/**
 * Created by filiplarsson on 15-05-07.
 */
public class DetaildAdView  {

    private TextView titleTextView;
    private TextView categoryTextView;
    private TextView distanceTextView;
    private TextView descriptionTextView;
    private TextView nameTextView;
    private TextView addressTextView;
    private TextView phoneTextView;
    private TextView mailTextView;

    public DetaildAdView(TextView titleTextView, TextView categoryTextView, TextView distanceTextView,
                         TextView descriptionTextView, TextView nameTextView, TextView addressTextView,
                         TextView phoneTextView, TextView mailTextView){

        this.titleTextView = titleTextView;
        this.categoryTextView = categoryTextView;
        this.distanceTextView = distanceTextView;
        this.descriptionTextView = descriptionTextView;
        this.nameTextView = nameTextView;
        this.addressTextView = addressTextView;
        this.phoneTextView = phoneTextView;
        this.mailTextView = mailTextView;

    }
}
