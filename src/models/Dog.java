package models;

import java.util.Objects;

public class Dog extends Pet {

    private String breed = "";
    private boolean dangerousBreed = false;
    private boolean neutered = false;
    private final float DANGEROUS_DAILY_RATE = 40f;
    private final float NONDANGEROUS_DAILY_RATE = 30f;

    public Dog(String name, String owner, boolean[] daysAttending, char sex, String breed, boolean dangerousBreed, boolean neutered) {
        super(name, owner, daysAttending, sex);
        this.breed = breed;
        this.dangerousBreed = dangerousBreed;
        this.neutered = neutered;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public boolean isDangerousBreed() {
        return dangerousBreed;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public float getDANGEROUS_DAILY_RATE() {
        return DANGEROUS_DAILY_RATE;
    }

    public float getNONDANGEROUS_DAILY_RATE() {
        return NONDANGEROUS_DAILY_RATE;
    }

    @Override
    public double calculateWeeklyFee() {
        float dailyRate;
        if (dangerousBreed) {
            dailyRate = DANGEROUS_DAILY_RATE;
        } else {
            dailyRate = NONDANGEROUS_DAILY_RATE;
        }
        return dailyRate * numOfDaysInKennel();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dog dog = (Dog) o;
        return dangerousBreed == dog.dangerousBreed && neutered == dog.neutered && Float.compare(DANGEROUS_DAILY_RATE, dog.DANGEROUS_DAILY_RATE) == 0 && Float.compare(NONDANGEROUS_DAILY_RATE, dog.NONDANGEROUS_DAILY_RATE) == 0 && Objects.equals(breed, dog.breed);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", dangerousBreed=" + dangerousBreed +
                ", neutered=" + neutered +
                ", DANGEROUS_DAILY_RATE=" + DANGEROUS_DAILY_RATE +
                ", NONDANGEROUS_DAILY_RATE=" + NONDANGEROUS_DAILY_RATE +
                "} " + super.toString();
    }
}
