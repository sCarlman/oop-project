package edu.ctl.pinjobs.Handler;

import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ctl.pinjobs.Advertisement.Advertisement;
import edu.ctl.pinjobs.Advertisement.AndroidAdvertisement;
import edu.ctl.pinjobs.Advertisement.Category;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import edu.ctl.pinjobs.Utils.LocationUtils;

/**
 * Created by Filips on 5/11/2015.
 */

public class MapView implements OnMapReadyCallback,GoogleMap.OnInfoWindowClickListener {
    Context context;
    GoogleMap map;
    HandlerLocationUtils locationUtils;
    Map<Marker,IAdvertisement> markerAdHashmap = new HashMap<>();
    List<IAdvertisement> adList;
    List<Marker> markers = new ArrayList<Marker>(); //a list of all the markers placed on the map

    public MapView(Context context, List<IAdvertisement> adList,MapFragment mapFragment){
        this.context = context;
        this.adList = adList;
        mapFragment.getMapAsync(this);
        this.locationUtils = new HandlerLocationUtils();
    }

    public void addMarker(LatLng location,String title){
        map.addMarker(new MarkerOptions().position(location).title(title));
    }

    private void addMarker(IAdvertisement ad){
        LatLng adPosition = locationUtils.getLocationFromAddress(context, ad.getLocation());
        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(adPosition.latitude, adPosition.longitude)).title(ad.getDescription()).icon(addCorrectCollorMarker(ad))
                .snippet("" + locationUtils.calculateDistanceFromCurrentPosition(ad, context)));

        markers.add(marker);
        markerAdHashmap.put(marker,ad);
    }

    private LatLngBounds.Builder setMapBounds(){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Marker closestMarker = null; //keeps track of the closest marker
        double closestDistance = -1; //Keeps track of the closest distance
        LatLng currentposition = LocationUtils.getCurrentLocation(context); //keeps track of current position

        for (Marker marker : markers){
            double markerDistance = locationUtils.calculateDistanceFromPosition(currentposition.latitude, marker.getPosition().latitude,
                    currentposition.longitude, marker.getPosition().longitude); //calulates the distance from your current position to the marker
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        map.setOnInfoWindowClickListener(this);
        setUpMap(adList);
    }

    public void addPinAndZoom(IAdvertisement ad){
        addMarker(ad);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(locationUtils.getLocationFromAddress(context,ad.getLocation()), 15));
    }

    @Override
    public void onInfoWindowClick(Marker marker){
        System.out.println(markerAdHashmap.get(marker).getTitle());
        HandlerActivity handlerController = new HandlerActivity();
        IAdvertisement ad = markerAdHashmap.get(marker);
        AndroidAdvertisement androidAD = new AndroidAdvertisement(ad);
        handlerController.openDetailedAdView(context,androidAD);

    }
}
