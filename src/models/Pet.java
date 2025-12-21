package models;

import utils.Utilities;

//Pet class will be the super class. It will be extended to the other pets like cat and dog.
//This is why it needs to be abstract.

public abstract class Pet {
   //  Added from spec. commented out will come back to this when further on to understand it when using it not sure now
    private static int nextId = 1000;
    // Adding initial Instance fields with defaults
    private int id = 1000;
    private String name = "";
    private String owner = "";
    private boolean[] daysAttending = new boolean[]{false, false, false, false, false};
    private char sex = 'f';

    public Pet(String name, String owner, boolean[] daysAttending, char sex, int nextId) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.daysAttending = daysAttending;
        this.sex = sex;
    }
}

