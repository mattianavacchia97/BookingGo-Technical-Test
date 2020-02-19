package com.mattianavacchia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class AllSupplier {
    final int READ_TIMEOUT = 2000;
    final String[] supplier = new String[] {"dave", "eric", "jeff"};
    private final Map<String, Integer> carTypePassengers;
    {
        Map<String, Integer> aMap = new HashMap<>();
        aMap.put("STANDARD", 4);
        aMap.put("EXECUTIVE", 4);
        aMap.put("LUXURY", 4);
        aMap.put("PEOPLE_CARRIER", 6);
        aMap.put("LUXURY_PEOPLE_CARRIER", 6);
        aMap.put("MINIBUS", 16);
        carTypePassengers = Collections.unmodifiableMap(aMap);
    }

    void getAllSupplierResult() {
        for (String s: supplier) {
            parseJSON();
        }
    }

    void parseJSON() {

    }

    void getJSONFromUrl(final String url) throws IOException {
        URL urlForGetRequest = new URL(url);
        String readLine = null;

        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        //connection.setConnectTimeout(5000);
        connection.setReadTimeout(READ_TIMEOUT);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            // print result
            System.out.println("JSON String Result " + response.toString());
            //GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
        }
    }
}
