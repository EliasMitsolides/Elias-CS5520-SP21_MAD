package edu.neu.madcourse.numad21s_eliasmitsolides;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ActivityLatLong extends AppCompatActivity {

    //it all stems from the LocationManager, it'll have the lats/longs and other metrics
    private LocationManager locationManager;
    private Location currentLocation;
    double latitude;
    double longitude;
    TextView latitudeTV;
    TextView longitudeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lat_long);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        if (currentLocation == null)
        {
            Toast.makeText(ActivityLatLong.this, "User Here Despite No Geo Permission, Wacky!!", Toast.LENGTH_SHORT).show();
        }else{
            latitude = currentLocation.getLatitude();

            String latitudeString = "Your Current Latitude: " + latitude;

            longitude = currentLocation.getLongitude();
            String longitudeString = "Your Current Latitude: " + longitude;

            latitudeTV = findViewById(R.id.latitude_tv);
            latitudeTV.setText(latitudeString);

            longitudeTV = findViewById(R.id.longitude_tv);
            longitudeTV.setText(longitudeString);
        }

    }
}