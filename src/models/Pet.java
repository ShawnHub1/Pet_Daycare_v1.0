package models;

import utils.Utilities;

import java.util.Arrays;
import java.util.Objects;

//Pet class will be the super class. It will be extended to the other pets like cat and dog.
//This is why it needs to be abstract.

public abstract class Pet {
    private static int nextId = 1000;
    private int id = 1000;
    private int age = 0;
    private String name = "";
    private String owner = "";
    private boolean[] daysAttending = new boolean[]{false, false, false, false, false};
    private char sex = 'f';

    public Pet(String name, String owner, char sex, int age) {
        this.id = nextId++;
        setName(name);
        setOwner(owner);
        setSex(sex);
        setAge(age);
//adding field for days attending but leaving out of constructor
        this.daysAttending = new boolean[]{false, false, false, false, false};
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Pet.nextId = nextId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Utilities.truncateString(name, 30);
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = Utilities.truncateString(owner, 20);
    }

    public boolean[] getDaysAttending() {
        return Arrays.copyOf(daysAttending, daysAttending.length);
    }

    public void setDaysAttending(boolean[] daysAttending) {

        this.daysAttending = new boolean[]{false, false, false, false, false};
        if (daysAttending != null) {
            System.arraycopy(daysAttending, 0, this.daysAttending, 0,
                    Math.min(daysAttending.length, this.daysAttending.length));
        }
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        if (sex == 'm' || sex == 'f') {
            this.sex = sex;
        }
    }

    public int numOfDaysInKennel() {
        int count = 0;
        for (boolean attending : daysAttending) {
            if (attending) {
                count++;
            }
        }
        return count;
    }

    public abstract double calculateWeeklyFee();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id && age == pet.age && sex == pet.sex && Objects.equals(name, pet.name) && Objects.equals(owner, pet.owner) && Objects.deepEquals(daysAttending, pet.daysAttending);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", daysAttending=" + Arrays.toString(daysAttending) +
                ", sex=" + sex +
                '}';
    }
}
