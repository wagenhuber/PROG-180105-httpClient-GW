package com.sabel.httpclientgw;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class BusinessLogic {

    private URL url1 = null;
    private URLConnection urlConnection;
    private InputStream inputStream;
    private BufferedReader bufferedReader;
    private JsonObject jsonObject1;
    private Json json;
    private String jsonZeile, zeile;


    public void getObject() throws IOException {
        this.url1 = new URL("https://bitaps.com/api/ticker/average");
        this.urlConnection = url1.openConnection();
        this.inputStream = urlConnection.getInputStream();
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        while ((zeile = bufferedReader.readLine()) != null) {
            jsonZeile = zeile;
        }

        String[] splitArray = jsonZeile.split("\"eur\": \"");
        splitArray = splitArray[1].split("\"");

        String eurValue = splitArray[0];
        System.out.println(eurValue);

        System.out.println(jsonZeile);


        /**
         * Das JSON-Object aus dem Internet enthält wiederum ein weiteres JSon-Object names "fx_rates". Dieses muss zunächst extrahiert werden.
         */
        JsonReader reader = Json.createReader(new StringReader(jsonZeile));
        jsonObject1 = reader.readObject();
        reader.close();
        System.out.println("-------------Test JSon-------------------");
        JsonObject fx_rates = jsonObject1.getJsonObject("fx_rates");
        System.out.println(fx_rates.toString());
        System.out.println(fx_rates.getString("eur"));

    }


    public static void main(String[] args) throws IOException {
        new BusinessLogic().getObject();
    }

}
