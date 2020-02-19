package com.mattianavacchia;

public class Main {

    public static void main(String[] args) {

        String supplier = "", pickup = "", dropoff = "";

        if (args.length == 0) {
            System.out.println("Provide name of rider/riders (dave or all). Pickup location. Dropoff location. Number " +
                    "of passengers.");
            System.exit(0);
        } else {
            supplier = args[0].toLowerCase();
            pickup = args[1];
            dropoff = args[2];
        }

        Dave.getDaveResults("https://techtest.rideways.com/" + supplier + "?pickup=" + pickup + "&dropoff=" + dropoff);
    }
}
