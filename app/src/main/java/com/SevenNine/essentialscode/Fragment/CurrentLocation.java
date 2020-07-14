package com.SevenNine.essentialscode.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
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
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.essentialscode.Activity.GPSTracker;
import com.SevenNine.essentialscode.Adapter.CartDetailsAdapter;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Crop_Post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class CurrentLocation extends Fragment implements
        OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    LocationManager manager;
    boolean permission_navigated = false,gps_navigated = false,got_latlong=false;
    int attempts=0,permission_attempt=0,gps_attempt=0;

    Intent intent;

    public static double latitude,longitude=0;

    TextView current_address,add_address;
    public static String current_addr=null;

    SessionManager sessionManager;

    boolean valid = true;
    Button confirm;
    String status;
    Fragment selectedFragment;

    LinearLayout nomap;

    boolean loc_frst_set;

    private LatLng currLatLong=null;

    private GoogleMap currentgoogleMap;

    SupportMapFragment mapFragment;
    String nav;
    public static CurrentLocation newInstance() {
        CurrentLocation fragment = new CurrentLocation();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.get_latitude_longitude, container, false);
       // displayLocationSettingsRequest(getActivity());
        nomap =view.findViewById(R.id.nomap);
        current_address=view.findViewById(R.id.curr_address);
        confirm=view.findViewById(R.id.cl);
        add_address=view.findViewById(R.id.add_address);

        ImageView current = view.findViewById(R.id.current);
        //  resutText = (TextView) view.findViewById(R.id.curr_address);
        sessionManager = new SessionManager(getActivity());


        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = SelectShippingAddress.newInstance();
                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("track1");
                transaction.commit();
            }
        });
        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("gdghd");
                if(currLatLong!=null) {
                    current_address.setText(getAddressFromLatLong(currLatLong.latitude, currLatLong.longitude));
                    //currentgoogleMap.addMarker(new MarkerOptions().position(india).title("India"));
                    System.out.println("gdghddddd");
                    //  currentgoogleMap.animateCamera(CameraUpdateFactory.newLatLng(currLatLong));


                    currentgoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currLatLong, 17f));
                }
            }
        });

       // if (!Places.isInitialized()) {
           // Places.initialize(getActivity().getApplicationContext(), "AIzaSyAXsifFPLFyNsK0YxpmcMld89LDRDC2SGI");
      //  }

// Initialize the AutocompleteSupportFragment.
        String apiKey = "AIzaSyAXsifFPLFyNsK0YxpmcMld89LDRDC2SGI";
// Initialize the SDK
        Places.initialize(getActivity(), apiKey);

        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setCountry("IN");
        autocompleteFragment.setHint("Enter your Location");

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.NAME, Place.Field.LAT_LNG));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(ContentValues.TAG, "Place: " + place.getName() + ", " + place.getId());
                String place_name=place.getName();
                System.out.println("dddd = "+place.getLatLng().longitude);

                latitude=place.getLatLng().latitude;

                longitude = place.getLatLng().longitude;

                mapFragment.getMapAsync(CurrentLocation.this);

                loc_frst_set=false;

                got_latlong=true;

                /*Intent intent=new Intent(YourLocationActivity.this, LandingPageActivity.class);
                intent.putExtra("PlaceName",place_name);
                startActivity(intent);*/

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(ContentValues.TAG, "An error occurred: " + status);
            }
        });


        //fetching places related ends



