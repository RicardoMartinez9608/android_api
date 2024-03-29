package com.example.prueba;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Localizacion implements LocationListener {
gps gps;
TextView tvMensaje;
TextView lon;
TextView lati;

public gps getGps(){return gps;}
public void setGps(gps gps, TextView lati, TextView lon){
this.gps = gps;
this.lon = lon;
this.lati = lati;
}
    @Override
    public void onLocationChanged(Location location) {


        String t1 = String.valueOf(location.getLatitude());
        String t2 = String.valueOf(location.getLongitude());
        lati.setText(t1);
        lon.setText(t2);

        mapa(location.getLatitude(), location.getLongitude());

    }

    public void mapa(double lat, double lon) {
        FragmentMaps fragment = new FragmentMaps();

        Bundle bundle = new Bundle();
        bundle.putDouble("lat", new Double(lat));
        bundle.putDouble("lon", new Double(lon));
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getGps().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment, fragment, null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug","LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug","LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug","LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
    }

    @Override
    public void onProviderEnabled(String s) {

        Toast toast = Toast.makeText(this.gps, "Servicio GPS Activado", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    @Override
    public void onProviderDisabled(String s) {

        Toast toast = Toast.makeText(this.gps, "GPS Desactivado, Activelo para continuar", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }
}
