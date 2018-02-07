package chris.example.com.gm_event.view.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import chris.example.com.gm_event.GM_EventApplication;
import chris.example.com.gm_event.R;
import chris.example.com.gm_event.model.Event;
import chris.example.com.gm_event.model.EventsResponse;
import chris.example.com.gm_event.util.Constants;
import chris.example.com.gm_event.util.EventsAdapter;

public class MainActivity extends AppCompatActivity implements MainContract.View
{
    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 10;
    @Inject
    MainPresenter presenter;
    private List<Event> events;
    private String latitude, longitude;
    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;
    private Location locationCurrent;
    private FusedLocationProviderClient fusedLocationProviderClient;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GM_EventApplication.get(this).getMainComponent().inject(this);
    
        bindViews();
        
        checkPermission();
        
        presenter.attachView(this);
    }
    
    private void bindViews()
    {
        events = new ArrayList<>();
        recyclerView = findViewById(R.id.rvEvents);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    
        locationCallback = new LocationCallback()
        {
            @Override
            public void onLocationResult(LocationResult locationResult)
            {
                for (Location location : locationResult.getLocations())
                {
                    Log.d(TAG, "onLocationResult: " + location.toString());
                }
            }
        };
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }
    
    @Override
    public void showError(String error)
    {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        presenter.detachView();
    }
    
    @Override
    public void setEvents(EventsResponse eventsResponse)
    {
        events = eventsResponse.getEvents();
        eventsAdapter = new EventsAdapter(events, this);
        
        recyclerView.setAdapter(eventsAdapter);
    }
    
    @SuppressLint("MissingPermission")
    public void getLocation()
    {
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>()
                {
                    
                    
                    
                    @Override
                    public void onSuccess(Location location)
                    {
                        Log.d(TAG, "onSuccess: " + location.toString());
                        locationCurrent = location;
                        latitude = locationCurrent.getLatitude() + "";
                        longitude = locationCurrent.getLongitude() + "";
                        presenter.getEvents(Constants.OAUTH_TOKEN, latitude, longitude);
                    }
                })
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults)
    {
        switch (requestCode)
        {
            case MY_PERMISSIONS_REQUEST_LOCATION:
            {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    getLocation();
                }
                else
                {
                    Log.d(TAG, "onRequestPermissionsResult: denied");
                    checkPermission();
                }
                return;
            }
            
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    
    private void checkPermission()
    {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
        {
            
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS))
            {
                Toast.makeText(this, "This application requires " +
                        "device location permissions inorder to " +
                        "provide the proper functionality.", Toast.LENGTH_SHORT).show();
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                
            }
            else
            {
                
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
        else
        {
            getLocation();
        }
    }
    
    @Override
    public void showProgress(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    protected void onStop()
    {
        super.onStop();
        GM_EventApplication.get(this).clearMainComponent();
    }
}
