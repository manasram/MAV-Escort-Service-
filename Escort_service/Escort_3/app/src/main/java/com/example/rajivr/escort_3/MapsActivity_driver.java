package com.example.rajivr.escort_3;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity_driver extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleMap.OnMarkerClickListener,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    public static final String TAG = MapsActivity_driver.class.getSimpleName();
    public static String Lati;
    public static String Longi;
    public static Double Longi2;
    public static Double Lati2;
    public static String Dummy = "abc";
    private ProgressDialog pDialog;
    Marker marker2,marker1,marker3,marker4;
    /*
     * Define a request code to send to Google Play services
     * This code is returned in Activity.onActivityResult
     */
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    public double currentLatitude;
    public double currentLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();


            }
        }
    }

    private void inserttoDB(Double lati2, Double longi2) {
        final String link = "http://192.168.0.12/wireless/ex.php?Latitude="+lati2+"&Longitude="+longi2;

        AsyncTask<Void, Void, String> async = new AsyncTask<Void, Void, String>() {
            public HttpResponse response;

            @Override
            protected String doInBackground(Void... params) {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet(link);

                try {
                    response = client.execute(request);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return response.toString();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        };
        async.execute();

    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    private void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());

         currentLatitude = location.getLatitude();
         currentLongitude = location.getLongitude();
        Longi2=currentLongitude;
        Lati2=currentLatitude;

        //Toast.makeText(getApplicationContext(), "Your Longi2 is " + Longi2,  Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), "Your Lati2 is " + Lati2,  Toast.LENGTH_SHORT).show();
        // new code this is where the co -ordinates are stored
        Lati = Double.toString(currentLatitude);
        Longi = Double.toString(currentLongitude);



        //Toast.makeText(getApplicationContext(), "Your Longi is " + Longi,  Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), "Your Lati is " + Lati,  Toast.LENGTH_SHORT).show();

        //First Marker
        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        //second marker
        final LatLng latLng2 = new LatLng(32.734671, -97.114912);


        // inserting to db
        inserttoDB(currentLatitude, currentLongitude);
        //this line was commented

        marker1 = mMap.addMarker(new MarkerOptions().position(new LatLng(currentLatitude, currentLongitude)).title("Current Location"));
        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("Escort");
        mMap.addMarker(options);



        //second marker
        marker2= mMap.addMarker(new MarkerOptions().position(new LatLng(currentLatitude,currentLongitude)).title("Future Location"));
        MarkerOptions options2 = new MarkerOptions()
                .position(latLng2)
                .title("Rider1").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        mMap.addMarker(options2);

        //third marker
         marker3 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(currentLatitude,currentLongitude))
                .title("rider2").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));


        marker4 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(currentLatitude,currentLongitude))
                .title("rider3").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker arg0) {
                if (arg0.getTitle().equals("Rider1")) // if marker source is clicked
                {
                    final Intent intent = new
                            Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" +
                            "saddr=" + currentLatitude + "," + currentLongitude + "&daddr=" + currentLatitude + "," +
                            currentLatitude));
                    intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
                    startActivity(intent);
                }

                if (arg0.getTitle().equals("rider2")) // if marker source is clicked
                {
                    final Intent intent = new
                            Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" +
                            "saddr=" + currentLatitude + "," + currentLongitude + "&daddr=" + currentLatitude + "," +
                            currentLatitude));
                    intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
                    startActivity(intent);
                }

                if (arg0.getTitle().equals("rider3")) // if marker source is clicked
                {
                    final Intent intent = new
                            Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" +
                            "saddr=" + currentLatitude + "," + currentLongitude + "&daddr=" + currentLatitude + "," +
                            currentLatitude));;
                    intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
                    startActivity(intent);
                }

                return true;
            }

        });

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));





    }

    public boolean onMarkerClick(final Marker marker) {

        if (marker.equals(marker2))
        {
            final Intent intent = new
                Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" +
                    "saddr=" + currentLatitude + "," + currentLongitude + "&daddr=" + currentLatitude + "," +
                    currentLatitude));
        intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
        startActivity(intent);
            return true;
        }
        else return false;
    }



    //error was here
    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        else {
            handleNewLocation(location);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public static String getMyString(){
        return Longi;
    }

    public static String getMyString2(){
        return Lati;
    }



    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }
}