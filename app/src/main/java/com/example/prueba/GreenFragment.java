package com.example.prueba;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba.Helper.ConexionApi;
import com.example.prueba.Helper.DataHTTP;
import com.example.prueba.Helper.Persona;
import com.example.prueba.Helper.Ubicacion_Persona;
import com.google.gson.Gson;
import org.apache.http.impl.client.BasicCookieStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
@TargetApi(Build.VERSION_CODES.N)
public class GreenFragment extends Fragment implements View.OnClickListener{
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private static final int WRITE_PERMISSION = 0x01;
    private ImageButton mBotonHablar;
    private EditText parametro;
    private Button buscar;
    private Button Ubicacion,verUbicacion,documentos;
    private TextView nombre_completo;
    private TextView dui;
    public String nombre_c;
    public String duiU;
    private TextView nit;
    private TextView fecha_nacimiento;
    public int id_Persona;
    private String key;

    public GreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_green, container, false);

        parametro= (EditText) v.findViewById(R.id.parametro);
        buscar=(Button) v.findViewById(R.id.buscar);
        nombre_completo=(TextView)v.findViewById(R.id.nombre_completo);
        dui=(TextView) v.findViewById(R.id.dui);
        nit=(TextView) v.findViewById(R.id.nit);
        mBotonHablar = v.findViewById(R.id.botonHablar);
        parametro.setText("03504301-7");
        checkIfLocationOpened();

        buscar.setOnClickListener((View.OnClickListener) this);
        try {
            Intent intent = getActivity().getIntent();
            key = intent.getExtras().get("token").toString();
        }catch (Exception ex){
            String error=ex.getMessage();
        }

        Ubicacion = (Button) v.findViewById(R.id.Ubicacion);
        Ubicacion.setVisibility(View.INVISIBLE);
        Ubicacion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (checkIfLocationOpened()== false){
                    Toast toast = Toast.makeText(getContext(), "Enciende el GPS", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }else {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(getActivity(), gps.class);
                        intent.putExtra("Id_usuario", String.valueOf(id_Persona));
                        intent.putExtra("nombreC", String.valueOf(nombre_c));
                        intent.putExtra("DUI", String.valueOf(duiU));
                        startActivity(intent);
                    } else {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                            Toast.makeText(getActivity(), "Permiso necesario para visualizar la geolozalizacion", Toast.LENGTH_LONG).show();
                        }
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, WRITE_PERMISSION);
                    }
                }

                //Deshabilitar control durante 1 segundos
                Ubicacion.postDelayed(new Runnable() { public void run() { Ubicacion.setVisibility(View.INVISIBLE); } }, 1000);
                verUbicacion.postDelayed(new Runnable() { public void run() { verUbicacion.setVisibility(View.INVISIBLE); } }, 1000);
                documentos.postDelayed(new Runnable() { public void run() { documentos.setVisibility(View.INVISIBLE); } }, 1000);


            }
        });
        verUbicacion = (Button) v.findViewById(R.id.verUbicacion);
        verUbicacion.setVisibility(View.INVISIBLE);
        verUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfLocationOpened() == false) {
                    Toast toast = Toast.makeText(getContext(), "Enciende el GPS", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                } else {
                    Intent intent = new Intent(getActivity(), MapsActivity.class);
                    intent.putExtra("Id_usuario", String.valueOf(id_Persona));
                    intent.putExtra("nombreC", String.valueOf(nombre_c));
                    intent.putExtra("DUI", String.valueOf(duiU));
                    startActivity(intent);
                    //Deshabilitar control durante 1 segundos
                    Ubicacion.postDelayed(new Runnable() {
                        public void run() {
                            Ubicacion.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                    verUbicacion.postDelayed(new Runnable() {
                        public void run() {
                            verUbicacion.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                    documentos.postDelayed(new Runnable() { public void run() { documentos.setVisibility(View.INVISIBLE); } }, 1000);
                }
            }
        });

        mBotonHablar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarEntradaVoz();
            }
        });

        documentos = v.findViewById(R.id.Documentos);
        documentos.setVisibility(View.INVISIBLE);
        documentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment nuevoFragmento = new fotografias();
                Bundle args = new Bundle();
                args.putString("id", String.valueOf(id_Persona));
                args.putString("dui", String.valueOf(duiU));
                args.putString("nombre",nombre_c);
                args.putString("apellido","");
                nuevoFragmento.setArguments(args);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, nuevoFragmento);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        //llamado a metodo para ocultar teclado
        setupUI(v.findViewById(R.id.parent));
        return v;

    }
    //metodos para poder ocultar el teclado ante una pulsacion en la pantalla
    public static void hideSoftKeyboard(GreenFragment activity) {
        InputMethodManager imm = (InputMethodManager) activity.getContext().getSystemService(activity.getContext().INPUT_METHOD_SERVICE);
        if (activity.getActivity().getCurrentFocus() != null)
            imm.hideSoftInputFromWindow(activity.getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }
    //metodos para poder ocultar el teclado ante una pulsacion en la pantalla
    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(GreenFragment.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    String print = "";
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    final Object[] voz = result.toArray();
                    String valor = voz[0].toString();
                    String h = valor.replace(" ", "");
                    if (h.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]-[0-9]")) {
                        print = result.get(0);
                        parametro.setText(print.replace(" ", ""));
                    } else {
                        Toast toast = Toast.makeText(getContext(), "Dui Incorrecto", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                    }
                }
            }
        }
    }

    private void iniciarEntradaVoz() {   Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Dicta el número de DUI");
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUT);
        }catch (ActivityNotFoundException e){

        }

    }
    //verificacion de GPS
    private boolean checkIfLocationOpened() {
        String provider = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        System.out.println("Provider contains=> " + provider);
        if (provider.contains("gps") || provider.contains("network")){
            return true;
        }
        Toast toast = Toast.makeText(getContext(), "Enciende el GPS", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
        return false;
    }
    public void onClick(View view) {
        if (checkIfLocationOpened()== false){
            Toast toast = Toast.makeText(getContext(), "Enciende el GPS", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }else {

            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                if (TextUtils.isEmpty(parametro.getText())) {
                    Toast toast = Toast.makeText(getContext(), "Ingresa el DUI de tu Cliente", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                } else {
                    ConexionApi cp = new ConexionApi();
                    List<DataHTTP> listData = new ArrayList<DataHTTP>();
                    listData.add(new DataHTTP("buscar_cliente", key, "post", ""));
                    String gsonCuerpo = new Gson().toJson(listData);
                    try {
                        String respuestaLogin = cp.execute("http://190.86.177.177/pordefecto/api/Personas/PersonaEspecifica?filtro=" + parametro.getText().toString(), "Operacion", gsonCuerpo).get();
                        if (respuestaLogin.length() == 38) {
                            Toast toast = Toast.makeText(getContext(), "DUI del cliente no almacenado en el sistema", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                            toast.show();
                            parametro.setText("");
                        } else {
                            Persona persona = new Gson().fromJson(respuestaLogin, Persona.class);
                            nombre_completo.setText("Nombre del Cliente: " + persona.getNombreCompleto());
                            dui.setText("                              DUI: " + persona.getDui());
                            nit.setText("                              NIT: " + persona.getNit());
                            id_Persona = Math.round(persona.getId_Persona());
                            nombre_c = persona.getNombreCompleto();
                            duiU = persona.getDui();
                            Ubicacion.setVisibility(View.VISIBLE);
                            verUbicacion.setVisibility(View.VISIBLE);
                            documentos.setVisibility(View.VISIBLE);
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Toast toast = Toast.makeText(getContext(), "Conectate a una Red de Internet", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            }
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
