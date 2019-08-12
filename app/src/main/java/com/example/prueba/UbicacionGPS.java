package com.example.prueba;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;


/**
 * A simple {@link Fragment} subclass.
 */
public class UbicacionGPS extends Fragment {
    TextView tvMensaje;

    //minimo tiempo para updates en milisegundos
    private static final long MIN_TIME = 10000; // 10 segundos

    public UbicacionGPS() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_ubicacion_g, container, false);
        tvMensaje = getActivity().findViewById(R.id.tvMensaje);

        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this.getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION,},1000);
        }else {
            iniciarLocalizacion();
        }

            return v;

    }

    private void iniciarLocalizacion(){
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        Localizacion local = new Localizacion();

       // local.setUbicacionGPS(this,tvMensaje);

        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled){
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this.getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION,},1000);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, 0,local);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, 0,local);

        tvMensaje.setText("Localizacion Agregada");

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]grantResults) {
        if(requestCode == 1000) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                iniciarLocalizacion();
                return;
            }
        }
    }


}
