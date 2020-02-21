package com.mattianavacchia;

import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

class AllSupplier {
    private static final int READ_TIMEOUT = 2000;
    private static final String[] supplier = new String[] {"Dave", "Eric", "Jeff"};
    private static Map<String, Integer> carTypePassengers =
            Map.of("STANDARD", 4, "EXECUTIVE", 4, "LUXURY", 4, "PEOPLE_CARRIER", 6, "LUXURY_PEOPLE_CARRIER", 6, "MINIBUS", 16);
    private static int passengers = 0;
    private static Map<String, Pair<String, Integer>> map = new HashMap<>();
    private static int currentSupplier = 0;

    static void getAllSupplierResult(final String urlApi, final String pickup, final String dropoff, final int numberOfPassengers) {
        for (int i = 0; i < supplier.length; i++) {
            currentSupplier = i;
            passengers = numberOfPassengers;
            parseJSON(getJSONFromUrl(urlApi + supplier[i] + "?pickup=" + pickup + "&dropoff=" + dropoff, i));
        }

        if (map.size() == 0)
            System.out.println("No cars available.");
        else
            for(Map.Entry<String, Pair<String, Integer>> entry : map.entrySet())
                System.out.println("{" + entry.getKey() + "} - {" + entry.getValue().getKey() + "} - {" + entry.getValue().getValue() + "}");
    }

    private static void parseJSON(final JSONObject jsonObject) {
        if (jsonObject != null) {
            JSONArray options = jsonObject.getJSONArray("options");
            for(Object o: options)
                if ( o instanceof JSONObject ) {
                    String car_type = (String) ((JSONObject) o).get("car_type");
                    int price = ((JSONObject) o).getInt("price");

                    if (carTypePassengers.containsKey(car_type)
                        && carTypePassengers.get(car_type) >= passengers) {
                        if (map.containsKey(car_type)) {
                            if (map.get(car_type).getValue() > price)
                                map.replace(car_type, new Pair<>(supplier[currentSupplier], price));
                        } else
                            map.put(car_type, new Pair<>(supplier[currentSupplier], price));
                    }
                }
        }
    }

    private static JSONObject getJSONFromUrl(final String url, final int i) {
        try {
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
                StringBuilder response = new StringBuilder();
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);
                } in .close();
                // print result
                System.out.println("JSON String Result " + response.toString());
                return new JSONObject(response.toString());
                //GetAndPost.POSTRequest(response.toString());
            } else {
                System.out.println("HTTP connection to " + supplier[i] + " did not work.");
                return null;
            }
        } catch (IOException e) {
            if (e instanceof SocketTimeoutException) {
                System.out.println(supplier[i] + " is not taken into consideration due to lack of response.");
                return null;
            }
        }
        return null;
    }
}
