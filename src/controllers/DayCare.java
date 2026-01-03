package controllers;

//imported util for arraylist and model.pet

import models.Cat;
import models.Dog;
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

    private ArrayList<Pet> pets;
    private int maxNumberOfPets = 90;
    private String name = "";
    private String filename = "pets.xml";

    public DayCare(String name, int maxNumberOfPets, String filename) {
        this.name = name;
        this.maxNumberOfPets = maxNumberOfPets;
        this.pets = new ArrayList<>();
        this.filename = filename;

    }

    public ArrayList<Pet> getPets() {
        return pets;
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
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getId() == id) {
                return pets.remove(i);
            }
        }
        return null;
    }

    public Pet getPetByIndex(int index) {
        if (Utilities.isValidIndex(pets, index)) {
            return pets.get(index);
        }
        return null;
    }

    public Pet getPetById(int id) {
        for (Pet p : pets) {
            if (p.getId() == id) return p;
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


    //Reporting methods

    public String listPets() {
        String listOfPets = "";
        for (Pet pet : pets) {
            listOfPets += pet.toString() + "\n";
        }
        return listOfPets;
    }

    public String listDogs() {
        String listOfDogs = "";
        for (Pet pet : pets) {
            if (pet instanceof Dog) {
                listOfDogs += pet.toString() + "\n";
            }
        }
        return listOfDogs;
    }

    public String listCats() {
        String listOfCats = "";
        for (Pet pet : pets) {
            if (pet instanceof Cat) {
                listOfCats += pet.toString() + "\n";
            }
        }
        return listOfCats;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DayCare dayCare = (DayCare) o;
        return maxNumberOfPets == dayCare.maxNumberOfPets && Objects.equals(pets, dayCare.pets) && Objects.equals(name, dayCare.name);
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(filename));
        out.writeObject(pets);
        out.close();
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes I want to serialise
        Class<Pet>[] classes = new Class[]{Pet.class, Cat.class, Dog.class};
        //Set up xstream with default security
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //writing to serialisation
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader(filename));
        pets = (ArrayList<Pet>) is.readObject();
        is.close();
    }

    public String fileName() {
        return filename;
    }
}



