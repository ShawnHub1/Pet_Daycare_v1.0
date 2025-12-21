package models;

public class Cat extends Pet {
public boolean indoorCat = true;
public String favouriteToy = "";

    public Cat(String name, String owner, boolean[] daysAttending, char sex, int nextId, boolean indoorCat, String favouriteToy) {
        super(name, owner, daysAttending, sex, nextId);
        this.indoorCat = indoorCat;
        this.favouriteToy = favouriteToy;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "indoorCat=" + indoorCat +
                ", favouriteToy='" + favouriteToy + '\'' +
                "} " + super.toString();
    }
}
