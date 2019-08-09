package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prueba.Helper.ConexionApi;
import com.example.prueba.Helper.DataHTTP;
import com.example.prueba.Helper.Login;
import com.google.gson.Gson;

import org.apache.http.impl.client.BasicCookieStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class prueba extends AppCompatActivity implements View.OnClickListener{
    private EditText usuario;
    private EditText contrasena;
    private Button acceder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        usuario=(EditText) findViewById(R.id.usuario);
        contrasena=(EditText) findViewById(R.id.contrasena);
        acceder =(Button) findViewById(R.id.acceder);
        acceder.setOnClickListener(this);

        /*valores de prueba*/
        usuario.setText("noelnio@hotmail.com");
        contrasena.setText("Inteligencia_2018");
    }

    @Override
    public void onClick(View view) {

        ConexionApi cp=new ConexionApi();
        Login login=new Login(usuario.getText().toString(),contrasena.getText().toString());
        String gsonLogin=new Gson().toJson(login);
        List<DataHTTP> listData= new ArrayList<DataHTTP>();
        listData.add(new DataHTTP("login",gsonLogin,"post",""));
        String gsonCuerpo=new Gson().toJson(listData);

        try {
            String respuestaLogin=cp.execute("http://190.86.177.177/pordefecto/api/Account/Login","Login",gsonCuerpo).get();
            if(respuestaLogin!="")
            {


        Intent pagos_corriente=new Intent(this,MainActivity.class);
        pagos_corriente.putExtra("token",respuestaLogin.replace('"', ' ').trim());
        startActivity(pagos_corriente);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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
