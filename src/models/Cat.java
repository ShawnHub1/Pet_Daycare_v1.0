package models;

import java.util.Objects;

 public class Cat extends Pet {
     private boolean indoorCat = true;
     private String favouriteToy = "";

    public Cat(String name, String owner, int age, char sex, boolean indoorCat, String favouriteToy) {
        super(name, owner, sex, age );
        this.indoorCat = indoorCat;
        this.favouriteToy = favouriteToy;
    }

    public boolean isIndoorCat() {
        return indoorCat;
    }

    public void setIndoorCat(boolean indoorCat) {
        this.indoorCat = indoorCat;
    }

    public String getFavouriteToy() {
        return favouriteToy;
    }

    public void setFavouriteToy(String favouriteToy) {
        this.favouriteToy = favouriteToy;
    }

     @Override
     public double calculateWeeklyFee() {
         float dailyRate;
         if (indoorCat) {
             dailyRate = 25f;
         } else {
             dailyRate = 20f;
         }
         return dailyRate * numOfDaysInKennel();
     }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cat cat = (Cat) o;
        return indoorCat == cat.indoorCat && Objects.equals(favouriteToy, cat.favouriteToy);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "indoorCat=" + indoorCat +
                ", favouriteToy='" + favouriteToy + '\'' +
                "} " + super.toString();
    }
}
