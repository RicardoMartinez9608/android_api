package com.example.prueba;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Inicio extends Fragment implements View.OnClickListener {
    Button pagos,localizacion,calculos,preinscrripcion;


    public Inicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_inicio, container, false);

        pagos = v.findViewById(R.id.btnpagos);
        localizacion = v.findViewById(R.id.btnlocalizacion);
        calculos = v.findViewById(R.id.btncalculo);
        preinscrripcion = v.findViewById(R.id.btnpreincipcion);

        pagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nuevoFragmento = new RedFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, nuevoFragmento);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });

        localizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nuevoFragmento = new GreenFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, nuevoFragmento);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });

        calculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nuevoFragmento = new CalculoCredito();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, nuevoFragmento);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();


            }
        });

        preinscrripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nuevoFragmento = new PreInscripcion();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, nuevoFragmento);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });

        return v;
    }




    @Override
    public void onClick(View v) {

    }

}
