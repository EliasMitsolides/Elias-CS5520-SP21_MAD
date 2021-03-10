package edu.neu.madcourse.numad21s_eliasmitsolides;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Hello World! text
    //An About button that displays your name and email when clicked
    //A unique icon that gets placed on the phone’s app list in the launcher
    //Anything else you want to add

    //This seems to be where the first stuff happens?
    //Where the creation, bootup, and initial showing of the pixel takes place

    // Register the permissions callback, which handles the user's response to the
    // system permissions dialog. Save the return value, an instance of
    // ActivityResultLauncher, as an instance variable.
    private int permissionGranted;
    //this will be changed/called when 'requestPermissionLauncher.launch' in onCreate returns
    //   here I edit the permission granted int of mine
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    permissionGranted = 1;
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    Toast.makeText(MainActivity.this, "Geolocation Permission Denied By User", Toast.LENGTH_SHORT).show();
                    permissionGranted = 0;

                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            this.permissionGranted = 1;
        }else{
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION );
        }
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }




    public void buttonToLetter(View view)
    {
        Intent intentFromMainToLetter = new Intent(this, MainActivityLetter.class);
        startActivity(intentFromMainToLetter);

    }

    public void buttonToList(View view)
    {
        Intent intentFromMainToLink = new Intent(this, MainActivityUrlList.class);
        startActivity(intentFromMainToLink);
    }

    public void buttonToLatLong(View view)
    {
        //RequestPermission();
        if (this.permissionGranted == 1) {
            Intent intentFromMainToLL = new Intent(this, ActivityLatLong.class);
            startActivity(intentFromMainToLL);
        }
        else{
            Toast.makeText(MainActivity.this, "Geolocation Permission Was Not Granted", Toast.LENGTH_SHORT).show();
        }

    }

    public void buttonToPictures(View view)
    {
        Intent intentFromMainToPictures = new Intent(this, ActivityPictureLibrary.class);
        startActivity(intentFromMainToPictures);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}