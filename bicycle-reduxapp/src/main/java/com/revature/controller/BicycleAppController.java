package com.revature.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
//import org.apache.log4j.Logger;

import com.revature.beans.*;
import com.revature.data.*;
import com.revature.utils.DBConnection;

public class BicycleAppController {

    static String dbUrl = "jdbc:postgresql://demo.czstjrspshsd.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=Demo1";
    static String userName = "palle017";
    static String password = "3edc1qaZ";

//    static Logger log = Logger.getLogger(BicycleAppController.class.getName());
    public static void main(String[] args) 
    {

        Scanner scan = null;
        try (Connection dbConnection = DBConnection.getConnection(dbUrl, userName, password)) {

            scan = new Scanner(System.in);

            System.out.println("----------------WELCOME TO MY BICYCLE SHOP!!!!!!-----------------\n");
            System.out.println("MAIN MENU :-\n");
            System.out.println("1. Register\n2. Log in\nPress any other key to Exit\n");

            Integer userInput;
            userInput = Integer.valueOf(scan.nextLine());

            Person currentUser = new Person();
            switch (userInput) {
                case 1:
                    System.out.println("Enter username");
                    String userName = scan.nextLine();
                    System.out.println("Type of Users:\n * User\n * Customer \n * Employee \n * System ");
                    System.out.println("Enter Type: ");
                    String type = scan.nextLine();


                    // System.out.println(currentUser);

                    currentUser = new PersonDao(dbConnection) {
                        @Override
                        public Person getUsername(String username) {
                            return null;
                        }

                        @Override
                        public Set<Person> getAll() {
                            return null;
                        }

                        @Override
                        public void update(Person t) {

                        }
                    }.createUser(userName, type);
                    break;
                case 2:
                    System.out.println("Enter username: ");
                    String userId2 = scan.nextLine();
                    currentUser = new PersonDao(dbConnection) {
                        @Override
                        public Person getUsername(String username) {
                            return null;
                        }

                        @Override
                        public Set<Person> getAll() {
                            return null;
                        }

                        @Override
                        public void update(Person t) {

                        }
                    }.loginUser(userId2);
                    break;
                default:
                    break;
            }
           // System.out.println(currentUser);

            while (true) {
                Integer bicycleId;
                List<Bicycle> bicycleList;
                List<Offer> offerList;
                //  Person person=new Person();
                String t = "";
              //  System.out.println(currentUser);
                System.out.println("What would you like to do?");

                if (t=="Customer") {
                    System.out.println("2. Make an offer\n");
                    System.out.println("3. Show my bicycles\n");
                    System.out.println("4. View my payments\n");
                } else if (t == "Employee" || t == "employee") {

                } else {
                    System.out.println("1. View available bicycles\n");

                    System.out.println("5. Select and accept offer\n");
                    System.out.println("6. Remove bicycle\n");
                    System.out.println("7. View all payments\n");
                    System.out.println("8. Add bicycle\n");
                    System.out.println("9. Update bicycle state\n");
                    System.out.println("10. Reject pending offers\n");
                    System.out.println("11. Weekly payments\n");

                }

                int userChoice = Integer.parseInt(scan.nextLine());
                switch (userChoice) {
                    case 1:
                        bicycleList = new BicycleDao().getAllBicycles();
                        System.out.println(bicycleList);
                        break;
                    case 2:
                        System.out.println("Enter bicycle ID: ");
                        bicycleId = Integer.valueOf(scan.nextLine());
                        System.out.println("Enter price");
                        Integer price = Integer.valueOf(scan.nextLine());
                        new OfferDao(dbConnection).addOffer(new Offer(555, currentUser.getName(), bicycleId, price, false));

                    case 3:
                        bicycleList = new BicycleDao().getBicyclesForUser(currentUser.getName());
                        System.out.println(bicycleList);
                        break;

                    case 4:
                        offerList = new OfferDao(dbConnection).getOffersForUser(currentUser.getName());
                        System.out.println(offerList);
                        break;

                    case 5:
                        System.out.println("Enter bicycle ID: ");
                        bicycleId = Integer.valueOf(scan.nextLine());
                        System.out.println("Offer prices: ");
                        offerList = new OfferDao(dbConnection).getOffersForBicycle(bicycleId);
                        System.out.println(offerList);
                        System.out.println("Enter offer ID: ");
                        Integer offerId = Integer.valueOf(scan.nextLine());
                        String offerUser = new OfferDao(dbConnection).acceptOffer(bicycleId, offerId);
                        new BicycleDao().updateOwner(bicycleId, offerUser);

                        break;
                    case 6:
                        System.out.println("Enter bicycle ID: ");
                        bicycleId = Integer.valueOf(scan.nextLine());
                        new BicycleDao().removeBicycle(bicycleId);
                        break;
                    case 7:
                        offerList = new OfferDao(dbConnection).getCompleteOffers();
                        System.out.println(offerList);
                        break;
                    case 8:
                        System.out.println("Enter bicycle ID: ");
                        bicycleId = Integer.valueOf(scan.nextLine());
                        System.out.println("Enter bicycle name: ");
                        String bicycleName = scan.nextLine();
                        new BicycleDao().addBicycle(bicycleId, bicycleName, null);
                        break;
                    case 9:
                        System.out.println("Enter bicycle ID: ");
                        break;

                    case 10:
                        System.out.println("Enter bicycle ID: ");
                        //break;
                    case 11:
                        System.out.println("Enter bicycle ID: ");
                        break;


                    default:
                        System.out.println("See you next time!");
                        break;

                }

                System.out.println("Continue: Y/N?");
                if (scan.nextLine().charAt(0) == 'N') {
                    break;
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        scan.close();
    }


	private static void type() {
		// TODO Auto-generated method stub
		
	}
}
