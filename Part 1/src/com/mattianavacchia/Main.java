package com.mattianavacchia;

import java.io.IOException;

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

        if (supplier.contains("dave"))
            Dave.getDaveResults(urlApi + supplier + "?pickup=" + pickup + "&dropoff=" + dropoff);
        else {
            numberOfPassengers = Integer.parseInt(args[3]);
            AllSupplier.getAllSupplierResult(urlApi, pickup, dropoff, numberOfPassengers);
        }
    }
}
