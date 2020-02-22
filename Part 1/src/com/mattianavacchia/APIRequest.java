package com.mattianavacchia;

import static spark.Spark.get;

public class APIRequest {

    public static void callAPIDave(final String urlApi) {
        get("/dave", (req, res)-> {
            res.type("application/json");
            return MapToJSON.jsonDave(Dave.getDaveResults(urlApi));
        });
    }

    public static void callAPIAll(final String urlApi, final String pickup, final String dropoff, final int numberOfPassengers) {
        get("/all", (req, res)-> {
            res.type("application/json");
            return MapToJSON.jsonAll(AllSupplier.getAllSupplierResult(urlApi, pickup, dropoff, numberOfPassengers));
        });
    }
}
