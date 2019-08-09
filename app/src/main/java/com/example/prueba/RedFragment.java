package com.example.prueba;


import android.os.Bundle;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.prueba.Helper.ConexionApi;
import com.example.prueba.Helper.DataHTTP;
import com.example.prueba.Helper.Item_Pago;
import com.example.prueba.Helper.Pago;
import com.example.prueba.Helper.PagoCorriente;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.impl.client.BasicCookieStore;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class RedFragment extends Fragment {
    private String key;
    //

    public static final String TAG = "RedFragment";

    public static RedFragment newInstance(Bundle arguments){
        RedFragment f = new RedFragment();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }

    ListView pagos_lista;

    public RedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_red, container, false);

        try {
            Intent intent = getActivity().getIntent();
            key = intent.getExtras().get("token").toString();
        }catch (Exception ex){
            String error=ex.getMessage();
        }

       final Handler handler= new Handler();
        final Runnable runnable=new Runnable() {
                @Override
            public void run() {
                //handler.postDelayed(this,1000);
                ObtenerLista();
            }
        };
       handler.postDelayed(runnable,1000);

        return v;
    }

    public void ObtenerLista()
    {
        ConexionApi cp=new ConexionApi();
        List<DataHTTP> listData= new ArrayList<DataHTTP>();
        listData.add(new DataHTTP("buscar_cliente",key,"get",""));
        String gsonCuerpo=new Gson().toJson(listData);
        try {
            String respuestaLogin=cp.execute("http://190.86.177.177/pordefecto/api/Transacciones_Credito/Pagos_Realizados","Operacion",gsonCuerpo).get();
            Type collectionType=new TypeToken<Collection<PagoCorriente>>(){}.getType();
            final ArrayList<Pago> pagos=new ArrayList<Pago>();
            Collection<PagoCorriente> pagos_corrientes =  new Gson().fromJson(respuestaLogin,collectionType);

            if(pagos_corrientes!=null) {
                for (PagoCorriente pagosjson : pagos_corrientes) {
                    pagos.add(
                            new Pago(
                                    pagosjson.getTransaccion().getIdCredito().toString(),
                                    pagosjson.getNombreCompleto(),
                                    pagosjson.getTransaccion().getValorCuota(),
                                    pagosjson.getTransaccion().getSaldoCapital()
                            ));
                }
                pagos_lista = (ListView)getActivity().findViewById(R.id.pagos_lista);
                final Item_Pago items = new Item_Pago(this,pagos);
                pagos_lista.setAdapter(items);

                pagos_lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Pago item=pagos.get(position);
                        Toast tostado=Toast.makeText(getActivity().getApplicationContext(),item.getCodigo_Credito(),Toast.LENGTH_LONG);
                        tostado.show();
                    }
                });


            }
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
