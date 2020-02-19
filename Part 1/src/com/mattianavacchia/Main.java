package com.mattianavacchia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String supplier = "", pickup = "", dropoff = "";

        if (args.length == 0) {
            System.out.println("Provide name of rider. (Dave, Eric, Jeff)");
            System.exit(0);
        } else {
            supplier = args[0].toLowerCase();
        }

        // pickup
        System.out.print("Enter pickup location in the format longitude, latitude (i.e. 51.470020,-0.454295) : ");
        Scanner scanner = new Scanner(System. in);
        pickup = scanner. nextLine();

        // dropoff
        System.out.print("Enter dropoff location in the format longitude, latitude (i.e. 3.410632,-2.157533) : ");
        dropoff = scanner. nextLine();

        readJSON(Objects.requireNonNull(readJsonFromUrl("https://techtest.rideways.com/" + supplier + "?pickup=" + pickup + "&dropoff=" + dropoff)));
    }

    private static void readJSON(JSONObject jsonObject) {
        // get options
        JSONArray options = jsonObject.getJSONArray("options");
        Map<String, Integer> map = new HashMap<>(), sortMap;
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();

        for(Object o: options){
            if ( o instanceof JSONObject ) {
                map.put(((JSONObject) o).getString("car_type"), ((JSONObject) o).getInt("price"));
            }
        }

        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        for(Map.Entry<String, Integer> entry : reverseSortedMap.entrySet())
            System.out.println("{" + entry.getKey() + "} - {" + entry.getValue() + "}");
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1)
            sb.append((char) cp);

        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(final String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            return new JSONObject(readAll(rd));
        }  catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
