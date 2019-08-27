package com.example.prueba.Helper;

import android.net.http.HttpResponseCache;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;


import android.os.AsyncTask;

import com.google.gson.Gson;


import java.util.List;

public class ConexionApi extends AsyncTask<String, Void, String> {

    private String Token="Token";
    private String Usuario="Usuario";
    private String Autenticado="esautenticado";
    private String UrlApi="Http:190.86.177.177/";
    private static HttpClient cliente;
    private HttpContext httpContext;
    public final String COOKIE_STORE = "http.cookie-store";
    private BasicCookieStore cookieData = new BasicCookieStore();

    public ConexionApi() {
        this.httpContext = new BasicHttpContext();
        this.httpContext.setAttribute(this.COOKIE_STORE, this.cookieData);
    }

    @Override
    protected String doInBackground(String... datos) {
        String respuesta="Trabajando";
        String params="";
        String recurso="Login";
        String url="";
        if(datos.length>2){
            params=datos[2];
        }
        recurso=datos[1];
        url=datos[0];
        switch (recurso){
            case "Login":
                respuesta= LoginBack(url,params);
                break;
            case "Operacion":
                respuesta=OperarApi(url,params);
        }

        return respuesta;
    }

    public ConexionApi(BasicCookieStore cookieData){
        this.httpContext = new BasicHttpContext();
        this.cookieData=cookieData;
        this.httpContext.setAttribute(this.COOKIE_STORE, this.cookieData);
    }

    private String LoginBack(String url, String params) {

        Gson gson=new Gson();
        DataHTTP[] datos=null;
        if(!params.equals("")){
            datos=gson.fromJson(params,DataHTTP[].class);
        }
        try{
            HttpClient httpClient= new DefaultHttpClient();
            HttpPost request=new HttpPost(url);

            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            JSONObject json=new JSONObject();
            for(int i=0;i<datos.length;i++){
                String nombre=datos[i].getNombre();
                if(nombre.equals("login"))
                {
                    Login g=gson.fromJson(datos[i].getValor(),Login.class);
                    json.put("User",g.getUser());
                    json.put("Password",g.getPassword());
                }
            }
            StringEntity jsonCodificado =new StringEntity(json.toString());
            jsonCodificado.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));

            //List<NameValuePair> valores=new ArrayList<NameValuePair>();
            //valores.add(new BasicNameValuePair("content",jsonCodificado.toString()));

            request.setEntity(jsonCodificado);
            HttpResponse response=httpClient.execute(request);

            HttpEntity entity=response.getEntity();

            InputStream instream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
            String strdata = null;
            String jsonString = "" ;
            while( (strdata =reader.readLine())!=null)
            {
                jsonString += strdata;
            }
            //return  response.getStatusLine().getStatusCode();
            return jsonString;
            //String otrodato=jsonString;
        }catch (Exception ex){
            String fallo=ex.getMessage();
            return "";
        }
    }

    private String OperarApi(String url, String params){
        Gson gson=new Gson();
        DataHTTP[] datos=null;
        if(!params.equals("")){
            datos=gson.fromJson(params,DataHTTP[].class);
        }
        try{

            String strdata = null;
            String jsonString = "" ;
            String valor = "";

            JSONObject json=new JSONObject();
            for(int i=0;i<datos.length;i++){
                String nombre=datos[i].getNombre();
                if(nombre.equals("buscar_cliente"))
                {
                    HttpResponse response=null;
                    String verbo=datos[i].getVerbo();
                    if(verbo.equals("get"))
                    {
                        HttpClient httpClient= new DefaultHttpClient();
                        HttpGet request=new HttpGet(url);
                        request.setHeader("Accept", "application/json");
                        request.setHeader("Content-type", "application/json");
                        String token=datos[i].getValor();
                        request.setHeader("Authorization","Bearer "+token);

                        response=httpClient.execute(request);
                    }
                    if(verbo.equals("post"))
                    {
                        HttpClient httpClient= new DefaultHttpClient();
                        HttpPost request=new HttpPost(url);
                        request.setHeader("Accept", "application/json");
                        request.setHeader("Content-type", "application/json");
                        String token=datos[i].getValor();
                        request.setHeader("Authorization","Bearer "+token);

                        response=httpClient.execute(request);
                    }

                    HttpEntity entity=response.getEntity();
                    InputStream instream = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                    while( (strdata =reader.readLine())!=null)
                        jsonString += strdata;

                }else if(nombre.equals("coordenadas")){
                    HttpResponse response=null;
                    String verbo=datos[i].getVerbo();
                    if(verbo.equals("put"))
                    {
                        HttpClient httpClient= new DefaultHttpClient();
                        HttpPut request=new HttpPut(url);
                        request.setHeader("Accept", "application/json");
                        request.setHeader("Content-type", "application/json");
                        StringEntity stringEntity = new StringEntity(datos[i].getContenido());
                        request.setEntity(stringEntity);
                        String token=datos[i].getValor();
                        request.setHeader("Authorization","Bearer "+token);

                        response=httpClient.execute(request);
                    }

                }else{
                    break;
                }
            }
            //return  response.getStatusLine().getStatusCode();
            return jsonString;
            //String otrodato=jsonString;
        }catch (Exception ex){
            String fallo=ex.getMessage();
            return "";
        }
    }

    private void ObtenerTokenAlmacenado()
    {
        List<Cookie> veamos=cookieData.getCookies();

    }
}
