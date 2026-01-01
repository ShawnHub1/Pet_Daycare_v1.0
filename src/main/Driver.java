package main;

import controllers.DayCare;
import models.*;
import utils.ScannerInput;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
//    private Scanner input = new Scanner(System.in);
    DayCare daycare;

    public static void main(String[] args) {
        new Driver();
    }

    public Driver() {
        runMenu();
        mainMenu();
    }
    private void runMenu() {
        int option = mainMenu();
        exitApp();
    }

    private int mainMenu() {
        System.out.print("""
                Pet Day Care Menu
                ---------
                   1) Pet CRUD Menu
                   2) Reports
                   3) Search Pets 
                   4) Sort Pets
                   5) Save Pets to Pets.xml
                   6) Load Pets from Pets.xml
                   0) Exit
                 ==>> """);
        int option = ScannerInput.readNextInt("Select Option: ");

        while (option !=0){
            switch (option){
                case 1 -> petAdminMenu();
                case 2 -> reportsMenu();
      //          case 3 -> searchPets();
      //          case 4 -> sortPets();
                case 5 -> savePets();
                case 6 -> loadPets();
                case 7 -> exitApp();
                default -> System.out.println("Invalid option selected: " + option);
            }
        }
        return option;
    }

    private int petAdminMenu() {
        System.out.print("""
                Pet admin Menu
                ---------
                   1) Add new pet
                   2) delete pet
                   3) Update pet
                   4) Sort Pets
                   5) Return to Main Menu
                 ==>> """);
        int option = ScannerInput.readNextInt("Select Option: ");
        return option;
    }

    private int reportsMenu() {
        System.out.print("""
                Reports Menu
                ---------
                   1) List all pets
                   2) List all dogs
                   3) List all cats
                   4) List dangerous dogs
                   5) Favourite toy
                   6) Neutered
                   0) Weekly income
                 ==>> """);
        int option = ScannerInput.readNextInt("Select Option: ");
        return option;
    }

    private void exitApp() {
        System.out.println("Exiting....");
        System.exit(0);
    }
    private void savePets() {
       try {
           this.daycare.save();
       } catch (Exception e) {
       System.err.println("Error writing to file" + e);
       }
    }

    private void loadPets() {
        try {
            this.daycare.load();
        } catch (Exception e) {
            System.err.println("Error reading to file" + e);
        }
    }
}

