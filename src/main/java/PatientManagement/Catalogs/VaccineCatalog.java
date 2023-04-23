/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Catalogs;

import java.util.ArrayList;

import PatientManagement.Patient.ClinicalHistory.Vaccine;

/**
 *
 * @author kal bugrara
 */
public class VaccineCatalog {
    ArrayList<Vaccine> vaccines;

    public VaccineCatalog() {
        vaccines = new ArrayList<Vaccine>();
    }

    public Vaccine newVaccine(String name) {
        Vaccine vaccine =new Vaccine(name);
        vaccines.add(vaccine);
        return vaccine;
    }
    
    public Vaccine getVaccineByName(String name) {
        for (Vaccine vaccine : vaccines) {
            if (vaccine.getName().equals(name)) {
                return vaccine;
            }
        }
        return null;
    }    

}

