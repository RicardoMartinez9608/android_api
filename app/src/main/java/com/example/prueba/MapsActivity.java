package com.example.prueba;

import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba.Helper.ConexionApi;
import com.example.prueba.Helper.DataHTTP;
import com.example.prueba.Helper.Persona;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Bundle id;
    Bundle nombreCompleto;
    Bundle DUI;
    TextView nombre;
    TextView dui;
    TextView ID;
    Button regresar, Actualizar;
    private String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ID = findViewById(R.id.id);
        nombre = findViewById(R.id.nombre_completo);
        dui = findViewById(R.id.dui);
        id = getIntent().getExtras();
        nombreCompleto = getIntent().getExtras();
        DUI = getIntent().getExtras();
        String IDobtenido = id.getString("Id_usuario");
        String nombreObtenido = nombreCompleto.getString("nombreC");
        String duiObtenido = DUI.getString("DUI");
        ID.setText(IDobtenido);
        nombre.setText("Nombre del Cliente: " +nombreObtenido);
        dui.setText(duiObtenido);
        regresar = findViewById(R.id.Regresar);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
     //   LatLng sydney = new LatLng(13.9698857, -89.562644);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("AyC"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.getUiSettings().setZoomControlsEnabled(true);

        Ubicaciones(googleMap);
    }

    public void Ubicaciones(GoogleMap googleMap) {
        Double LatitudDomicilio;
        Double LongitudDomicilio;

        Double LatitudNegocio;
        Double Longitudnegocio;

        Double LatitudTrabajo;
        Double LongitudTrabajo;

        mMap = googleMap;
        ConexionApi cp=new ConexionApi();
        List<DataHTTP> listData= new ArrayList<DataHTTP>();
        listData.add(new DataHTTP("buscar_cliente",key,"post",""));
        String gsonCuerpo=new Gson().toJson(listData);
        try {
            String respuestaLogin=cp.execute("http://190.86.177.177/pordefecto/api/Personas/PersonaEspecifica?filtro="+dui.getText().toString()   ,"Operacion",gsonCuerpo).get();
            Persona persona =new Gson().fromJson(respuestaLogin,Persona.class);
            if (persona.getLongitud_Direccion_Trabajo() == null && persona.getLongitud_Direccion_Negocio() ==null && persona.getLongitud_Domicilio()== null )
            {
                Toast toast = Toast.makeText(this, "No existe localización guardada para su cliente, porfavor actualize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            }else{
                //carga de valores extraidos de la api para ubicacion del trabajo

                if (persona.getLatitud_Domicilio()== null && persona.getLongitud_Domicilio() == null){
                    Toast toast = Toast.makeText(this, "Localizacion del Domicilio no Almacenada", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }else{
                    LatitudDomicilio = Double.parseDouble( persona.getLatitud_Domicilio());
                    LongitudDomicilio =Double.parseDouble( persona.getLongitud_Domicilio());
                    final LatLng punto1 = new LatLng(LatitudDomicilio,LongitudDomicilio);
                    mMap.addMarker(new MarkerOptions().position(punto1).title("Ubicación del Domicilio"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(punto1));

                }
                //carga de valores extraidos de la api para ubicacion del Negocio

                if (persona.getLatitud_Direccion_Negocio() == null && persona .getLongitud_Direccion_Negocio()== null) {
                    Toast toast = Toast.makeText(this, "Localizacion del Negocio no Almacenada", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }else{
                    LatitudNegocio = Double.parseDouble(persona.getLatitud_Direccion_Negocio());
                    Longitudnegocio = Double.parseDouble(persona.getLongitud_Direccion_Negocio());
                    final LatLng punto2 = new LatLng(LatitudNegocio,Longitudnegocio);
                    mMap.addMarker(new MarkerOptions().position(punto2).title("Ubicación del Negocio"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(punto2));
                }
                //carga de valores extraidos de la api para ubicacion del Trabajo

                if (persona.getLatitud_Direccion_Trabajo() ==null && persona.getLongitud_Direccion_Trabajo()== null)
                {
                    Toast toast = Toast.makeText(this, "Localizacion del Trabajo no Almacenada", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }else{
                    LatitudTrabajo = Double.parseDouble(persona.getLatitud_Direccion_Trabajo());
                    LongitudTrabajo = Double.parseDouble(persona.getLongitud_Direccion_Trabajo());
                    final LatLng punto3 = new LatLng(LatitudTrabajo,LongitudTrabajo);
                    mMap.addMarker(new MarkerOptions().position(punto3).title("Ubicación del Trabajo"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(punto3));
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
