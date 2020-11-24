package com.example.myprojekmaps;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InvalidClassException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    ProgressBar pbMap;
    Spinner spWisata;
    String[] sWisata = {"Masjid", "restaurant", "atm", "bank", "SEKOLAH"
            , "hospital", "university", "post_office", "Police"};
    String xWisata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setID();

        spWisata.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object itemDB = parent.getItemAtPosition(pos);
                xWisata = itemDB.toString();
                SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                fragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        mMap = googleMap;
                        initMap();
                    }
                });
                startProg();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initMap() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 115);
            return;
        }

        if (mMap != null) {
            startProg();
            mMap.setMyLocationEnabled(true);

            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(provider);

            if (location != null) {
                GoogleMap googleMap = null;
                onMapReady(googleMap);
            } else
                stopProg();
            locationManager.requestLocationUpdates(provider, 20000, 0, (LocationListener) this);
        }
    }

    private void stopProg() {
    }
//    public void onLocationChanged(Location location) {
//        double mLatitude = location.getLatitude();
//        double mLongitude = location.getLongitude();
//        LatLng latLng = new LatLng(mLatitude, mLongitude);
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
//
//        String sb = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
//                "location=" + mLatitude + "," + mLongitude +
//                "&radius=20000" +
//                "&types=" + xWisata +
//                "&key=" + getResources().getString(R.string.google_maps_key);
//        new MapsActivity.PlacesTask().execute(sb);
//        stopProg();
//    }
//
//    private void stopProg() {
//    }

    private void setID() {
        pbMap = findViewById(R.id.pbMap);
        spWisata = findViewById(R.id.spWisata);
        ArrayAdapter<String> adpWisata = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sWisata);
        adpWisata.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spWisata.setAdapter(adpWisata);
    }

    private void startProg() {
        pbMap.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-2.9547949, 104.6929235);
        mMap.addMarker(new MarkerOptions().position(sydney).title("PALEMBANG"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        startProg();
    }
 
//    @SuppressLint("StaticFieldLeak")
//    private class PlacesTask extends AsyncTask<String, Integer, String> {
//        @Override
//        protected String doInBackground(String... url) {
//            String data = null;
//            try {
//                data = downloadUrl(url[0]);
//            } catch (Exception e) {
//                stopProg();
//                e.printStackTrace();
//            }
//            return data;
//        }
//    }

    private String downloadUrl(String strUrl) {
        String data = "";
        InputStream iStream;
        HttpURLConnection urlConnection;
        try {
            URL url = new URL(strUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            br.close();
            iStream.close();
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}