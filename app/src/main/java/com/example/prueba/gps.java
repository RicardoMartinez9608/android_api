package com.example.prueba;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.EditText;
import android.widget.TextView;

public class gps extends AppCompatActivity {
    TextView tvMensaje;
    TextView ID;
    Bundle id;
    Bundle nombreCompleto;
    Bundle DUI;
    TextView nombre;
    TextView dui;
    //minimo tiempo para updates en milisegundos
    private static final long MIN_TIME = 10000; // 10 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        tvMensaje = findViewById(R.id.tvMensaje);
        ID = findViewById(R.id.txtTipoUbicacion);
        nombre = findViewById(R.id.nombre_completo);
        dui = findViewById(R.id.dui);
        //ID de usuario
        id = getIntent().getExtras();
        nombreCompleto = getIntent().getExtras();
        DUI = getIntent().getExtras();
        String IDobtenido = id.getString("Id_usuario");
        String nombreObtenido = nombreCompleto.getString("nombreC");
        String duiObtenido = DUI.getString("DUI");
        //ID.setText(IDobtenido);
        nombre.setText("Nombre del Cliente: " +nombreObtenido);
        dui.setText("DUI de cliente: " +duiObtenido);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,},1000);
        }else {
            iniciarLocalizacion();
        }

    }

    private void iniciarLocalizacion(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Localizacion local = new Localizacion();

        local.setGps(this,tvMensaje);

        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled){
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,},1000);
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
