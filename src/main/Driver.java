package main;

import controllers.DayCare;
import models.*;
import utils.ScannerInput;

public class Driver {
    private DayCare daycare = new DayCare("Pet Daycare", 90, "pets.xml");


    public static void main(String[] args) {
        new Driver();
    }
    public Driver() {
        runMenu();
    }

    private void runMenu() {
        int option = mainMenu();

        while (option != 0) {
            switch (option) {
                case 1 -> petAdminMenu();
                case 2 -> runReportsMenu();
                case 3 -> savePets();
                case 4 -> loadPets();
                default -> System.out.println("Invalid option selected: " + option);
            }
            ScannerInput.readNextLine("\nPress enter key to continue...");
            option = mainMenu();
        }
        exitApp();
    }


    private int mainMenu() {
        return ScannerInput.readNextInt("""
                Pet Day Care Menu
                ---------
                   1) Pet Admin Menu
                   2) Reports Menu
                   3) Save Pets to Pets.xml
                   4) Load Pets from Pets.xml
                   0) Exit
                 ==>> """);

    }


    private void petAdminMenu() {
        int option = adminMenu();

        while (option != 0) {
            switch (option) {
                case 1 -> addPet();
                case 2 -> deletePet();
                default -> System.out.println("Invalid option selected: " + option);
            }

            ScannerInput.readNextLine("\nPress enter key to continue...");
            option = adminMenu();
        }

    }


    private int adminMenu() {
        return ScannerInput.readNextInt("""
                Pet admin Menu
                ---------
                   1) Add new pet
                   2) Delete a pet
                   0) Return to Main Menu
                 ==>> """);

    }

    private void addPet() {

        boolean isAdded = false;

        int option = ScannerInput.readNextInt("""
                ---------------------------
                |   1) Add a Cat          |
                |   2) Add a Dog          |
                |   3) MainMenu           |
                ---------------------------
                ==>> """);

        switch (option) {
            case 1 -> {
                String name = ScannerInput.readNextLine("Enter the cats name:  ");
                String owner = ScannerInput.readNextLine("Enter the owners name:  ");
                String favouriteToy = ScannerInput.readNextLine("Cats favourite toy:  ");
                int age = ScannerInput.readNextInt("Enter cats age:  ");
                char sex = ScannerInput.readNextChar("Gender of cat? (m/f):  ");
                boolean[] daysAttending = readDaysAttending();
                //had issues with the indoorCat char but added below from lecture to get passed this with the boolean
                char isIndoorCatChar = ScannerInput.readNextChar("Is this an indoor cat? (y/n):  ");
                boolean indoorCat = false;
                if ((isIndoorCatChar == 'y') || (isIndoorCatChar == 'Y'))
                    indoorCat = true;


                Cat cat = new Cat(name, owner, age, sex, indoorCat, favouriteToy);
                cat.setDaysAttending(daysAttending);
                isAdded = daycare.addPet(cat);
            }
            case 2 -> {
                String name = ScannerInput.readNextLine("Enter the dog's name: ");
                String owner = ScannerInput.readNextLine("Enter the owner's name: ");
                String breed = ScannerInput.readNextLine("Enter the dog's breed: ");
                int age = ScannerInput.readNextInt("Enter Dogs age:  ");
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

                Dog dog = new Dog(name, owner, age, sex, breed, dangerousBreed, neutered);
                dog.setDaysAttending(daysAttending);
                isAdded = daycare.addPet(dog);
            }
            case 3 -> mainMenu();


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
//Didn't have time to finish this
    private void deletePet() {

    }


    private int reportsMenu() {
        return ScannerInput.readNextInt("""
                 Reports Menu
                ---------
                   1) List all pets
                   2) List all dogs
                   3) List all cats
                   0) Return to MainMenu
                 ==>> """);

    }

    private void runReportsMenu() {
        int option = reportsMenu();

        while (option != 0) {
            switch (option) {
                case 1 -> System.out.println(daycare.listPets());
                case 2 -> System.out.println(daycare.listDogs());
                case 3 -> System.out.println(daycare.listCats());
                default -> System.out.println("Invalid option selected: " + option);
            }

            ScannerInput.readNextLine("\nPress enter key to continue...");
            option = reportsMenu();
        }

    }

    private void listPets() {
        System.out.println(daycare.listPets());
    }

    private void listDogs() {
        System.out.println(daycare.listDogs());
    }

    private void listCats() {
        System.out.println(daycare.listCats());
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
            System.out.println("Loading to file: " + daycare.fileName());
            this.daycare.load();
        } catch (Exception e) {
            System.err.println("Error reading to file" + e);
        }
    }
}

