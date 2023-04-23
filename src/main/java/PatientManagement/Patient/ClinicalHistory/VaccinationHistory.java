/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kal bugrara
 */
public class VaccinationHistory {
    private Vaccine vaccine;
    private String date;
    // ArrayList<Vaccine> vaccines;

    // public void addvaccine(Vaccine v) {
    //     vaccines.add(v);
    // }
    public VaccinationHistory(Vaccine vaccine, String date) {
        this.vaccine = vaccine;
        this.date = date;
    }
    public Vaccine getVaccine() {
        return vaccine;
    }

    public String getDate() {
        return date;
    }
    
}
