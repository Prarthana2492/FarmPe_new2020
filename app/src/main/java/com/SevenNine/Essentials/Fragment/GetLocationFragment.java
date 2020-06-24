package com.SevenNine.Essentials.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.Essentials.Activity.GPSTracker;
import com.SevenNine.Essentials.R;
import com.SevenNine.Essentials.SessionManager;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;


import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class GetLocationFragment extends Fragment {
   /* implements
} OnMapReadyCallback {*/

    LocationManager manager;
    boolean permission_navigated = false, gps_navigated = false, got_latlong = false;
    int attempts = 0, permission_attempt = 0, gps_attempt = 0;
    private Location location;
    Intent intent;
    public static String livelocation;
    String loc;
    public static double latitude, longitude;

    TextView current_address;
    Fragment selectedFragment;
    SessionManager sessionManager;
    LocationManager locationManager;

    LocationListener locationListener;
    boolean valid = true;
    GPSTracker gps;
    LinearLayout nomap;
    boolean loc_frst_set;
    // private LatLng currLatLong=null;
    private GoogleMap currentgoogleMap;
    Geocoder geocoder;
    List<Address> addresses;
    String currentLocation;
    AutocompleteSupportFragment autocompleteFragment;
    String nav;
    LinearLayout back_feed;
    /* @Override
        public void onBackPressed() {
          Intent i = new Intent(GetLocationFragment.this, com.FarmPe.Ads.Activity.HomePage_With_Bottom_Navigation.class);
          startActivity(i);
        }*/
    public static GetLocationFragment newInstance() {
        GetLocationFragment fragment = new GetLocationFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.get_latitude_longitude, container, false);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
      /*  intent = getIntent();
        Bundle b = intent.getExtras();
        if (b!=null) {
            Log.d("Navfrom", b.getString("NavigationFrom"));
            nav = b.getString("NavigationFrom");
        }
        */
        //String nav = intent.getExtras().getString("NavigateFrom");
        //nomap = findViewById(R.id.nomap);
        current_address = view.findViewById(R.id.curr_address);
        sessionManager = new SessionManager(getActivity());
        back_feed=view.findViewById(R.id.back_feed);
       /* ImageView current = findViewById(R.id.current);
        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("gdghd");

            }
        });*/
        if (!Places.isInitialized()) {
            Places.initialize(getActivity(), "AIzaSyDoPoXCJKf9xGg809DWT2d9C7v1Upe0R00");
        }
// Initialize the AutocompleteSupportFragment.
        autocompleteFragment = (AutocompleteSupportFragment)
                getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                loc = place.getName();
                sessionManager.savelocation(place.getName());
                Bundle bundle = new Bundle();
                bundle.putString("homelocation", loc);
               /* selectedFragment = Home_Menu_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("home");
                transaction.commit();*/
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }
                return false;
            }
        });

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });




        current_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentAddress();
            }
        });
        manager = (LocationManager) getActivity()
                .getSystemService(Context.LOCATION_SERVICE);
        checkLocationPermission();

        return view;
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            //ask for permission pop up
            askForPermission();

        } else {

            if (permission_navigated) permission_navigated = false;

            //check if gps is turned on
            // checkGPSTurnedOn();

        }
    }
    @SuppressLint("NewApi")
    public void getCurrentAddress() {
        // Get the location manager
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (locationManager != null) {

            try {
                locationManager = (LocationManager)
                        getActivity(). getSystemService(Context.LOCATION_SERVICE);
            } catch (Exception ex) {
                Log.i("msg", "fail to request location update, ignore", ex);
            }
            if (locationManager != null) {
                if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                location = locationManager
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            Geocoder gcd = new Geocoder(getActivity(),
                    Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(location.getLatitude(),
                        location.getLongitude(), 1);
                if (addresses.size() > 0) {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String locality = addresses.get(0).getLocality();
                    String subLocality = addresses.get(0).getSubLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();
                    if (subLocality != null) {

                        currentLocation = locality + "," + subLocality;
                        System.out.println("effd"+currentLocation);
                        current_address.setText(currentLocation);
                        sessionManager.savelocation(currentLocation);
                       /* selectedFragment = Home_Menu_Fragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.addToBackStack("home");
                        transaction.commit();*/
                    } else {
                        currentLocation = locality;
                    }
                    // current_locality = locality;
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Toast.makeText(getActivity(), Constants.ToastConstatnts.ERROR_FETCHING_LOCATION, Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
// dialogue box event handling

        switch (requestCode) {
            case 99: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //check if gps is turned on
                    //   checkGPSTurnedOn();
                }
                else {
                    permission_attempt++;
                    askForPermission();
                }
            }
        }
    }
    private void askForPermission()
    {
        if(permission_attempt<=2)
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // if location permission is not on will be promfted for first time
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 99);

            } else {

                //user pressed dont show again
                permissionDeclined();

            }
        else
            Toast.makeText(getActivity(),"Enter Location Manually", Toast.LENGTH_SHORT).show();
    }

    private void permissionDeclined()
    {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Allow Location Access");
        alertDialogBuilder.setMessage("You have declined location access permission please turn it on to navigate into the App.");
        alertDialogBuilder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                permission_navigated=true;
                //will navigate to permission section to check location
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        alertDialogBuilder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                permission_attempt++;
                dialogInterface.dismiss();

                if(permission_attempt!=2)
                    alertDialogBuilder.show();
                Toast.makeText(getActivity(),"Enter Location Manually", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.show();
    }






/*    private void getLatLongfromPlaceId(String placeid)
    {
        final ProgressDialog  progressDialog = ProgressDialog.show(GetLocationFragment.this, "",
                "Fetching LatLong. Please wait...");

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "https://maps.googleapis.com/maps/api/place/details/json?placeid="+placeid+"&key=AIzaSyDgQSmB4zuUBFUv4rzBhY_e-ZRygBRVT4U",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.cancel();

                        try {
                            JSONObject main = new JSONObject(response);

                            JSONObject getresult = main.getJSONObject("result");

                            JSONObject getgeometry = getresult.getJSONObject("geometry");

                            JSONObject getlocation = getgeometry.getJSONObject("location");

                            Toast.makeText(GetLocationFragment.this,getlocation.getString("lat")+"  "+getlocation.getString("lng"),Toast.LENGTH_SHORT).show();

                            latitude=getlocation.getDouble("lat");

                            longitude = getlocation.getDouble("lng");

                            mapFragment.getMapAsync(GetLocationFragment.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



System.out.println("lattttt = "+response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressDialog.cancel();

                    }
                }

        );

        VolleySingletonQuee.getInstance(getBaseContext()).addToRequestQueue(stringRequest);

    }*/

}

