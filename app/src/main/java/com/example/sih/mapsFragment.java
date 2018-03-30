package com.example.sih;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapsFragment extends Fragment implements GoogleMap.OnMarkerClickListener {

    private OnFragmentInteractionListener listener;
    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(53.551, 9.993);
    private GoogleMap googleMap;

    public static mapsFragment newInstance() {
        return new mapsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_maps, container, false);;
        googleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        LatLng l = new LatLng(35,72);
         googleMap.addMarker(new MarkerOptions().position(l).title("Test"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40,77)).title("test2"));
        moveToCurrentLocation(l);
        return rootview;
    }


    private void moveToCurrentLocation(LatLng currentLocation)
    {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,5));
        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(5), 2000, null);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
      /* LatLng position = marker.getPosition();
        Double lat = position.latitude;
        Double lon = position.longitude;
        String url = "https://www.google.com/maps/dir/?api=1&destination=" + position.latitude + "," + position.longitude + "&travelmode=driving";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);*/
        return true;
    }

    public interface OnFragmentInteractionListener {
    }
}
