package com.mattianavacchia;

import javafx.util.Pair;
import org.json.JSONObject;

import java.util.Map;

public class MapToJSON {
    public static JSONObject jsonDave(final Map<String, Integer> map) {
        return new JSONObject(map);
    }

    public static JSONObject jsonAll(final Map<String, Pair<String, Integer>> map) {
        JSONObject jsonObject = new JSONObject();
        JSONObject j;

        for (String key: map.keySet()) {
            j = new JSONObject();
            j.put("price", map.get(key).getValue());
            j.put("supplier", map.get(key).getKey());
            jsonObject.put(key.toLowerCase(), j);
        }

        System.out.println(jsonObject);
        return jsonObject;
    }
}
