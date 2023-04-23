/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient;

import PatientManagement.Catalogs.Drug;
import PatientManagement.Catalogs.Limits;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Clinic.PatientDirectory;
import PatientManagement.Patient.ClinicalHistory.AlergyHistory;
import PatientManagement.Patient.ClinicalHistory.VaccinationHistory;
import PatientManagement.Patient.ClinicalHistory.Vaccine;
import PatientManagement.Patient.Encounters.Diagnosis;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.EncounterHistory;
import PatientManagement.Persona.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

/**
 *
 * @author kal bugrara
 */
public class Patient {
    Clinic clinic;
    EncounterHistory encounterhistory;
    Person person;
    AlergyHistory alergyhistory;
    Drug drug;
    private ArrayList<VaccinationHistory> vaccinationHistory;

    private static Map<String, Integer> locationInfectedCount = new HashMap<>();


    public Patient(Person p, Clinic clinic) {
        encounterhistory = new EncounterHistory(this); // link this patient object back
        person = p;
        this.clinic = clinic;
        vaccinationHistory = new ArrayList<>();
    }

    public ArrayList<VaccinationHistory> getVaccinationHistory() {
        return vaccinationHistory;
    }

    public void addVaccinationHistory(Vaccine vaccine, String date) {
        VaccinationHistory record = new VaccinationHistory(vaccine, date);
        vaccinationHistory.add(record);
    }

    

    public boolean hasReceivedVaccine(String vaccineName) {
        for (VaccinationHistory record : vaccinationHistory) {
            if (record.getVaccine().getName().equals(vaccineName)) {
                return true;
            }
        }
        return false;
    }

    

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public EncounterHistory getEncounterHistory() {
        return encounterhistory;
    }
    // the below method will return the encounter where the infection occured
    // from the returned encounter you pull the event, the site, etc.

    public Encounter getConfirmedEncounter() {
        ArrayList<Encounter> patientencounterlist = encounterhistory.getEncounterList();

        for (Encounter currentencounter : patientencounterlist) {
            Diagnosis diag = currentencounter.getDiagnosis();
            if (diag.isConfirmed()) {
                return currentencounter;// send back the whole encounter so we extract event and site
            }
        }
        return null;
    }

    public boolean isConfirmedPositive() {

        ArrayList<Encounter> patientencounterlist = encounterhistory.getEncounterList();

        for (Encounter currentencounter : patientencounterlist) {
            Diagnosis diag = currentencounter.getDiagnosis();
            return diag.isConfirmed();

        }
        return false;
    }

    public Person getPerson() {
        return person;
    }

    public Encounter newEncounter(String chiefcomplaint, Event ev) {
        return encounterhistory.newEncounter(chiefcomplaint, ev);
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void updateLocationInfectedCount(String locationName) {
        Integer count = locationInfectedCount.get(locationName);
        if (count == null) {
            count = 0;
        }
        locationInfectedCount.put(locationName, count + 1);
    }

    
    public static Map<String, Integer> getLocationInfectedCount() {
        return locationInfectedCount;
    }

    public void setVaccine(String string) {
    }

    

    
}
