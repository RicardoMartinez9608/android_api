package com.example.prueba;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.prueba.Helper.ConexionApi;
import com.example.prueba.Helper.DataHTTP;
import com.example.prueba.Helper.Persona;
import com.example.prueba.Helper.Ubicacion_Persona;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.apache.http.impl.client.BasicCookieStore;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class gps extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    TextView tvMensaje;
    TextView ID;
    String dato ;
    Bundle id;
    Bundle nombreCompleto;
    Bundle DUI;
    TextView nombre;
    TextView dui;
    Spinner ubicaciones;
    TextView UbicacionEn;
    TextView LongitudEn;
    TextView LatitudEn;
    private String key;
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
        UbicacionEn = findViewById(R.id.UbicacionEncontrada);
        LongitudEn = findViewById(R.id.LongitudEncontrada);
        LatitudEn = findViewById(R.id.LatitudEncontrada);
        ubicaciones = findViewById(R.id.spinner);
        ubicaciones.setOnItemSelectedListener(this);
        //ID de usuario
        id = getIntent().getExtras();
        nombreCompleto = getIntent().getExtras();
        DUI = getIntent().getExtras();
        String IDobtenido = id.getString("Id_usuario");
        String nombreObtenido = nombreCompleto.getString("nombreC");
        String duiObtenido = DUI.getString("DUI");
        //ID.setText(IDobtenido);
        nombre.setText("Nombre del Cliente: " +nombreObtenido);
        dui.setText(duiObtenido);

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


    //metodos del Spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int indice = ubicaciones.getSelectedItemPosition();
        ConexionApi cp=new ConexionApi();
        List<DataHTTP> listData= new ArrayList<DataHTTP>();
        listData.add(new DataHTTP("buscar_cliente",key,"post",""));
        String gsonCuerpo=new Gson().toJson(listData);
        try {
            String respuestaLogin=cp.execute("http://190.86.177.177/pordefecto/api/Personas/PersonaEspecifica?filtro="+dui.getText().toString()   ,"Operacion",gsonCuerpo).get();
            Persona persona =new Gson().fromJson(respuestaLogin,Persona.class);
            if (indice == 0){
                LatitudEn.setText("Latitud  "+persona.getLatitud_Domicilio());
                LongitudEn.setText("Longitud " + persona.getLatitud_Domicilio());
            }else if(indice == 1){
                LatitudEn.setText("Latitud "+persona.getLatitud_Direccion_Negocio());
                LongitudEn.setText("Longitud " + persona.getLongitud_Direccion_Negocio());
            }else if(indice == 2){
                LatitudEn.setText("Latitud  "+persona.getLatitud_Direccion_Trabajo());
                LongitudEn.setText("Longitud " + persona.getLongitud_Direccion_Trabajo());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        ConexionApi cp=new ConexionApi();
        List<DataHTTP> listData= new ArrayList<DataHTTP>();
        Ubicacion_Persona ubicacion = new Ubicacion_Persona();
        int id = 17;
        String tipo = "Trabajo";
        String lat = "00000";
        String longi = "0010101";

        ubicacion.setId_Persona(id);
        ubicacion.setTipo_Ubicacion(tipo);
        ubicacion.setLat(lat);
        ubicacion.setLong(longi);
        Gson gson = new Gson();
        String JSON = gson.toJson(ubicacion);
        listData.add(new DataHTTP("coordenadas",key,"put",JSON));
        String gsonCuerpo=new Gson().toJson(listData);
        try {

            String respuestaLogin= cp.execute("http://190.86.177.177/pordefecto/api/Personas/Actualizar_Ubicacion_Persona","Operacion", gsonCuerpo).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private class ConexionAPI extends ConexionApi {
        public ConexionAPI(){
            super();
        }

        public ConexionAPI(BasicCookieStore cookieStore){
            super(cookieStore);
        }
    }
}
