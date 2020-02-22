package com.mattianavacchia;

public class Main {

    public static void main(String[] args) {

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

        if (supplier.contains("dave")) {
            if (args.length == 4) {
                APIRequest.callAPIDave(urlApi + supplier + "?pickup=" + pickup + "&dropoff=" + dropoff);
            } else
                Dave.getDaveResults(urlApi + supplier + "?pickup=" + pickup + "&dropoff=" + dropoff);
        } else {
            numberOfPassengers = Integer.parseInt(args[3]);

            if (args.length == 5) {
                APIRequest.callAPIAll(urlApi, pickup, dropoff, numberOfPassengers);
            } else {
                AllSupplier.getAllSupplierResult(urlApi, pickup, dropoff, numberOfPassengers);
            }
        }
    }
}
