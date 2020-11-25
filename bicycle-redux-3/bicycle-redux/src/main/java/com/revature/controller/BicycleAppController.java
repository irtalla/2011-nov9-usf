package com.revature.controller;

import java.util.List;
import java.util.Scanner;
//import org.apache.log4j.Logger;

import com.revature.beans.*;
import com.revature.services.BicycleService;
import com.revature.services.PersonService;

public class BicycleAppController {

    //    static Logger log = Logger.getLogger(BicycleAppController.class.getName());
    public static void main(String[] args) {

        BicycleService bicycleService = new BicycleService();
        PersonService personService = new PersonService();

        Scanner scan = new Scanner(System.in);

        System.out.println("----------------WELCOME TO MY BICYCLE SHOP!!!!!!-----------------\n");

        Person currentUser = null;
        while (currentUser == null) {
            System.out.println("MAIN MENU :-\n");
            System.out.println("1. Register\n2. Log in\n");

            Integer userInput = Integer.valueOf(scan.nextLine());
            switch (userInput) {
                case 1:
                    System.out.println("Enter username");
                    String userName = scan.nextLine();
                    System.out.println("Type of Users:\n * User\n * Customer \n * Employee \n * System ");
                    System.out.println("Enter Type: ");
                    String type = scan.nextLine();

                    currentUser = personService.createUser(userName, type);
                    break;
                case 2:
                    System.out.println("Enter username: ");
                    String userId2 = scan.nextLine();
                    currentUser = personService.loginUser(userId2);
                    break;
                default:
                    break;
            }
        }
        System.out.println("Logged in user:");
        System.out.println(currentUser);


        while (true) {
            Integer bicycleId;
            List<Bicycle> bicycleList;
            List<Offer> offerList;
            String t = "";
            System.out.println(currentUser);
            System.out.println("What would you like to do?");

            if (currentUser.getType().equalsIgnoreCase("customer")) {
                System.out.println("1. View available bicycles\n");
                System.out.println("2. Make an offer\n");
                System.out.println("3. Show my bicycles\n");
                System.out.println("4. View my payments\n");
            } else if (currentUser.getType().equalsIgnoreCase("employee")) {
                System.out.println("5. Select and accept offer\n");
                System.out.println("6. Remove bicycle\n");
                System.out.println("7. View all payments\n");
                System.out.println("8. Add bicycle\n");
            } else {
                System.out.println("9. Update bicycle state\n");
                System.out.println("10. Reject pending offers\n");
                System.out.println("11. Weekly payments\n");
            }

            int userChoice = Integer.valueOf(scan.nextLine());
            switch (userChoice) {
                case 1:
                    bicycleList = bicycleService.getAllBicycles();
                    System.out.println(bicycleList);
                    break;
                case 2:
                    System.out.println("Enter bicycle ID: ");
                    bicycleId = Integer.valueOf(scan.nextLine());
                    System.out.println("Enter price");
                    Integer price = Integer.valueOf(scan.nextLine());
                    bicycleService.addOffer(new Offer(555, currentUser.getName(), bicycleId, price, false));

                case 3:
                    bicycleList = bicycleService.getBicyclesForUser(currentUser.getName());
                    System.out.println(bicycleList);
                    break;

                case 4:
                    offerList = bicycleService.getOffersForUser(currentUser.getName());
                    System.out.println(offerList);
                    break;

                case 5:
                    System.out.println("Enter bicycle ID: ");
                    bicycleId = Integer.valueOf(scan.nextLine());
                    System.out.println("Offer prices: ");
                    offerList = bicycleService.getOffersForBicycle(bicycleId);
                    System.out.println(offerList);
                    System.out.println("Enter offer ID: ");
                    Integer offerId = Integer.valueOf(scan.nextLine());
                    bicycleService.selectAndAcceptOffer(offerId, bicycleId);
                    break;
                case 6:
                    System.out.println("Enter bicycle ID: ");
                    bicycleId = Integer.valueOf(scan.nextLine());
                    bicycleService.removeBicycle(bicycleId);
                    break;
                case 7:
                    offerList = bicycleService.getCompleteOffers();
                    System.out.println(offerList);
                    break;
                case 8:
                    System.out.println("Enter bicycle ID: ");
                    bicycleId = Integer.valueOf(scan.nextLine());
                    System.out.println("Enter bicycle name: ");
                    String bicycleName = scan.nextLine();
                    bicycleService.addBicycle(bicycleId, bicycleName, null);
                    break;
                case 9:
                    System.out.println("Enter bicycle ID: ");

                case 10:
                    System.out.println("Enter bicycle ID: ");
                case 11:
                    System.out.println("Enter bicycle ID: ");

                default:
                    System.out.println("Invalid option");
                    break;
            }

            System.out.println("Continue: Y/N?");
            if (!scan.nextLine().equalsIgnoreCase("Y")) {
                break;
            }

        }
        scan.close();
    }
}
