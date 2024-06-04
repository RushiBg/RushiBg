package com.Task1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;


public class OnlineReservationSystem {

    static Map<String, String> userDatabase = new HashMap<>();
    static Map<String, Reservation> reservationDatabase = new HashMap<>();

    static class Reservation {
        String trainNumber;
        String trainName;
        String classType;
        String dateOfJourney;
        String fromPlace;
        String toDestination;

        Reservation(String trainNumber, String trainName, String classType, String dateOfJourney, String fromPlace, String toDestination) {
            this.trainNumber = trainNumber;
            this.trainName = trainName;
            this.classType = classType;
            this.dateOfJourney = dateOfJourney;
            this.fromPlace = fromPlace;
            this.toDestination = toDestination;
        }

        @Override
        public String toString() {
            return "Train Number: " + trainNumber + ", Train Name: " + trainName + ", Class Type: " + classType + 
                   ", Date of Journey: " + dateOfJourney + ", From: " + fromPlace + ", To: " + toDestination;
        }
    }

    public static void main(String[] args) {
        // Sample users
        userDatabase.put("abc", "ru");
        userDatabase.put("pqr", "st");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Online Reservation System");
        System.out.print("\nEnter Login ID: ");
        String loginId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (authenticateUser(loginId, password)) {
            System.out.println("Login Successful!");

            boolean exit = false;
            while (!exit) {
                System.out.println("\n1. Reserve Ticket");
                System.out.println("2. Cancel Ticket");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        reserveTicket(scanner);
                        break;
                    case 2:
                        cancelTicket(scanner);
                        break;
                    case 3:
                        exit = true;
                        System.out.println("GoodBye!!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid Login ID or Password!");
        }

        scanner.close();
    }

    private static boolean authenticateUser(String loginId, String password) {
        return userDatabase.containsKey(loginId) && userDatabase.get(loginId).equals(password);
    }

    private static void reserveTicket(Scanner scanner) {
        System.out.print("Enter Train Number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter Train Name: ");
        String trainName = scanner.nextLine();
        System.out.print("Enter Class Type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter Date of Journey (YYYY-MM-DD): ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter From Place: ");
        String fromPlace = scanner.nextLine();
        System.out.print("Enter To Destination: ");
        String toDestination = scanner.nextLine();

        String pnr = generatePNR();
        Reservation reservation = new Reservation(trainNumber, trainName, classType, dateOfJourney, fromPlace, toDestination);
        reservationDatabase.put(pnr, reservation);

        System.out.println("\nTicket Reserved Successfully!");
        System.out.println("\nYour PNR: " + pnr);
    }

    private static void cancelTicket(Scanner scanner) {
        System.out.print("\nEnter PNR Number: ");
        String pnr = scanner.nextLine();

        if (reservationDatabase.containsKey(pnr)) {
            Reservation reservation = reservationDatabase.get(pnr);
            System.out.println("\nReservation Details: " + reservation);
            System.out.print("\nDo you want to cancel this reservation? (y/n): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("y")) {
                reservationDatabase.remove(pnr);
                System.out.println("\nReservation Cancelled Successfully!");
            } else {
                System.out.println("\nCancellation Aborted.");
            }
        } else {
            System.out.println("\nInvalid PNR Number!");
        }
    }

    private static String generatePNR() {
        return "PNR" + UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
    }
}