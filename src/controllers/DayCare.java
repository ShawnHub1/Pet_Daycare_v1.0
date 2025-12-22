package controllers;

//imported util for arraylist and model.pet
import models.Pet;
import utils.Utilities;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;




public class DayCare {

 //TODO Create a list to store the Pets
    private ArrayList<Pet> pets = new ArrayList<>();

 //TODO Create a int for max number of pets
    private int maxNumberOfPets = 90;

    //TODO Create a field to store the Kennel Name
    private String name = "";
    //TODO create constructor initialise the kennel name and to instantiate the pets list

    public DayCare(String name, int maxNumberOfPets, ArrayList<Pet> pets) {
        this.name = name;
        this.maxNumberOfPets = maxNumberOfPets;
        this.pets = pets;
    }

    //TODO - CRUD Methods

    public boolean addPet(Pet pet) {
        return pets.add(pet);
    }

    public Pet deletePetByIndex(int index) {
        if (Utilities.isValidIndex(pets, index)) {
            return pets.remove(index);
        }
        return null;
    }

    public Pet getPetByIndex(int index) {
        if (Utilities.isValidIndex(pets, index)) {
            return pets.get(index);
        }
        return null;
    }


    // TODO Reporting Methods
    public String listPets() {

        if (pets.size() == 0) {
            return "No Pets";
        }

        else {
            String listOfPets = "";

            for (int i = 0; i < pets.size(); i++) {
                listOfPets += i + ": " + pets.get(i) + "\n";
            }
            return listOfPets;
        }
    }

    // TODO number methods

    //TODO validation method below:
    //the following is isValidId can be updated
    //to suit your code - checks is the id already there in the list
    /*
    public boolean isValidId(int id) {
        for (Pet p : whateverYouCalledYourList) {
            if (p.getId().equals(id))
                return false;
        }
            return true;
        }
*/

    //TODO get Pets methods

    //TODO - delete methods



   // TODO Persistence methods
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(pets.xml));
        out.writeObject(pets);
        out.close();
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
       //list of classes I want to serialise
        Class<Pet>[] classes = new Class[] {Pet.class};
        //Set up xstream with default security
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

       //writing to serialisation
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader(pets.xml));
        pets = (ArrayList<Pet>) is.readObject();
        is.close();
    }

}



