package main;

import controllers.DayCare;
import models.*;
import utils.ScannerInput;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    private Scanner input = new Scanner(System.in);
    DayCare daycare;

    public static void main(String[] args) {
        new Driver();
    }

    public Driver() {
        runMenu();
        mainMenu();
    }

    private int mainMenu() {
        System.out.print("""
                Pet Day Care Menu
                ---------
                   1) Pet CRUD Menu
                   2) Reports
                   3) Search Pets 
                   4) Sort Pets
                   5) Save Pets to .xml
                   6) Load Pets from .xml
                   0) Exit
                 ==>> """);
        int option = input.nextInt();
        return option;
    }

    private void runMenu() {
        int option = mainMenu();
        exitApp();
    }

    private void exitApp() {

        System.out.println("Exiting....");
        System.exit(0);
    }
}

