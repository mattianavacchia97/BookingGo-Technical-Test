package com.mattianavacchia;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
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
    }
}
