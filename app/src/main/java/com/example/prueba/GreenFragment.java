package com.example.prueba;


import android.annotation.TargetApi;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
@TargetApi(Build.VERSION_CODES.N)
public class GreenFragment extends Fragment implements View.OnClickListener{
    private EditText parametro;
    private Button buscar;
    private Button Ubicacion;
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
        fecha_nacimiento=(TextView) v.findViewById(R.id.fecha_nacimiento);
        parametro.setText("03209799-4");


        buscar.setOnClickListener((View.OnClickListener) this);
        try {
            Intent intent = getActivity().getIntent();
            key = intent.getExtras().get("token").toString();
        }catch (Exception ex){
            String error=ex.getMessage();
        }

        Ubicacion = (Button) v.findViewById(R.id.Ubicacion);
        Ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //transaction.replace(R.id.content_main,new UbicacionGPS()).commit();

                Intent intent = new Intent(getActivity(),gps.class);
                intent.putExtra("Id_usuario",String.valueOf(id_Persona));
                intent.putExtra("nombreC", String.valueOf(nombre_c));
                intent.putExtra("DUI", String.valueOf(duiU));
                startActivity(intent);
            }
        });
        return v;

    }

    public void onClick(View view) {
        ConexionApi cp=new ConexionApi();
        List<DataHTTP> listData= new ArrayList<DataHTTP>();
        listData.add(new DataHTTP("buscar_cliente",key,"post"));
        String gsonCuerpo=new Gson().toJson(listData);
        try {
            String respuestaLogin=cp.execute("http://190.86.177.177/pordefecto/api/Personas/PersonaEspecifica?filtro="+parametro.getText().toString(),"Operacion",gsonCuerpo).get();
            Persona persona =new Gson().fromJson(respuestaLogin,Persona.class);

            nombre_completo.setText(persona.getNombreCompleto());
            dui.setText("DUI: "+persona.getDui());
            nit.setText("NIT: "+persona.getNit());
            fecha_nacimiento.setText("Fecha de Nacimiento: "+persona.getFechaNacimiento());
            id_Persona=Math.round(persona.getId_Persona());
            nombre_c =  persona.getNombreCompleto();
            duiU = persona.getDui();


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