// map related
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);



      //  Button continue_ =view.findViewById(R.id.cl);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_addr=current_address.getText().toString();
                System.out.println("currentaddresss "+current_addr);
                sessionManager.saveDefaultAddress(current_addr,"");

                if(got_latlong & valid &
                        !current_address.getText().toString().contains("Ocean") &
                        !current_address.getText().toString().contains("Sea")) {

                    sessionManager.storeLatLong(latitude+"",longitude+"");
                    CheckoutDetails();
                    /*Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);*/



                }
                else {
             /*       if(attempts%2!=0)
                      //  askTogetHighAccuracy();else
                    checkGPSTurnedOn();*/
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setTitle("Not getting Location?");
                    alertDialogBuilder.setMessage("Click try again to get Your Location");
                    alertDialogBuilder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),"", "Fetching your Current loaction. Please wait...");
                            final int[] count = {5};

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    final GPSTracker gpsTracker = new GPSTracker(getActivity().getBaseContext());
                                    final Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            count[0]--;

                                            if(gpsTracker.longitude>0) {

                                                got_latlong=true;

                                                currLatLong = new LatLng(gpsTracker.latitude,gpsTracker.longitude);
                                                latitude=gpsTracker.latitude;
                                                longitude=gpsTracker.longitude;
                                                loc_frst_set=false;
                                                mapFragment.getMapAsync(CurrentLocation.this);
                                            }
                                            if(got_latlong |  count[0]==0) {
                                                progressDialog.dismiss();
                                                handler.removeCallbacksAndMessages(null);

                                            }
                                            else
                                                handler.postDelayed(this, 500);
                                        }
                                    }, 1000);  //the time is in miliseconds

                                }
                            });

                        }
                    });
                    alertDialogBuilder.setNegativeButton("Enter Manually", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.show();
                    // Toast.makeText(getBaseContext(),"Enter Location Manually",Toast.LENGTH_SHORT).show();
                }
            }
        });
        manager = (LocationManager) getActivity().getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);
        checkLocationPermission();


        return view;
    }

    private void checkLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //ask for permission pop up
            askForPermission();
        } else {
            if(permission_navigated)permission_navigated=false;
            //check if gps is turned on
            checkGPSTurnedOn();
        }
    }
    @SuppressLint("MissingPermission")
    private void checkGPSTurnedOn()
    {
        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        } else {
            if (gps_navigated) gps_navigated = false;

            final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),"", "Fetching your Current loaction. Please wait...");
            final int[] count = {5};

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final GPSTracker gpsTracker = new GPSTracker(getActivity().getBaseContext());
                    attempts++;
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            count[0]--;

                            if(gpsTracker.longitude>0) {

                                got_latlong=true;
                                currLatLong = new LatLng(gpsTracker.latitude,gpsTracker.longitude);
                                latitude=gpsTracker.latitude;
                                longitude=gpsTracker.longitude;

                                loc_frst_set=false;

                                mapFragment.getMapAsync(CurrentLocation.this);

                            }

                            if(got_latlong |  count[0]==0) {
                                progressDialog.dismiss();
                                handler.removeCallbacksAndMessages(null);

                            }
                            else
                                handler.postDelayed(this, 500);
                        }
                    }, 1000);  //the time is in miliseconds

                }
            });

        }
    }

    @Override
    public void onResume() {

        super.onResume();

        if(permission_navigated & permission_attempt<2)
            checkLocationPermission();

        if(gps_navigated & gps_attempt<2)
            checkGPSTurnedOn();

/*            if(highAccuracy_navigated)
                checkGPSTurnedOn();*/
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
                    checkGPSTurnedOn();

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
            Toast.makeText(getActivity().getBaseContext(),"Enter Location Manually",Toast.LENGTH_SHORT).show();
    }

    private void buildAlertMessageNoGps() {
        gps_attempt++;
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Turn on GPS?");
        alertDialogBuilder.setMessage("Your GPS seems to be disabled, do you want to enable it?");
        alertDialogBuilder.setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                gps_navigated=true;
                //will be navigated to turn on gps
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));

            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

                if(gps_attempt<2)
                    alertDialogBuilder.show();
                else
                    Toast.makeText(getActivity().getBaseContext(),"Enter Location Manually",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.show();

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
                Toast.makeText(getActivity().getBaseContext(),"Enter Location Manually",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.show();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        currentgoogleMap=googleMap;
        nomap.setVisibility(View.INVISIBLE);

        LatLng india = new LatLng(latitude, longitude);

        current_address.setText(getAddressFromLatLong(latitude,longitude));
        //currentgoogleMap.addMarker(new MarkerOptions().position(india).title("India"));

        currentgoogleMap.moveCamera(CameraUpdateFactory.newLatLng(india));

        // for adding + and - to map to zoom in and out
        currentgoogleMap.getUiSettings().setZoomControlsEnabled(true);

        currentgoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(india, 17f));



        currentgoogleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                if(loc_frst_set) {

                    LatLng midLatLng = currentgoogleMap.getCameraPosition().target;

                    current_address.setText(getAddressFromLatLong(midLatLng.latitude, midLatLng.longitude));

                    latitude=midLatLng.latitude;
                    longitude=midLatLng.longitude;

                }
                else
                    loc_frst_set=true;

            }
        });


    }

    private String getAddressFromLatLong(Double Latitude,Double Longitude)
    {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getActivity(), Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(Latitude, Longitude, 1);
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            valid=true;
            return address;

        } catch (IOException e) {
            valid=false;
            return "Address Not Available for this Location";

        }
        catch (IndexOutOfBoundsException e)
        {
            valid=false;
            return "Please choose a valid location";
        }
    }

    private void CheckoutDetails() {
        try {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("CartCheckOutId", 0);
            jsonObject.put("TotalPrice", CartDetailsAdapter.total_prise_st);
            jsonObject.put("TotalCartItems", CartDetailsAdapter.total_cart_items);
            jsonObject.put("CartProductListId", CartDetailsAdapter.strlist);
            jsonObject.put("AddressId", "");
          //  jsonObject.put("CustLongitude",longitude);
          //  jsonObject.put("CustLatitude",latitude);
            jsonObject.put("CustAddress",current_addr);
            jsonObject.put("UserId", sessionManager.getRegId("userId"));
            System.out.println("Add_New_AddresssssssssssssssssjsonObject11" + jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.AddUpateCartCheckoutDetails, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {


                    System.out.println("Add_New_Addresssssssssssssssssllllllllllllllllll888" + result);
                    try {

                        status = result.getString("Status");
                        // message = result.getString("Message");

                        //   bundle.putString("add_id",status);

                        //   bundle.putString("streetname",  DistrictAdapter.district_name);


                        if (status.equals("Success")) {
                            selectedFragment = SelectPaymentMethod.newInstance();
                            FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout1, selectedFragment);
                            transaction.addToBackStack("track1");
                            transaction.commit();

                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}





