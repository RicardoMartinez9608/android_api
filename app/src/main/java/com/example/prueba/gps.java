package com.example.prueba;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba.Helper.ConexionApi;
import com.example.prueba.Helper.DataHTTP;
import com.example.prueba.Helper.Persona;
import com.example.prueba.Helper.Ubicacion_Persona;
import com.google.gson.Gson;
import org.apache.http.impl.client.BasicCookieStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class gps extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    String[] strFrutas;
  private   List<String> listaFrutas =new ArrayList<>();
    ArrayAdapter<String> comboAdapter;
    TextView tvMensaje;
    TextView LongitudAc;
    TextView LatitudAC;
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
    Button Atras;
    private String key;
    //minimo tiempo para updates en milisegundos
    private static final long MIN_TIME = 10000; // 10 segundos


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        LongitudAc = findViewById(R.id.LongitudActual);
        LatitudAC = findViewById(R.id.LatitudActual);
        tvMensaje = findViewById(R.id.tvMensaje);
        ID = findViewById(R.id.id);
        nombre = findViewById(R.id.nombre_completo);
        dui = findViewById(R.id.dui);
        UbicacionEn = findViewById(R.id.UbicacionEncontrada);
        LongitudEn = findViewById(R.id.LongitudEncontrada);
        LatitudEn = findViewById(R.id.LatitudEncontrada);
        Atras = findViewById(R.id.Regresar);
        ubicaciones = findViewById(R.id.spinner);
        ubicaciones.setOnItemSelectedListener(this);

        //Arreglo con nombre de frutas
        strFrutas = new String[] {"Domicilio", "Negocio", "Trabajo"};
        //Agrego las frutas del arreglo `strFrutas` a la listaFrutas
        Collections.addAll(listaFrutas, strFrutas);
        //Implemento el adapter con el contexto, layout, listaFrutas
        comboAdapter = new ArrayAdapter<>(this,R.layout.spinner_item, listaFrutas);
        //Cargo el spinner con los datos
        ubicaciones.setAdapter(comboAdapter);
        //Metodo para regresar al fragment de seleccion de cliente
        Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_main);
               // if(getSupportFragmentManager().getBackStackEntryCount() > 0){
               //     getSupportFragmentManager().popBackStackImmediate();
                //}else{
                    finish();

               // }
            }
        });
        //ID de usuario
        id = getIntent().getExtras();
        nombreCompleto = getIntent().getExtras();
        DUI = getIntent().getExtras();

        String IDobtenido = id.getString("Id_usuario");
        String nombreObtenido = nombreCompleto.getString("nombreC");
        String duiObtenido = DUI.getString("DUI");
        ID.setText(IDobtenido);
        nombre.setText("Nombre del Cliente: " +nombreObtenido);
        dui.setText(duiObtenido);
        //Intent intent = getIntent();
       // key = intent.getExtras().get("token").toString();
       // listaFrutas = new ArrayList<>();

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

        local.setGps(this,LatitudAC,LongitudAc);


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

        tvMensaje.setText("Localizacion Actual");
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
        //validacion si existe conexion a internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
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
                    LongitudEn.setText("Longitud " + persona.getLongitud_Domicilio());
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
        }else {
            Toast toast = Toast.makeText(this, "Conectate a una Red de Internet", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }



    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        //validacion si existe conexion a internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Quiere actualizar la Ubicacion de su cliente");
            builder.setTitle("Satelite");
            builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int indice = ubicaciones.getSelectedItemPosition();
                    String tipo = "";
                    ConexionApi cp=new ConexionApi();
                    List<DataHTTP> listData= new ArrayList<DataHTTP>();
                    if (indice ==0){
                        tipo = "Domicilio";
                    }else if (indice == 1){
                        tipo = "Negocio";
                    }else if (indice==2){
                        tipo = "Trabajo";
                    }

                    String lat = LatitudAC.getText().toString();
                    String longi = LongitudAc.getText().toString();
                    Ubicacion_Persona ubicacion = new Ubicacion_Persona();
                    ubicacion.setId_Persona(Integer.parseInt(ID.getText().toString()));
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

                    Toast toast = Toast.makeText(getApplicationContext(), "Ubicación actualizada correctamente", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();


                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }else {
            Toast toast = Toast.makeText(this, "Conectate a una Red de Internet", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }

    }

    @Override
    public void onBackPressed(){
        isFinishing();
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
