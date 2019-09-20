package com.example.prueba;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba.Helper.Calculo_Credito;
import com.example.prueba.Helper.ConexionApi;
import com.example.prueba.Helper.DataHTTP;
import com.example.prueba.Helper.LineasCredito;
import com.example.prueba.Helper.Persona;
import com.example.prueba.Helper.enviarFoto;
import com.example.prueba.Helper.idtipoFoto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.impl.client.BasicCookieStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class fotografias extends Fragment {
    Spinner tipoFoto;
    TextView idcliente;
    TextView nombre;
    String ID;
    String iddocumento;
    Button btnsiguiente;
    private String key;
    private static final String LOG_TAG = "";
    ImageView imageView;
    private final String CARPETA_RAIZ = "ImagenesAyC/";
    private final String RUTA_IMAGEN =  CARPETA_RAIZ+"Documentos";
    private static final int WRITE_PERMISSION = 0x01;
    private String path;
    Bitmap bit;
    Bitmap bitm;
    Button btnfoto;
    Button btnenviar;
    String base;
    String nombreObtenido;
    String apellidoObtenido;
    String duiobtenido;
    String duicliente;
    String nombreCompleto;
    static final int REQUEST_IMAGE_CAPTURE = 20;
    public fotografias() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fotografias, container, false);
        tipoFoto = v.findViewById(R.id.tipofoto);
        imageView = v.findViewById(R.id.imagen);
        btnfoto = v.findViewById(R.id.tomarFoto);
        idcliente = v.findViewById(R.id.id_cliente);
        nombre = v.findViewById(R.id.nombre);
        Intent intent = getActivity().getIntent();
        key = intent.getExtras().get("token").toString();

        id();
        requestWritePermission();


        btnenviar = v.findViewById(R.id.enviarFoto);
        ID = getArguments() != null ? getArguments().getString("id"): "";
        duiobtenido = getArguments() != null ? getArguments().getString("dui"): "";
        nombreObtenido = getArguments() != null ? getArguments().getString("nombre"): "";
        apellidoObtenido = getArguments() != null ? getArguments().getString("apellido"): "";
        idcliente.setText(ID);
        duicliente = duiobtenido;
        nombreCompleto = nombreObtenido +" "+ apellidoObtenido;
        nombre.setText(nombreCompleto);
        btnfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificardocumento();
                //  abrirCamara();
            }
        });
        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviar();
            }
        });

        btnsiguiente = v.findViewById(R.id.siguiente);

            btnsiguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnsiguiente();

                }
            });


    return  v;
    }

    public void btnsiguiente(){
        if (checkIfLocationOpened()== false){
            Toast toast = Toast.makeText(getContext(), "Enciende el GPS", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }else{
            Intent intent = new Intent(getActivity(), gps.class);
            intent.putExtra("Id_usuario", String.valueOf(ID));
            intent.putExtra("nombreC", String.valueOf(nombreCompleto));
            intent.putExtra("DUI", String.valueOf(duiobtenido));
            startActivity(intent);
        }


    }

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if(requestCode == WRITE_PERMISSION){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(LOG_TAG, "Fallo al otorgar Permisos");
                Toast.makeText(getActivity(), "Permisos otorgados satisfactoriamente", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void requestWritePermission(){
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_PERMISSION);
        }
    }




    public void id(){
        ConexionApi cp=new ConexionApi();
        List<DataHTTP> listData= new ArrayList<DataHTTP>();
        listData.add(new DataHTTP("buscar_cliente",key,"get",""));
        String gsonCuerpo=new Gson().toJson(listData);
        try {
            String respuestaLogin=cp.execute("http://190.86.177.177/pordefecto/api/Documentos/Descripciones_Documento","Operacion",gsonCuerpo).get();
            Type listType = new TypeToken<ArrayList<idtipoFoto>>(){}.getType();
            List<idtipoFoto> idtipo =new Gson().fromJson(respuestaLogin,listType);
            ArrayList<String> p = new ArrayList<String>();
            if (idtipo != null) {
                for (idtipoFoto id : idtipo ) {
                    id.getId_Descripcion_Documento();
                    id.getDescripcion();
                    p.add(id.getId_Descripcion_Documento()+ " - " + id.getDescripcion());
                }
                final Object[] id =  p.toArray();
                ArrayAdapter<String> h = new ArrayAdapter<String>(getContext(),R.layout.spinner_item,p);
                tipoFoto.setAdapter(h);

                tipoFoto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String valor = id[i].toString();
                        String[] idtraido = valor.split("-");
                        String idlineac = idtraido[0];
                        iddocumento=idlineac.trim();
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
    }

    private void abrirCamara () {
        File fileImagen= new File(Environment.getExternalStorageDirectory(),RUTA_IMAGEN);
        boolean isCreada = fileImagen.exists();
        String nombreImagen= "";
        if (isCreada == false){
            isCreada = fileImagen.mkdirs();
        }

        if (isCreada== true){

            nombreImagen= System.currentTimeMillis()/100 +".jpg";
        }
        path = Environment.getExternalStorageDirectory()+File.separator+RUTA_IMAGEN + File.separator+nombreImagen;

        File imagen = new File(path);
        Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID, imagen));
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_IMAGE_CAPTURE:
                MediaScannerConnection.scanFile(getContext(), new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String s, Uri uri) {
                        Log.i("Path",""+path);
                    }
                });

                bitm= BitmapFactory.decodeFile(path);
                final int maxSize = 400;
                int outWidth;
                int outHeight;
                int inWidth = bitm.getWidth();
                int inHeight = bitm.getHeight();
                if(inWidth > inHeight){
                    outWidth = maxSize;
                    outHeight = (inHeight * maxSize) / inWidth;
                } else {
                    outHeight = maxSize;
                    outWidth = (inWidth * maxSize) / inHeight;
                }
                imageView.setImageBitmap(Bitmap.createScaledBitmap(bitm,outWidth,outHeight,false));
                bit = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                new AsyncTask<Void,Void,String>() {

                    @Override
                    protected String doInBackground(Void... voids) {
                        ByteArrayOutputStream base = new ByteArrayOutputStream();
                        bit.compress(Bitmap.CompressFormat.JPEG,100,base);
                        byte[] b = base.toByteArray();
                        String encodeImages = Base64.encodeToString(b,Base64.DEFAULT);
                        try {
                            base.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return encodeImages;
                    }
                    @Override
                    protected void onPostExecute(String s) {
                          base = s;
                    }
                }.execute();
                break;
        }



    }
    public void verificardocumento(){
        ConexionApi cp=new ConexionApi();
        int id_persona = Integer.parseInt((String) idcliente.getText());
        int id_des_documento = Integer.parseInt( iddocumento);
        List<DataHTTP> listData= new ArrayList<DataHTTP>();
        listData.add(new DataHTTP("buscar_cliente",key,"get",""));
        String gsonCuerpo=new Gson().toJson(listData);
        try {
            String respuesta_foto=cp.execute("http://190.86.177.177/pordefecto/api/Documentos/Verificar_Documento?id_descripcion_documento="+id_des_documento+"&id_persona="+id_persona,"Operacion",gsonCuerpo).get();

            if (respuesta_foto.length() >13 ){
                Toast toast = Toast.makeText(getContext(), "La Fotografia ya se encuentra guardada", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            }else{
                File fileImagen= new File(Environment.getExternalStorageDirectory(),RUTA_IMAGEN);
                boolean isCreada = fileImagen.exists();
                String nombreImagen= "";
                if (isCreada == false){
                    isCreada = fileImagen.mkdirs();
                }

                if (isCreada== true){

                    nombreImagen= System.currentTimeMillis()/100 +".jpg";
                }
                path = Environment.getExternalStorageDirectory()+File.separator+RUTA_IMAGEN + File.separator+nombreImagen;

                File imagen = new File(path);
                Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
                intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID, imagen));
                startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void enviar(){
        ConexionApi cp=new ConexionApi();
        int id_documento = 0;
        int id_persona = Integer.parseInt((String) idcliente.getText());
      //  int id_persona = 1154;
        int id_des_documento = Integer.parseInt( iddocumento);
        String nombreArchivo= "";
        String base64 = base;
        Persona persona = null;
        Boolean es_Imagen = true;
        Boolean es_PDF = false;
        enviarFoto  enviarFoto = new enviarFoto();
        enviarFoto.setId_Documento(id_documento);
        enviarFoto.setId_Persona(id_persona);
        enviarFoto.setId_Descripcion_Documento(id_des_documento);
        enviarFoto.setNombreArchivo(nombreArchivo);
        enviarFoto.setArchivo_Base64(base64);
        enviarFoto.setEs_Imagen(es_Imagen);
        enviarFoto.setEs_PDF(es_PDF);
        enviarFoto.setPersona(persona);
        String gsonFoto=new Gson().toJson(enviarFoto);
        List<DataHTTP> listData= new ArrayList<DataHTTP>();
        listData.add(new DataHTTP("persona",key,"post",gsonFoto));
        String gsonCuerpo=new Gson().toJson(listData);
        try {
            String respuesta_foto=cp.execute("http://190.86.177.177/pordefecto/api/Documentos/Nuevo_Documento","Operacion",gsonCuerpo).get();

            if( respuesta_foto.length() >3){
                Toast toast = Toast.makeText(getContext(), "Fotografia Guardada", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
                imageView.setImageBitmap(null);
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