package com.example.prueba;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prueba.Helper.Cartera;
import com.example.prueba.Helper.ConexionApi;
import com.example.prueba.Helper.DataHTTP;
import com.example.prueba.Helper.Persona;
import com.google.gson.Gson;

import org.apache.http.impl.client.BasicCookieStore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreInscripcion extends Fragment {
 EditText nombres ;
 EditText apellidos;
 EditText dui;
 EditText nit;
 Button siguiente;
 String id_obtenido;
 Integer tipop = 1;
 Integer id_Cartera;
 private String key;
    public PreInscripcion() {
        // Required empty public constructor
    }

    public void limpiarEditText(){
        nombres.setText("");
        apellidos.setText("");
        dui.setText("");
        nit.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pre_inscripcion, container, false);

        nombres = v.findViewById(R.id.nombreCliente);
        apellidos = v.findViewById(R.id.apellidoCliente);
        dui = v.findViewById(R.id.DUI);
        nit = v.findViewById(R.id.NIT);
        siguiente = v.findViewById(R.id.siguiente);
        Intent intent = getActivity().getIntent();
        key = intent.getExtras().get("token").toString();
        extraercartera();
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preinscripcion();

            }
        });

        //llamado a metodo para ocultar teclado
        setupUI(v.findViewById(R.id.parent));
        return v;
    }
    //metodos para poder ocultar el teclado ante una pulsacion en la pantalla
    public static void hideSoftKeyboard(PreInscripcion activity) {
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
                    hideSoftKeyboard(PreInscripcion.this);
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




    public void extraercartera(){
        ConexionApi cp=new ConexionApi();
        List<DataHTTP> listData= new ArrayList<DataHTTP>();
        listData.add(new DataHTTP("buscar_cliente",key,"get",""));
        String gsonCuerpo=new Gson().toJson(listData);
        try {
            String respuestaLogin=cp.execute("http://190.86.177.177/pordefecto/api/Carteras/Cartera_Usuario_Autenticado","Operacion",gsonCuerpo).get();
            if(respuestaLogin.length() >30){
                Cartera cartera =new Gson().fromJson(respuestaLogin,Cartera.class);
                id_Cartera = cartera.getId_Cartera();
            }else{
                Toast toast = Toast.makeText(getContext(), "No tiene cartera, comuniquese con su jefe inmediato", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
                siguiente.setEnabled(false);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }



    public void preinscripcion(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if(nombres.getText().toString().isEmpty()){
                Toast toast = Toast.makeText(getContext(), "Ingrese el nombre del Cliente", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            }else{
                if (apellidos.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getContext(), "Ingrese el apellido del Cliente", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }else{
                    if (dui.getText().toString().isEmpty()){
                        Toast toast = Toast.makeText(getContext(), "Ingrese el dui del Cliente", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                    }else{
                        if (nit.getText().toString().isEmpty()){
                            Toast toast = Toast.makeText(getContext(), "Ingrese el nit del cliente", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                            toast.show();
                        }else{
                            ConexionApi cp=new ConexionApi();
                            Persona persona = new Persona();
                            persona.setNombres(nombres.getText().toString());
                            persona.setApellidos(apellidos.getText().toString());
                            persona.setDui(dui.getText().toString());
                            persona.setNit(nit.getText().toString());
                            persona.setId_Cartera(id_Cartera);
                            String gsonPersona=new Gson().toJson(persona);
                            List<DataHTTP> listData= new ArrayList<DataHTTP>();
                            listData.add(new DataHTTP("persona",key,"post",gsonPersona));
                            String gsonCuerpo=new Gson().toJson(listData);

                            try {
                                String respuestaLogin=cp.execute("http://190.86.177.177/pordefecto/api/Personas/CrearPersona?tipo_persona="+tipop,"Operacion",gsonCuerpo).get();
                                if (respuestaLogin.length() == 45){
                                    Toast toast = Toast.makeText(getContext(), "El Dui ya esta almacenado en sistema", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                    toast.show();
                                    limpiarEditText();
                                }else{
                                    id_obtenido = respuestaLogin;
                                    Toast toast = Toast.makeText(getContext(), "Cliente pre-calificado exitosamente", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                    toast.show();

                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();

                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                            Fragment nuevoFragmento = new fotografias();
                            Bundle args = new Bundle();
                            args.putString("id", id_obtenido);
                            args.putString("dui", dui.getText().toString());
                            args.putString("nombre",nombres.getText().toString());
                            args.putString("apellido",apellidos.getText().toString());
                            nuevoFragmento.setArguments(args);
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.content_main, nuevoFragmento);
                            transaction.addToBackStack(null);
                            transaction.commitAllowingStateLoss();
                            limpiarEditText();
                        }
                    }
                }
            }



        }else {
            Toast toast = Toast.makeText(getContext(), "Conectate a una Red de Internet", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }


    }

    private class ConexionAPI extends ConexionApi{
        public ConexionAPI(){
            super();
        }

        public ConexionAPI(BasicCookieStore cookieStore){
            super(cookieStore);
        }
    }

}
