package edu.ctl.pinjobs.Handler;

import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.Services.IAdvertisementService;
import edu.ctl.pinjobs.Advertisement.Advertisement;
import edu.ctl.pinjobs.Advertisement.Category;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;

/**
 * Created by Filips on 4/26/2015.
 */
public class MapModel implements IMapModel {

    Context context;
    GoogleMap map;
    List<MarkerOptions> markers = new ArrayList<MarkerOptions>(); //a list of all the markers placed on the map

    public MapModel(GoogleMap map, Context context){
        this.map = map;
        this.context = context;
    }

    private void addMarker(LatLng location,String title){
        map.addMarker(new MarkerOptions().position(location).title(title));
    }

    private void addMarker(IAdvertisement ad){
        LatLng adPosition = Location.getLocationFromAddress(context,ad.getLocation());
        MarkerOptions marker = new MarkerOptions().position(new LatLng(adPosition.latitude, adPosition.longitude)).title(ad.getDescription()).icon(addCorrectCollorMarker(ad))
                .snippet("" + Location.calculateDistanceFromCurrentPosition(ad, context));

        map.addMarker(marker);
        markers.add(marker);
    }

    private LatLngBounds.Builder setMapBounds(){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        MarkerOptions closestMarker = null; //keeps track of the closest marker
        double closestDistance = -1; //Keeps track of the closest distance
        LatLng currentposition = Location.getCurrentLocation(context); //keeps track of current position

        for (MarkerOptions marker : markers){
            double markerDistance = Location.calculateDistanceFromPosition(currentposition.latitude,marker.getPosition().latitude,
                    currentposition.longitude,marker.getPosition().longitude); //calulates the distance from your current position to the marker
            if(closestMarker==null){ //Sets the first marker as the closest marker
                closestMarker = marker;
                closestDistance = markerDistance;
            }
            else if(closestDistance > markerDistance){ //chooses the closest marker
                closestDistance = markerDistance;
                closestMarker = marker;
            }
        }

        //sets the build to the closestmarker and your current position
        builder.include(currentposition);
        builder.include(closestMarker.getPosition());
        return builder;
    }

    private void addAllStartMarkers(List<IAdvertisement> list){

        for(IAdvertisement ad : list){
            addMarker(ad);
        }
    }

    //Chooses different colors depending on the Job category
    private BitmapDescriptor addCorrectCollorMarker(IAdvertisement ad){
        Category category = ad.getCategory();
        switch (category){
            case GARDEN : return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
            case LABOUR : return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
            case OTHER: return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
            default: return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
        }
    }

    private void setUpCamera(){
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(setMapBounds().build(), 200)); //sets the location to your current location
    }

    public void setUpMap(List<IAdvertisement> list){
        map.setMyLocationEnabled(true);

        if(list!=null) {
            addAllStartMarkers(list);
        }
        setUpCamera();
    }
}
