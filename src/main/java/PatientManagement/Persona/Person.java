package PatientManagement.Persona;

import PatientManagement.Clinic.Location;
import PatientManagement.Patient.Patient;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kal bugrara
 */
public class Person {

    String id;
    Person mother;
    Person father;
    ArrayList<Person> friends;
    Patient patient;
    int age;
    Location location;

    public Person(String id, int a) {
        this.id = id;
        friends = new ArrayList<Person>();
        age = a;
    }

    public Person(String id, int a, Location location) {
        this.id = id;
        this.location = location;
        friends = new ArrayList<Person>();
        age = a;
    }

    public String getPersonId() {
        return id;
    }

    public void setFather(Person f) {
        father = f;
    }

    public void setMother(Person m) {
        mother = m;
    }

    public void addSibling(Person s) {
        if (friends.contains(s))
            return; // sibling already in the arraylist
        friends.add(s);
    }

    public boolean isMatch(String id) {
        if (getPersonId().equals(id))
            return true;
        return false;
    }

    public int getAge() {
        return age;
    }


    public String getLocation() {
        return location.getLocation();
    }
}
