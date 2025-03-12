package example.application;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.*;



import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.maps.android.heatmaps.WeightedLatLng;
import example.application.databinding.ActivityMapsBinding;

import com.google.android.gms.location.FusedLocationProviderClient;
import example.data.StaticUserDAO;
import example.data.TouristDestinations.Beach;
import example.data.TouristDestinations.Landmark;
import example.data.TouristDestinations.RestaurantCafe;
import example.data.TouristDestinations.TouristDestination;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jetbrains.annotations.NotNull;
import example.data.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.maps.android.heatmaps.HeatmapTileProvider;
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FloatingActionButton mainViewButton;
    private Button checkInButton;

    private static final String TAG = MapsActivity.class.getSimpleName();
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private Location lastKnownLocation;
    public List<TouristDestination> touristDestinations;
    private LatLng mLastKnownLocation;
    
    StaticUserDAO staticUserDAO = RegisterActivity.staticUserDAO;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewButton = findViewById(R.id.mainViewButton);

        mainViewButton.setOnClickListener(v -> onMainViewButtonClick());

        checkInButton = findViewById(R.id.checkInButton);

        checkInButton.setOnClickListener(v -> onCheckInButtonClick());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        // The SupportMapFragment object manages the life cycle of the map and
        // is the parent element of the app's UI.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Schedule the location checking task to run every 5 seconds
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::getUserLocation, 0, 5, TimeUnit.SECONDS);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("PotentialBehaviorOverride")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // The GoogleMap object provides access to the map data and view.
        // This is the main class of the Maps SDK for Android.
        mMap = googleMap;

        touristDestinations = readTouristDestinationItems(R.raw.popular_destinations);

        for(TouristDestination td: touristDestinations) {
            LatLng temp = new LatLng(td.getXLocation(), td.getYLocation());
            mMap.addMarker(new MarkerOptions().position(temp).title(td.getName()).snippet(
                    "Point Value : " + td.getPointVal()
            ));
        }

        // In cases where there is no GPS location, Default to QUT.
        // If location exists, camera position overridden by updateMap method
        LatLng qut = new LatLng(-27.477251216558134, 153.02818746747138);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(qut));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));

        // Check if the app has permission to access the user's location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // If the permission is not granted, permission is requested.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            //If permission is granted, gets user's location
        } else {
            getUserLocation();
            mMap.setMyLocationEnabled(true);
        }
        //Create Heat Map feature - COD46
        createHeatMap();


        // Set the marker click listener
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                launchDestinationInfo(marker);//On Marker click, launches the destination info page.
                return true;
            }
        });
    }

    //Launch Destination Info screen when clicking on a marker
    private void launchDestinationInfo(Marker marker){
        Intent intent = new Intent(getApplicationContext(), DestinationInfoActivity.class);

        // Pass additional data to the new activity. May need to refactor destination info activity to include more details.
        intent.putExtra("marker_text", marker.getTitle());

        // Start the new activity
        startActivity(intent);
    }

    private void onMainViewButtonClick() {
        // Transition to the main view
        launchMainView();
        finish();
    }

    private void launchMainView() {
        // Create new Activity that leads to MainViewActivity.
        Intent intent = new Intent(this, MainViewActivity.class);

        // Original User name from the user class instantiated in LoginActivity.
        Intent originalIntent = getIntent();
        String currentUser = originalIntent.getStringExtra("CURRENT_USER");
        intent.putExtra("CURRENT_USER", currentUser);

        startActivity(intent);
    }

    //Method to get the user's current location and show a Marker there with the label of "Your Location"
    private void getUserLocation() {
        try {
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new com.google.android.gms.tasks.OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location){
                            if (location != null) {
                                // Add a marker for the user's current location
                                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

                                // Update the Current Location Map marker and blue dot.
                                updateMap(currentLocation);
                            }
                        }
                    });
            //Error message in the case location is turned off.
        } catch (SecurityException e) {
            Log.e(TAG, "Error retrieving Location. Please ensure location is turned on." + e.getMessage());
        }
    }

    private void updateMap(LatLng currentLocation) {
        if (mMap != null && (mLastKnownLocation == null || !mLastKnownLocation.equals(currentLocation))) {
            // User's location has changed
            mLastKnownLocation = currentLocation;
            // Move the camera to where the user is located.

            // TODO(???): App will crash if this is uncommented due to mMap being null on return to activity.
            //  Update: Added mMap != null to mitigate crash.
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
        }
    }

    //Creates a Heat Map of popular beaches from the JSON file - Implementation of COD46
    //Not complete yet.
    private void createHeatMap(){
        List<WeightedLatLng> popularLocations = null;
        // Get the data: latitude/longitude positions of popular beaches.
        popularLocations = readHeatMapItems(R.raw.popular_destinations);

        // Create a heat map tile provider, passing it the latitudes and longitudes of the destinations.
        HeatmapTileProvider provider = new HeatmapTileProvider.Builder().weightedData(popularLocations).build();
        provider.setRadius(100);
        // Add a tile overlay to the map, using the heat map tile provider.
        TileOverlay overlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(provider));
    }

   private ArrayList<WeightedLatLng> readHeatMapItems(int resource) {
       ArrayList<WeightedLatLng> resultList = new ArrayList<WeightedLatLng>();
       try {
           JSONArray array = getJsonArray(resource);

           for (int i = 0; i < array.length(); i++) {
               JSONObject object = array.getJSONObject(i);
               WeightedLatLng weightedLatLng = createWeightedLatLng(object);
               resultList.add(weightedLatLng);
           }
       } catch (JSONException e) {
           Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show();
       }

       return resultList;
   }



    // Add points to user's account if the user after checking in at a destination
    private void onCheckInButtonClick(){
        // Get the current user
        Intent originalIntent = getIntent();
        String userEmail = staticUserDAO.getUserName(originalIntent.getStringExtra("CURRENT_USER")).getEmail();
        currentUser = staticUserDAO.getUser(userEmail);
        // Get a list of all destinations the user is in range of and add points
        isNearDestination();
        // TODO(TOM) Prevent unlimited check-ins

    }


    /* Method to check which tourist destinations the user is nearby to

    Asynchronous function to add point value of tourist destination user is
    closest to.
     */
    private void isNearDestination(){

        // This try catch is Asynchronous.
        try {
            Task<Location> locationResult = fusedLocationClient.getLastLocation();
            // last location listener
            locationResult.addOnCompleteListener(task -> {
                // Destination gotten
                if (task.isSuccessful()) {
                    lastKnownLocation = task.getResult();
                    if (lastKnownLocation != null) {
                        LatLng userLocation = new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude());
                        // Add all destinations user is nearby to in an arraylist
                        ArrayList<TouristDestination> nearbyDestinations = filterDestinations(userLocation);
                        addPointsToUser(nearbyDestinations);
                    }
                }
            });
        } catch (SecurityException e){
            Log.e(TAG, "Error receiving location. Please ensure location is turned on." + e.getMessage());
        }
    }

    /* Add Points to the user if they are nearby to a Tourist Destination.

    Put a toast message to notify user of how many total points were added.
     */
    private void addPointsToUser(ArrayList<TouristDestination> nearbyDestinations) {

        int pointsAdded = 0;
        // Get the point value of all Tourist Destinations in ArrayList.
        for (TouristDestination td : nearbyDestinations) {
            currentUser.addPoints(td.getPointVal());
            pointsAdded = pointsAdded + td.getPointVal();
        }

        //TODO(anyone): Need opinion regarding toast or snackbar.

        // Snackbar to show total points.
        Snackbar.make(
                findViewById(R.id.checkInButton),
                "Added: "+ pointsAdded + " Points. Total:" +  currentUser.getPoints(),
                Snackbar.LENGTH_SHORT
        ).show();
//        Toast.makeText(this, currentUser.getPoints(), Toast.LENGTH_SHORT).show();
    }

    private ArrayList<TouristDestination> filterDestinations(LatLng userLocation) {
        // Initialize an array to populate with nearby destinations
        ArrayList<TouristDestination> destinations = new ArrayList<TouristDestination>();
        // Go through all tourist destinations and get the closest to the user.
        for (TouristDestination destination : touristDestinations) {
            if (inRange(userLocation, destination)) {
                destinations.add(destination);
            }
        }
        return destinations;
    }

    //Converts degrees latitude/longitude to meters
    private double[] latlngToMeters(LatLng latLng){
        double[] latlngm = new double[2];
        latlngm[0] = latLng.latitude* 111139;
        latlngm[1] = latLng.longitude* 111139;
        return latlngm;
    }

    //Return true is user is within 50m of a tourist destination
    private boolean inRange(LatLng userLocation, TouristDestination destination){
        LatLng dest = new LatLng(destination.getXLocation(), destination.getYLocation());
        double[] dest_m = latlngToMeters(dest);
        double[] user_m = latlngToMeters(userLocation);

        return Math.abs(dest_m[0] - user_m[0]) <= 50 && Math.abs(dest_m[1] - user_m[1]) <= 50;
    }

    private WeightedLatLng createWeightedLatLng(JSONObject object) throws JSONException {
        double lat = object.getDouble("xCoordinate");
        double lng = object.getDouble("yCoordinate");
        LatLng coordinate = new LatLng(lat, lng);
        return new WeightedLatLng(coordinate, 100);
    }

    private ArrayList<TouristDestination> readTouristDestinationItems(int resource) {
        ArrayList<TouristDestination> touristDests = new ArrayList<TouristDestination>();

        try {
            JSONArray array = getJsonArray(resource);

            // Iterate through the JSON array and create TouristDestination objects based on their type
            for (int i = 0; i < array.length(); i++) {
                TouristDestination td = null;
                JSONObject obj = array.getJSONObject(i);

                // Create a TouristDestination object based on the type of destination
                switch(obj.getString("type")) {
                    case "Landmark":
                        td = new Landmark(obj);
                        break;
                    case "Beach":
                        td = new Beach(obj);
                        break;
                    case "RestaurantCafe":
                        td = new RestaurantCafe(obj);
                        break;
                    default:
                        break;
                }

                // Add the TouristDestination object to the list if it was successfully created
                if(td != null) {
                    touristDests.add(td);
                }
            }
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of tourist destinations.", Toast.LENGTH_LONG).show();
        }

        return touristDests;
    }

   private JSONArray getJsonArray(int resource) throws JSONException {
       InputStream inputStream = getResources().openRawResource(resource);
       String json = new Scanner(inputStream).useDelimiter("\\A").next();
       return new JSONArray(json);
   }
}
