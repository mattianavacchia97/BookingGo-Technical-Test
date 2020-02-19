package com.mattianavacchia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Dave {
    public static void getDaveResults(final String urlApi) {
        parseJSON(Objects.requireNonNull(readJsonFromUrl(urlApi)));
    }

    private static void parseJSON(final JSONObject jsonObject) {
        // get options
        JSONArray options = jsonObject.getJSONArray("options");
        Map<String, Integer> map = new HashMap<>(), sortMap;
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();

        for(Object o: options)
            if ( o instanceof JSONObject )
                map.put(((JSONObject) o).getString("car_type"), ((JSONObject) o).getInt("price"));

        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        for(Map.Entry<String, Integer> entry : reverseSortedMap.entrySet())
            System.out.println("{" + entry.getKey() + "} - {" + entry.getValue() + "}");
    }

    private static JSONObject readJsonFromUrl(final String url) throws JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            return new JSONObject(readAll(rd));
        }  catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1)
            sb.append((char) cp);

        return sb.toString();
    }
}
