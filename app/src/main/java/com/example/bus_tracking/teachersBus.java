package com.example.bus_tracking;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;

public class teachersBus extends AppCompatActivity implements OnMapReadyCallback {


    private MapboxMap mapboxMap;
    private MapView mapView;
    private SymbolManager symbolManager;
    private Bitmap bitmap;
    private EditText latlongshow;
    private TextView busNameShow;

    private static final String TAG  = "teachersBus", LOCATION_MARKER = "my-marker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.access_token));

        setContentView(R.layout.activity_teachers_bus);

        //latlongshow = (EditText) findViewById(R.id.latlong);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        busNameShow = (TextView)findViewById(R.id.busName);

        busNameShow.setText("Teachers Bus");
    }


    @Override
    public void onMapReady(@NonNull final MapboxMap mapbox) {
        this.mapboxMap = mapbox;

        mapboxMap.setStyle(Style.MAPBOX_STREETS,
                new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        symbolManager = new SymbolManager(mapView, mapboxMap, style);
                        symbolManager.setIconAllowOverlap(true);
                        symbolManager.setIconIgnorePlacement(true);



                        //load all the images to mapbox style
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bus_location_marker);
                        style.addImage(LOCATION_MARKER, bitmap);


                        // Teachers BUS MARKER
                        //bus no 1
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Bus").child("Teachers bus").child("Bus no 1");

                        databaseReference.addValueEventListener(new ValueEventListener() {

                            Symbol symbol = symbolManager.create(new SymbolOptions()
                                    .withLatLng(new LatLng(24.1268,91.2556))
                                    .withIconImage(LOCATION_MARKER)
                                    .withIconSize(.3f));

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                Double lat = (Double) dataSnapshot.child("Lattitude").getValue();
                                Double lng = (Double) dataSnapshot.child("Longitude").getValue();
                                LatLng point = new LatLng(lat,lng);

                                symbol.setLatLng(point);
                                symbolManager.update(symbol);

                               // latlongshow.setText(lat+"  "+lng);

                                CameraPosition position = new CameraPosition.Builder()
                                        .target(point)
                                        .zoom(10)
                                        .tilt(20)
                                        .build();
                                mapboxMap.setCameraPosition(position);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                        //bus no 2
                        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Bus").child("Teachers bus").child("Bus no 2");

                        databaseReference2.addValueEventListener(new ValueEventListener() {

                            Symbol symbol = symbolManager.create(new SymbolOptions()
                                    .withLatLng(new LatLng(24.1268,91.2556))
                                    .withIconImage(LOCATION_MARKER)
                                    .withIconSize(.3f));

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                Double lat = (Double) dataSnapshot.child("Lattitude").getValue();
                                Double lng = (Double) dataSnapshot.child("Longitude").getValue();
                                LatLng point = new LatLng(lat,lng);

                                symbol.setLatLng(point);
                                symbolManager.update(symbol);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError2) {

                            }
                        });

                        //bus 3
                        DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference("Bus").child("Teachers bus").child("Bus no 3");

                        databaseReference3.addValueEventListener(new ValueEventListener() {

                            Symbol symbol = symbolManager.create(new SymbolOptions()
                                    .withLatLng(new LatLng(24.1268,91.2556))
                                    .withIconImage(LOCATION_MARKER)
                                    .withIconSize(.3f));

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                Double lat = (Double) dataSnapshot.child("Lattitude").getValue();
                                Double lng = (Double) dataSnapshot.child("Longitude").getValue();
                                LatLng point = new LatLng(lat,lng);

                                symbol.setLatLng(point);
                                symbolManager.update(symbol);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }
                });
    }


    @SuppressWarnings( {"MissingPermission"})

    @Override

    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private void debugLog(String logText){
        Log.d(TAG, logText);
    }
}