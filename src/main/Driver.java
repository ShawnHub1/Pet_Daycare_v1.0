package main;

import controllers.DayCare;
import models.*;
import utils.ScannerInput;
import utils.ISerializer;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    //    private Scanner input = new Scanner(System.in);
    private DayCare daycare = new DayCare("Pet Daycare", 90, new ArrayList<>());

    public static void main(String[] args) {
        new Driver();
    }

    public Driver() {
     //   this.daycare = new DayCare("Pet Daycare", 90, new ArrayList<>());
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
                   1) Pet Admin Menu
                   2) Reports
                   3) Search Pets 
                   4) Sort Pets
                   5) Save Pets to Pets.xml
                   6) Load Pets from Pets.xml
                   0) Exit
                 ==>> """);
        int option = ScannerInput.readNextInt("Select Option: ");

        while (option != 0) {
            switch (option) {
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

        while (option != 0) {
            switch (option) {
                case 1 -> addPet();
                case 7 -> exitApp();
                default -> System.out.println("Invalid option selected: " + option);
            }
        }
        return option;
    }


    private void addPet() {

        boolean isAdded = false;

        int option = ScannerInput.readNextInt("""
                ---------------------------
                |   1) Add a Cat          |
                |   2) Add a Dog          |
                ---------------------------
                ==>> """);

        switch (option) {
            case 1 -> {
                String name = ScannerInput.readNextLine("Enter the cats name:  ");
                String owner = ScannerInput.readNextLine("Enter the owners name:  ");
                String favouriteToy = ScannerInput.readNextLine("Cats favourite toy:  ");
                String filename = ScannerInput.readNextLine("Enter filename:  ");
                char sex = ScannerInput.readNextChar("Gender of cat? (m/f):  ");
                boolean[] daysAttending = readDaysAttending();

                //had issues with the indoorCat char but added below from lecture to get passed this with the boolean
                char isIndoorCatChar = ScannerInput.readNextChar("Is this an indoor cat? (y/n):  ");
                boolean indoorCat = false;
                if ((isIndoorCatChar == 'y') || (isIndoorCatChar == 'Y'))
                    indoorCat = true;


                Cat cat = new Cat(name, owner, daysAttending, sex, indoorCat, favouriteToy);
                isAdded = daycare.addPet(cat);
            }
            case 2 -> {
                String name = ScannerInput.readNextLine("Enter the dog's name: ");
                String owner = ScannerInput.readNextLine("Enter the owner's name: ");
                String breed = ScannerInput.readNextLine("Enter the dog's breed: ");
                char sex = ScannerInput.readNextChar("Gender of dog? (m/f): ");
                boolean[] daysAttending = readDaysAttending();

                //same approach as Cat here with the char and boolean for neutered and dangerous dog
                char neuteredChar = ScannerInput.readNextChar("Is this dog neutered? (y/n):  ");
                boolean neutered = false;
                if ((neuteredChar == 'y') || (neuteredChar == 'Y'))
                    neutered = true;

                char dangerousChar = ScannerInput.readNextChar("Is this a dangerous breed? (y/n):  ");
                boolean dangerousBreed = false;
                if ((dangerousChar == 'y') || (dangerousChar == 'Y'))
                    dangerousBreed = true;

                Dog dog = new Dog(name, owner, daysAttending, sex, breed, dangerousBreed, neutered);
                isAdded = daycare.addPet(dog);
            }
            default -> System.out.println("Invalid option entered: " + option);
        }

        if (isAdded) {
            System.out.println("Pet Added Successfully");
        } else {
            System.out.println("No Pet Added");
        }
    }

    private boolean[] readDaysAttending() {
        boolean[] days = new boolean[5]; // Mon..Fri

        days[0] = ScannerInput.readNextChar("Attending Monday? (y/n): ") == 'y';
        days[1] = ScannerInput.readNextChar("Attending Tuesday? (y/n): ") == 'y';
        days[2] = ScannerInput.readNextChar("Attending Wednesday? (y/n): ") == 'y';
        days[3] = ScannerInput.readNextChar("Attending Thursday? (y/n): ") == 'y';
        days[4] = ScannerInput.readNextChar("Attending Friday? (y/n): ") == 'y';

        return days;
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
            System.out.println("Saving to file: " + daycare.fileName());
            this.daycare.save();
        } catch (Exception e) {
            System.err.println("Error writing to file" + e);
        }
    }

    private void loadPets() {
        try {
            System.out.println("Saving to file: " + daycare.fileName());
            this.daycare.load();
        } catch (Exception e) {
            System.err.println("Error reading to file" + e);
        }
    }
}

