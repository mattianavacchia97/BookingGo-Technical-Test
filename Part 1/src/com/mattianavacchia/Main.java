package com.mattianavacchia;

import org.json.JSONObject;

import java.io.IOException;

import static spark.Spark.get;

public class Main {

    public static void main(String[] args) throws IOException {

        String supplier = "", pickup = "", dropoff = "", urlApi = "https://techtest.rideways.com/";
        int numberOfPassengers = 0;

        if (args.length == 0) {
            System.out.println("Provide name of rider/riders (dave or all). Pickup location. Dropoff location. Number " +
                    "of passengers.");
            System.exit(0);
        } else {
            supplier = args[0].toLowerCase();
            pickup = args[1];
            dropoff = args[2];
        }

        String finalPickup = pickup;
        String finalDropoff = dropoff;

        if (supplier.contains("dave")) {
            if (args.length == 4) {
                String finalSupplier = supplier;
                get("/dave", (req, res)-> {
                    res.type("application/json");
                    return MapToJSON.jsonDave(Dave.getDaveResults(urlApi + finalSupplier + "?pickup=" + finalPickup + "&dropoff=" + finalDropoff));
                });
            } else
                Dave.getDaveResults(urlApi + supplier + "?pickup=" + pickup + "&dropoff=" + dropoff);
        } else {
            numberOfPassengers = Integer.parseInt(args[3]);
            int finalNumberOfPassengers = numberOfPassengers;

            if (args.length == 5) {
                get("/all", (req, res)-> {
                    res.type("application/json");
                    return MapToJSON.jsonAll(AllSupplier.getAllSupplierResult(urlApi, finalPickup, finalDropoff, finalNumberOfPassengers));
                });
            } else {
                AllSupplier.getAllSupplierResult(urlApi, pickup, dropoff, numberOfPassengers);
            }
        }
    }
}
