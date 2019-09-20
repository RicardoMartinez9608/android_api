package com.example.prueba;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba.Helper.Calculo_Credito;
import com.example.prueba.Helper.ConexionApi;
import com.example.prueba.Helper.DataHTTP;
import com.example.prueba.Helper.LineasCredito;
import com.example.prueba.Helper.Periodos_de_Pago;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalculoCredito extends Fragment implements AdapterView.OnItemSelectedListener {
    TextView idlinea;
    TextView idfrecuencia;
    Spinner frecuencia;
    Spinner descripcionLinea;
    TextView Cuotas;
    TextView Ncuotas;
    TextView TotalaPagar;
    TextView Monto;
    TextView Plazo;
    private String key;
    Button calcular;
    public CalculoCredito() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_calculo_credito, container, false);

        idlinea = v.findViewById(R.id.idlinea);
        descripcionLinea =(Spinner) v.findViewById(R.id.linea);
        descripcionLinea.setOnItemSelectedListener(this);
        LineaCredito();
        idfrecuencia = v.findViewById(R.id.idfrecuencia);
        frecuencia =(Spinner) v.findViewById(R.id.frecuenciaPago);
        calcular = (Button) v.findViewById(R.id.calcular);
        Cuotas = v.findViewById(R.id.Cuotas);
        Ncuotas =v.findViewById(R.id.Ncuotas);
        TotalaPagar = v.findViewById(R.id.TotalaPagar);
        Monto= v.findViewById(R.id.txtmonto);
        Plazo = v.findViewById(R.id.txtplazo);
        frecuencia.setOnItemSelectedListener(this);
        Intent intent = getActivity().getIntent();
        key = intent.getExtras().get("token").toString();
        FrecuenciaPagos();
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validacion si existe conexion a internet
                ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    if(Monto.getText().toString().isEmpty()){
                        Toast toast = Toast.makeText(getContext(), "Monto a Calcular está Vacio", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                    }else{
                        if (Plazo.getText().toString().isEmpty()){
                            Toast toast = Toast.makeText(getContext(), "Plazo a Calcular está Vacio", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                            toast.show();
                        }else {
                            ConexionApi cp=new ConexionApi();
                            List<DataHTTP> listData= new ArrayList<DataHTTP>();
                            listData.add(new DataHTTP("buscar_cliente",key,"post",""));
                            String gsonCuerpo=new Gson().toJson(listData);
                            int linea = Integer.parseInt( idlinea.getText().toString().trim());
                            double monto = Double.parseDouble(Monto.getText().toString().trim());
                            int periodo =Integer.parseInt(idfrecuencia.getText().toString().trim());
                            int plazo = Integer.parseInt(Plazo.getText().toString().trim());
                            int credito= 0;
                            try {
                                String respuestaLogin=cp.execute("http://190.86.177.177/pordefecto/api/Creditos/CuotaPrimaria?id_linea="+linea+"&monto="+monto+"&id_periodo="+periodo+"&plazo="+plazo+"&id_credito="+credito,"Operacion",gsonCuerpo).get();
                                Calculo_Credito calculo_credito =new Gson().fromJson(respuestaLogin,Calculo_Credito.class);
                                Integer size = calculo_credito.amortizacion.size();
                                Ncuotas.setText(String.valueOf(size));
                                Cuotas.setText(String.valueOf("$ "+ calculo_credito.getCuotaTotal()));
                                float totalpago =  size * calculo_credito.getCuotaTotal();
                                TotalaPagar.setText(String.valueOf("$ "+ totalpago));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }else {
                    Toast toast = Toast.makeText(getContext(), "Conectate a una Red de Internet", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }

            }
        });
        //llamado a metodo para ocultar teclado
        setupUI(v.findViewById(R.id.parent));
        return v;
    }
    //metodos para poder ocultar el teclado ante una pulsacion en la pantalla
    public static void hideSoftKeyboard(CalculoCredito activity) {
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
                    hideSoftKeyboard(CalculoCredito.this);
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

    //metodo para llenar el spinner de linea de credito y obtener su id
    public void LineaCredito(){

        //validacion si existe conexion a internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            ConexionApi cp=new ConexionApi();
            List<DataHTTP> listData= new ArrayList<DataHTTP>();
            listData.add(new DataHTTP("buscar_cliente",key,"get",""));
            String gsonCuerpo=new Gson().toJson(listData);

            try {
                String respuestaLogin=cp.execute("http://190.86.177.177/pordefecto/api/Linea_Credito/LineasCredito","Operacion",gsonCuerpo).get();
                Type listType = new TypeToken<ArrayList<LineasCredito>>(){}.getType();
                List<LineasCredito> lineasCreditos =new Gson().fromJson(respuestaLogin,listType);
                ArrayList<String> p = new ArrayList<String>();
                //   ArrayList<Integer> id = new ArrayList<Integer>();
                //     String contador = "";
                if (lineasCreditos != null) {
                    for (LineasCredito linea : lineasCreditos ) {
                        linea.getId_Linea();
                        linea.getDescripcion_linea();
                        p.add(linea.getId_Linea()+ " - " + linea.getDescripcion_linea());
                    }
                    final Object[] id =  p.toArray();
                    ArrayAdapter<String> h = new ArrayAdapter<String>(getContext(),R.layout.spinner_item,p);
                    descripcionLinea.setAdapter(h);

                    descripcionLinea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String valor = id[i].toString();
                            String[] idtraido = valor.split("-");
                            String idlineac = idtraido[0];
                            idlinea.setText(idlineac);
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }else {
            Toast toast = Toast.makeText(getContext(), "Conectate a una Red de Internet", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }

    }
    //metodo para llenar el spinner de linea de credito y obtener su id
    public void FrecuenciaPagos(){
        //validacion si existe conexion a internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            ConexionApi cp=new ConexionApi();
            List<DataHTTP> listData= new ArrayList<DataHTTP>();
            listData.add(new DataHTTP("buscar_cliente",key,"get",""));
            String gsonCuerpo=new Gson().toJson(listData);

            try {
                String respuestaLogin=cp.execute("http://190.86.177.177/pordefecto/api/Creditos/Periodos","Operacion",gsonCuerpo).get();
                Type listType = new TypeToken<ArrayList<Periodos_de_Pago>>(){}.getType();
                List<Periodos_de_Pago> periodos_de_pagos =new Gson().fromJson(respuestaLogin,listType);
                ArrayList<String> p = new ArrayList<String>();


                if (periodos_de_pagos != null) {
                    for (Periodos_de_Pago periodos : periodos_de_pagos) {

                        periodos.getId_Periodo();
                        periodos.getDescripcion();
                        p.add(periodos.getId_Periodo()+ " - "+ periodos.getDescripcion());

                    }
                    final Object[] id =  p.toArray();
                    ArrayAdapter<String> h = new ArrayAdapter<String>(getContext(),R.layout.spinner_item,p);
                    frecuencia.setAdapter(h);

                    frecuencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String valor = id[i].toString();
                            String[] idtraido = valor.split("-");
                            String idfre = idtraido[0];
                            idfrecuencia.setText(idfre);
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }else {
            Toast toast = Toast.makeText(getContext(), "Conectate a una Red de Internet", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }




}
