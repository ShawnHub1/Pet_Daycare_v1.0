package models;

public class Dog extends Pet {

    public String breed = "";
    public boolean dangerousBreed = false;
    public boolean neutered = false;
    public float DANGEROUS_DAILY_RATE = 40;
    public float NONDANGEROUS_DAILY_RATE = 30;

    public Dog(String name, String owner, boolean[] daysAttending, char sex, int nextId, String breed, boolean dangerousBreed, boolean neutered, float DANGEROUS_DAILY_RATE, float NONDANGEROUS_DAILY_RATE) {
        super(name, owner, daysAttending, sex, nextId);
        this.breed = breed;
        this.dangerousBreed = dangerousBreed;
        this.neutered = neutered;
        this.DANGEROUS_DAILY_RATE = DANGEROUS_DAILY_RATE;
        this.NONDANGEROUS_DAILY_RATE = NONDANGEROUS_DAILY_RATE;
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
