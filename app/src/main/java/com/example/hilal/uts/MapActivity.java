package com.example.hilal.uts;

import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MapActivity extends AppCompatActivity implements DapatkanAlamat.onTaskSelesai{

    private Button mLocationButton;
    private Button mPickerButton;
    private TextView mLocationTextView;
    private ImageView mAndroidImageView;
    private AnimatorSet mRotateAnim;

    private boolean mTrackingLocation;

    private Location mLastLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;

    //places class
    private PlaceDetectionClient mPlaceDetectionClient;

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final int REQUEST_PICK_PLACE = 0;

    //variabel untuk savedinstance
    private static String nama, alamat;
    private static int gambar = -1;

    //savedinstance agar tampilan landscape dan portrait tetap
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("nama",nama);
        savedInstanceState.putString("alamat",alamat);
        savedInstanceState.putInt("gambar",gambar);
    }

    //restore data dari instancestate pada object yg diinginkan
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState.getString("name")=="") {
            mLocationTextView.setText("Tekan Buttonnya bro, untuk mendapatkan lokasi");
        } else{

            mLocationTextView.setText(getString(R.string.alamat_text,savedInstanceState.getString("nama"),savedInstanceState.getString("alamat"), System.currentTimeMillis()));
            mAndroidImageView.setImageResource(savedInstanceState.getInt("gambar"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mPlaceDetectionClient = Places.getPlaceDetectionClient(this);

        mLocationButton = (Button) findViewById(R.id.button_location);
        mPickerButton = (Button) findViewById(R.id.button_pilih);
        mLocationTextView = (TextView) findViewById(R.id.textview_location);
        mAndroidImageView = (ImageView) findViewById(R.id.imageview_android);

        // Untuk animasi
        mRotateAnim = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.rotate);
        mRotateAnim.setTarget(mAndroidImageView);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mTrackingLocation) {
                    mulaiTrackingLokasi();
                } else {
                    stopTrackingLokasi();
                }
            }
        });

        mPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try{
                    startActivityForResult(builder.build(MapActivity.this), REQUEST_PICK_PLACE);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        mLocationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult){
                //jika tracking aktif proses reserve geocode menjadi data alamat
                if (mTrackingLocation){
                    new DapatkanAlamat(MapActivity.this,
                            MapActivity.this)
                            .execute(locationResult.getLastLocation());
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            Place place = PlacePicker.getPlace(this, data);
            setTipeLokasi(place);
            mLocationTextView.setText(
                    getString(R.string.alamat_text,
                            place.getName(),
                            place.getAddress(),
                            System.currentTimeMillis()));
            nama = place.getName().toString();
            alamat = place.getAddress().toString();
            gambar = setTipeLokasi(place);
            mAndroidImageView.setImageResource(gambar);
        } else {
            mLocationTextView.setText("Lokasinya kok gak dipilih se");
        }
    }

    private void mulaiTrackingLokasi(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            // Log.d("GETPERMISI", "getLocation: permissions granted");
//            mFusedLocationClient.getLastLocation().addOnSuccessListener(
//                    new OnSuccessListener<Location>() {
//                        @Override
//                        public void onSuccess(Location location) {
//                            if (location != null){
////                                mLastLocation = location;
////                                mLocationTextView.setText(
////                                        getString(R.string.location_text,
////                                                mLastLocation.getLatitude(),
////                                                mLastLocation.getLongitude(),
////                                                mLastLocation.getTime()));
//
//                                // lakukan reverse geocode AsyncTask
//                                new DapatkanAlamatTask(MainActivity.this,
//                                        MainActivity.this).execute(location);
//                            } else {
//                                mLocationTextView.setText("Lokasi Ra Ono");
//                            }
//                        }
//                    }
//            );

            mFusedLocationClient.requestLocationUpdates(getLocationRequest(), mLocationCallback, null);

            mLocationTextView.setText(getString(R.string.alamat_text,"sedang mencari nama tempat", "sedang mencari alamat",System.currentTimeMillis()));
            mTrackingLocation = true;
            mLocationButton.setText("Stop Tracking Lokasi");
            mRotateAnim.start();
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){

        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                // jika permissions diijinkan getLocation()
                // jika tidak tampilkan toast
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mulaiTrackingLokasi();
                } else {
                    Toast.makeText(this, "Permission tak didapat", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onTaskCompleted(final String result) throws SecurityException{
        if (mTrackingLocation) {

            Task<PlaceLikelihoodBufferResponse> placeResult = mPlaceDetectionClient.getCurrentPlace(null);

            placeResult.addOnCompleteListener
                    (new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
                        @Override
                        public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                            if (task.isSuccessful()){
                                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                                float maxLikelihood = 0;
                                Place currentPlace = null;

                                for (PlaceLikelihood placeLikelihood : likelyPlaces){
                                    if (maxLikelihood < placeLikelihood.getLikelihood()){
                                        maxLikelihood = placeLikelihood.getLikelihood();
                                        currentPlace = placeLikelihood.getPlace();
                                    }

                                    //tampilan di UI
                                    if (currentPlace != null){
                                        mLocationTextView.setText(
                                                getString(R.string.alamat_text,
                                                        currentPlace.getName(),
                                                        result,
                                                        System.currentTimeMillis())
                                        );
                                        setTipeLokasi(currentPlace);
                                        nama = placeLikelihood.getPlace().getName().toString();
                                        alamat = placeLikelihood.getPlace().getAddress().toString();
                                        gambar = setTipeLokasi(currentPlace);
                                        mAndroidImageView.setImageResource(gambar);
                                    }
                                }
                                likelyPlaces.release();

                            } else {
                                mLocationTextView.setText(
                                        getString(R.string.alamat_text,
                                                "Nama Lokasi Tak Ditemukan",
                                                result,
                                                System.currentTimeMillis())
                                );
                            }
                        }
                    });

            // update UI dengan tampilan hasil alamat
//            mLocationTextView.setText(getString(R.string.alamat_text,
//                    result, System.currentTimeMillis()));

        }
    }

    private void stopTrackingLokasi(){
        if (mTrackingLocation){
            mTrackingLocation = false;
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
            mLocationButton.setText("Mulai Tracking Lokasi");
            mLocationTextView.setText("Tracking Sedang Dihentikan");
            mRotateAnim.end();
        }
    }

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    private static int setTipeLokasi(Place currentPlace) {
        int drawableID = -1;
        for(Integer placeType : currentPlace.getPlaceTypes()){
            switch (placeType){
                case Place.TYPE_UNIVERSITY:
                    drawableID = R.drawable.kampus;
                    break;
                case Place.TYPE_CAFE:
                    drawableID = R.drawable.warkop;
                    break;
                case Place.TYPE_SHOPPING_MALL:
                    drawableID = R.drawable.toko;
                    break;
                case Place.TYPE_MOVIE_THEATER:
                    drawableID = R.drawable.bioskop;
                    break;
                case Place.TYPE_AIRPORT:
                    drawableID = R.drawable.bandara;
                    break;
                case Place.TYPE_STADIUM:
                    drawableID = R.drawable.stadion;
                    break;
                case Place.TYPE_HOSPITAL:
                    drawableID = R.drawable.rs;
                    break;
            }
        }
        if (drawableID < 0){
            drawableID = R.drawable.unknown;
        }
//        mAndroidImageView.setImageResource(drawableID);
        return drawableID;
    }
}
