package com.mattianavacchia;

public class Main {

    public static void main(String[] args) {

        String supplier = "", pickup = "", dropoff = "", urlApi = "https://techtest.rideways.com/";
        int numberOfPassengers;

        if (args.length < 3) {
            System.out.println("It was no possible to start the application. Follow the instructions inside the README file.");
            System.exit(0);
        } else {
            supplier = args[0].toLowerCase();
            pickup = args[1];
            dropoff = args[2];
        }

        if (supplier.contains("dave")) {
            // call only dave algorithm
            if (args.length == 4)
                APIRequest.callAPIDave(urlApi + supplier + "?pickup=" + pickup + "&dropoff=" + dropoff);
            else
                Dave.getDaveResults(urlApi + supplier + "?pickup=" + pickup + "&dropoff=" + dropoff);
        } else {
            // call algorithm responsible for all the suppliers
            numberOfPassengers = Integer.parseInt(args[3]);

            if (args.length == 5)
                APIRequest.callAPIAll(urlApi, pickup, dropoff, numberOfPassengers);
            else
                AllSupplier.getAllSupplierResult(urlApi, pickup, dropoff, numberOfPassengers);
        }
    }
}
