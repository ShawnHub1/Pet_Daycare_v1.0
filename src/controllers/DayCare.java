package controllers;

//imported util for arraylist and model.pet

import models.Pet;
import utils.ISerializer;
import utils.Utilities;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Objects;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class DayCare implements ISerializer {

    private ArrayList<Pet> pets = new ArrayList<>();
    private int maxNumberOfPets = 90;
    private String name = "";

    public DayCare(String name, int maxNumberOfPets, ArrayList<Pet> pets) {
        this.name = name;
        this.maxNumberOfPets = maxNumberOfPets;
        this.pets = pets;
    }

    public boolean addPet(Pet pet) {
        return pets.add(pet);
    }

    public Pet deletePetByIndex(int index) {
        if (Utilities.isValidIndex(pets, index)) {
            return pets.remove(index);
        }
        return null;
    }

    public Pet deletePetById(int id) {
        if (Utilities.isValidIndex(pets, id)) {
            return pets.remove(id);
        }
        return null;
    }

    public Pet getPetByIndex(int index) {
        if (Utilities.isValidIndex(pets, index)) {
            return pets.get(index);
        }
        return null;
    }

    public Pet getPetById (int id) {
        if (Utilities.isValidIndex(pets, id)) {
            return pets.get(id);
        }
        return null;
    }

    public boolean isValidId(int id) {
        for (Pet p : pets) {
            if (p.getId() == id)
                return true;
        }
        return false;
    }

    public int numberOfPets() {
        return pets.size();
    }

    public int numberOfCats() {
        return pets.size();
    }

    public int numberOfDogs() {
        return pets.size();
    }

    public int numberOfDangerousDogs() {
        return pets.size();
    }

    public int numberOfIndoorCats() {
        return pets.size();
    }

    //Reporting methods

    public String listPets() {
        if (pets.size() == 0) {
            return "No Pets";
        } else {
            String listOfPets = "";

            for (int i = 0; i < pets.size(); i++) {
                listOfPets += i + ": " + pets.get(i) + "\n";
            }
            return listOfPets;
        }
    }

    public String listAllDogs() {
        if (pets.size() == 0) {
            return "No Dogs";
        } else {
            String listOfDogs = "";

            for (int i = 0; i < pets.size(); i++) {
                listOfDogs += i + ": " + pets.get(i) + "\n";
            }
            return listOfDogs;
        }
    }

    public String listAllCats() {
        if (pets.size() == 0) {
            return "No Cats";
        } else {
            String listOfCats = "";

            for (int i = 0; i < pets.size(); i++) {
                listOfCats += i + ": " + pets.get(i) + "\n";
            }
            return listOfCats;
        }
    }

    public String listAllDangerousDogs() {
        if (pets.size() == 0) {
            return "No Dangerous Dogs in Kennels";
        } else {
            String listOfDangerousDogs = "";

            for (int i = 0; i < pets.size(); i++) {
                listOfDangerousDogs += i + ": " + pets.get(i) + "\n";
            }
            return listOfDangerousDogs;
        }
    }

    public String listAllPetsByOwner() {
        if (pets.size() == 0) {
            return "No Pets for this owner";
        } else {
            String listOfPetsByOwner = "";

            for (int i = 0; i < pets.size(); i++) {
                listOfPetsByOwner += i + ": " + pets.get(i) + "\n";
            }
            return listOfPetsByOwner;
        }
    }

    public String listAllPetsThatStayMoreThanDays() {
        if (pets.size() == 0) {
            return "No Pets stay for more than + listOfPetsThatStayMoreThanDays + , Days";
        } else {
            String listOfPetsThatStayMoreThanDays = "";

            for (int i = 0; i < pets.size(); i++) {
                listOfPetsThatStayMoreThanDays += i + ": " + pets.get(i) + "\n";
            }
            return listOfPetsThatStayMoreThanDays;
        }
    }

    public double getWeeklyIncome() {
        double weeklyIncome = (double)0.0F;

        for(Pet thePet : this.pets) {
            weeklyIncome += thePet.calculateWeeklyFee();
        }

        return weeklyIncome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DayCare dayCare = (DayCare) o;
        return maxNumberOfPets == dayCare.maxNumberOfPets && Objects.equals(pets, dayCare.pets) && Objects.equals(name, dayCare.name);
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("pets.xml"));
        out.writeObject(pets);
        out.close();
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes I want to serialise
        Class<Pet>[] classes = new Class[]{Pet.class};
        //Set up xstream with default security
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //writing to serialisation
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("pets.xml"));
        pets = (ArrayList<Pet>) is.readObject();
        is.close();
    }
     public String fileName(){
        return "pets.xml";
    }
}



