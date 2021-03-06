package edu.ctl.pinjobs.handler.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

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

import edu.ctl.pinjobs.advertisement.model.Category;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import edu.ctl.pinjobs.handler.utils.HandlerLocationUtils;
import edu.ctl.pinjobs.utils.LocationUtils;

/**
 * Created by Filips on 5/11/2015.
 */

public class MapView implements OnMapReadyCallback {
    Context context;
    GoogleMap map;
    HandlerLocationUtils locationUtils;
    Map<Marker,IAdvertisement> markerAdHashmap = new HashMap<>(); //keeps track of which ad the marker represents
    List<IAdvertisement> adList;
    List<Marker> markers = new ArrayList<Marker>(); //a list of all the markers placed on the map
    IAdvertisement zoomAd = null; //hold a new ad that will be added and camera moved and zoomed too
    GoogleMap.OnInfoWindowClickListener infoWindowClickListener;
    IAdvertisement oldAD; //If an ad is updated this is the old ad that should be removed


    public MapView(Context context, List<IAdvertisement> adList,MapFragment mapFragment,
                   GoogleMap.OnInfoWindowClickListener infoWindowClickListener){
        //used when setting up the regular map
        this.context = context;
        this.adList = adList;
        mapFragment.getMapAsync(this); //calls the onMapReady method
        this.locationUtils = new HandlerLocationUtils();
        this.infoWindowClickListener = infoWindowClickListener;
    }

    public MapView(Context context, List<IAdvertisement> adList,MapFragment mapFragment,IAdvertisement ad,
                   GoogleMap.OnInfoWindowClickListener InfoWindowClickListener){
        //Used when setting up the regular map with zoom on a new pin.
        this.context = context;
        this.adList = adList;
        mapFragment.getMapAsync(this); //calls the onMapReady method
        this.locationUtils = new HandlerLocationUtils();
        zoomAd = ad;
        this.infoWindowClickListener = InfoWindowClickListener;
    }
    public MapView(Context context, List<IAdvertisement> adList,MapFragment mapFragment,IAdvertisement ad,
                   GoogleMap.OnInfoWindowClickListener InfoWindowClickListener,IAdvertisement oldAd){
        //Used when modifying an ad and adds and zoom on the new pin and removes the old one.
        this.context = context;
        this.adList = adList;
        mapFragment.getMapAsync(this); //calls the onMapReady method
        this.locationUtils = new HandlerLocationUtils();
        zoomAd = ad;
        this.oldAD = oldAd;
        this.infoWindowClickListener = InfoWindowClickListener;
    }


    public void addMarker(LatLng location,String title){
        map.addMarker(new MarkerOptions().position(location).title(title));
    }

    private void addMarker(IAdvertisement ad,LatLng currentPosition){
        if(!ad.equals(oldAD)) { //skips the old ad that should be removed if third constructor is used
            Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(ad.getLatitude(), ad.getLongitude()))
                    .title(ad.getTitle()).icon(addCorrectCollorMarker(ad)).snippet(setSnippet(ad, currentPosition)));
            //ads the marker with correct information
            markers.add(marker);
            markerAdHashmap.put(marker, ad);
        }
    }

    private LatLngBounds.Builder setMapBounds(){
        if(adList.size()!=0) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            Marker closestMarker = null; //keeps track of the closest marker
            double closestDistance = -1; //Keeps track of the closest distance
            LatLng currentposition = LocationUtils.getCurrentLocation(context); //keeps track of current position

            for (Marker marker : markers) {
                double markerDistance = locationUtils.calculateDistanceFromPosition(currentposition.latitude, marker.getPosition().latitude,
                        currentposition.longitude, marker.getPosition().longitude); //calulates the distance from your current position to the marker
                if (closestMarker == null) { //Sets the first marker as the closest marker
                    closestMarker = marker;
                    closestDistance = markerDistance;
                } else if (closestDistance > markerDistance) { //chooses the closest marker
                    closestDistance = markerDistance;
                    closestMarker = marker;
                }
            }

            //sets the build to the closestmarker and your current position
            builder.include(currentposition);
            builder.include(closestMarker.getPosition());
            return builder;
        }
        return new LatLngBounds.Builder().include(new LatLng(57.686746, 11.921653));
        //should never occur but prevents crashing if it would, should always return builder
    }

    private void addAllStartMarkers(List<IAdvertisement> list){
        LatLng currentPos = LocationUtils.getCurrentLocation(context);
        for(IAdvertisement ad : list){
            addMarker(ad,currentPos);
        }
    }

    private BitmapDescriptor addCorrectCollorMarker(IAdvertisement ad){
        //Chooses different colors depending on the Job category
        Category category = ad.getCategory();
        switch (category){
            case GARDEN : return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
            case LABOUR : return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
            case OTHER: return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
            default: return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
        }
    }

    private void setUpCamera(){

        map.moveCamera(CameraUpdateFactory.newLatLngBounds(setMapBounds().build(), 200 ));
        //sets the camera to include your position and closest marker
    }

    public void setUpMap(List<IAdvertisement> list){
        map.setMyLocationEnabled(true);
        if(list!=null) {
            addAllStartMarkers(list);
        }if(zoomAd == null){
            setUpCamera();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        map.setOnInfoWindowClickListener(infoWindowClickListener);
        setUpMap(adList);
        if(zoomAd!=null){
            addPinAndZoom(zoomAd);
            zoomAd = null;
        }
    }

    public void addPinAndZoom(IAdvertisement ad){
        addMarker(ad,LocationUtils.getCurrentLocation(context));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom((new LatLng(ad.getLatitude(),ad.getLongitude())), 15));
    }

    private String setSnippet(IAdvertisement ad,LatLng currentPos){
        //Sets the text of the info window that isn't the title.
        String distance = "" + locationUtils.calculateDistanceFromPosition(currentPos.latitude,
                ad.getLatitude(),currentPos.longitude,ad.getLongitude());
        int index = distance.indexOf('.');
        distance = distance.substring(0, index + 2);
        return distance+" km bort"; //sets the text to amount of km from current position with one decimal
    }

    public IAdvertisement getAdFromMarker(Marker marker){
        return markerAdHashmap.get(marker);
    }
}
