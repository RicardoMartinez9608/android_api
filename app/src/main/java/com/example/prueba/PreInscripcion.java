package com.example.prueba;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
 EditText nombres;
 EditText apellidos;
 EditText dui;
 EditText nit;
 Button siguiente;
 Integer tipop = 1;
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
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConexionApi cp=new ConexionApi();
                Persona persona = new Persona();
                persona.setNombres(nombres.getText().toString());
                persona.setApellidos(apellidos.getText().toString());
                persona.setDui(dui.getText().toString());
                persona.setNit(nit.getText().toString());
                persona.setId_Cartera(20);
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
                        Toast toast = Toast.makeText(getContext(), "Cliente pre-calificado exitosamente", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                        limpiarEditText();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();

                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });



        return v;
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
