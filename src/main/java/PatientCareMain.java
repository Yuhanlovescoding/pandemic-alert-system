
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.github.javafaker.Faker;

import PatientManagement.Catalogs.AgeGroup;
import PatientManagement.Catalogs.Drug;
import PatientManagement.Catalogs.DrugCatalog;
import PatientManagement.Catalogs.VitalSignLimits;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Clinic.EventSchedule;
import PatientManagement.Clinic.Location;
import PatientManagement.Clinic.LocationList;
import PatientManagement.Clinic.PatientDirectory;
import PatientManagement.Clinic.Site;
import PatientManagement.Clinic.SiteCatalog;
import PatientManagement.Patient.Patient;
import PatientManagement.Patient.ClinicalHistory.VaccinationHistory;
import PatientManagement.Patient.ClinicalHistory.Vaccine;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.VitalSignMetric;
import PatientManagement.Persona.Person;
import PatientManagement.Persona.PersonDirectory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kal bugrara
 */

public class PatientCareMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Create a clinic
        Clinic myClinic = new Clinic("KP Hospitals");
        
        
        
        // Configuring vital signs catalog
        VitalSignsCatalog vsc = myClinic.getVitalSignsCatalog();
        AgeGroup adults_18_50 = vsc.newAgeGroup("Adults 18-50", 50, 18);
        VitalSignLimits heartRateLimits = vsc.newVitalSignLimits("HR");
        VitalSignLimits bloodPressureLimits = vsc.newVitalSignLimits("BP");
        VitalSignLimits bodyTemperatureLimits= vsc.newVitalSignLimits("BT");
        heartRateLimits.addLimits(adults_18_50, 100, 60);
        bloodPressureLimits.addLimits(adults_18_50, 120, 80);
        bodyTemperatureLimits.addLimits(adults_18_50, 99, 97);

        // Create locations and Local Services- BayArea, CA
        Location locationSJ = new Location("San Jose", true, true);
        Location locationMV = new Location("Mountain View", false, true);
        Location locationSV = new Location("Sunnyvale", false,false);
        Location locationMP = new Location("Milpitas",true,false);
        Location locationFM = new Location("Fremont",true,false);
        LocationList locationsInCA = myClinic.getLocationList();
        locationsInCA.newLocation(locationMP);
        locationsInCA.newLocation(locationSJ);
        locationsInCA.newLocation(locationSV);
        locationsInCA.newLocation(locationMV);
        locationsInCA.newLocation(locationFM);

        System.out.println("\n---------------Local Services Available to Serve Patients.----------------");
        myClinic.checkServices("San Jose"); 
        myClinic.checkServices("Mountain View"); 
        myClinic.checkServices("Sunnyvale"); 
        myClinic.checkServices("Milpitas");
        myClinic.checkServices("Fremont");
        
        // Adding homeless people
        PersonDirectory pd = myClinic.getPersonDirectory();
        Person person1 = pd.newPersonY("Person_1", locationMP);
        Person person2 = pd.newPersonY("Person_2", locationSJ);
        Person person3 = pd.newPersonY("Person_3", locationSV);
        Person person4 = pd.newPersonY("Person_4",  locationMV);
        Person person5 = pd.newPersonY("Person_5",  locationFM);
        Person person6 = pd.newPersonY("Person_6",  locationMP);
        Person person7 = pd.newPersonY("Person_7",  locationSJ);
        Person person8 = pd.newPersonY("Person_8",  locationSV);
        Person person9 = pd.newPersonY("Person_9", locationMV);
        Person person10 = pd.newPersonY("Person_10",  locationFM);
        Person person11 = pd.newPersonY("Person_11",  locationMP);
        Person person12 = pd.newPersonY("Person_12", locationSJ);
        
        System.out.println("\n---------------Basic Information about the Homeless----------------");
        pd.printPerson();

        // Creating patients
        PatientDirectory patientDirectory = myClinic.getPatientDirectory();
        Patient patient1 = patientDirectory.newPatient(person1);
        Patient patient2 = patientDirectory.newPatient(person2);
        Patient patient3 = patientDirectory.newPatient(person3);        
        Patient patient4 = patientDirectory.newPatient(person4); 
        Patient patient5 = patientDirectory.newPatient(person5); 
        Patient patient6 = patientDirectory.newPatient(person6); 
        Patient patient7 = patientDirectory.newPatient(person7); 
        Patient patient8 = patientDirectory.newPatient(person8); 
        Patient patient9 = patientDirectory.newPatient(person9); 
        Patient patient10 = patientDirectory.newPatient(person10); 
        Patient patient11 = patientDirectory.newPatient(person11); 
        Patient patient12 = patientDirectory.newPatient(person12); 

        //Craet DrugCatalog
        DrugCatalog drugCatalog = myClinic.getDrugcatalog();
        Drug ibuprofen = drugCatalog.getDrugByName("ibuprofen");
        Drug Hydrochlorothiazide = drugCatalog.getDrugByName("Hydrochlorothiazide");
        Drug Bunaftine = drugCatalog.getDrugByName("Bunaftine");
        Drug VitaminC = drugCatalog.getDrugByName("Vitamin C, and drink more water");
        
       
        //  SiteCatalog siteCatalog = clinic.getSiteCatalog();
        //  Site nuCurryCenter = siteCatalog.newSite(greaterBostonArea);
        //  Site nuHealthServices = siteCatalog.newSite(greaterBostonArea);

        // Create an event schedule
        EventSchedule eventSchedule = new EventSchedule();
        
        //Create events
        Event patriotsWeekendPatientScreening1 = eventSchedule.newEvent(locationMP, "0");
        Event patriotsWeekendPatientScreening2 = eventSchedule.newEvent(locationSJ, "1");
        Event patriotsWeekendPatientScreening3 = eventSchedule.newEvent(locationSV, "2");
        Event patriotsWeekendPatientScreening4 = eventSchedule.newEvent(locationMV, "3");
        Event patriotsWeekendPatientScreening5 = eventSchedule.newEvent(locationFM, "4");
        
        //Craet VaccineCatalog
        Vaccine covidVaccine = new Vaccine("COVID-19");    
        Vaccine influenzaVaccine = new Vaccine("Influenza");
        Vaccine bacilleCalmetteGuerinvaccine = new Vaccine("Bacille Calmette-Guerin");
        Vaccine varicella = new Vaccine("Varicella");
        Vaccine mumps = new Vaccine("Mumps");
        

        // providing healthcare services to the homeless and in the process monitoring the spread of contiguous disease (COVID-19) in the community.
        System.out.println("\n---------------The patient's Medical history and the Status of This Visit.----------------");
        //#1 
        System.out.println("==========Patient 1============");
        Encounter patient1VisitToDoctor = patient1.newEncounter("Seasonal Flu", patriotsWeekendPatientScreening1);
        patient1VisitToDoctor.addNewVitals("HR", 90);
        patient1VisitToDoctor.addNewVitals("BP", 150);
        patient1VisitToDoctor.addNewVitals("BT",102);
        patient1.addVaccinationHistory(covidVaccine,"2022-03-01");
        patient1.addVaccinationHistory(bacilleCalmetteGuerinvaccine,"2021-10-15");
        
        System.out.println("Does the patient feel well?\n" +
        patient1VisitToDoctor.areVitalsNormal());
        System.out.println(patient1VisitToDoctor.performCovidTestResult());
        System.out.println(patient1VisitToDoctor.getPrescribeMedication());
        for (VaccinationHistory record : patient1.getVaccinationHistory()) {
            System.out.println("VaccineHistory: " + record.getVaccine().getName() + ", Date: " + record.getDate());
        }


        //#2
        System.out.println("==========Patient 2============");
        Encounter patient2VisitToDoctor = patient2.newEncounter("Headache", patriotsWeekendPatientScreening2);
        patient2VisitToDoctor.addNewVitals("HR", 94);
        patient2VisitToDoctor.addNewVitals("BP", 100);
        patient2VisitToDoctor.addNewVitals("BT", 101);
        patient2.addVaccinationHistory(covidVaccine,"2022-012-01");
        patient2.addVaccinationHistory(influenzaVaccine,"2017-10-10");

        System.out.println("Does the patient feel well?\n" +
        patient2VisitToDoctor.areVitalsNormal());
        System.out.println(patient2VisitToDoctor.performCovidTestResult());
        System.out.println(patient2VisitToDoctor.getPrescribeMedication());
        for (VaccinationHistory record : patient2.getVaccinationHistory()) {
            System.out.println("VaccineHistory: " + record.getVaccine().getName() + ", Date: " + record.getDate());
        }


        //#3
        System.out.println("==========Patient 3============");
        Encounter patient3VisitToDoctor = patient3.newEncounter("Cough", patriotsWeekendPatientScreening3);
        patient3VisitToDoctor.addNewVitals("HR", 98);
        patient3VisitToDoctor.addNewVitals("BP", 114);
        patient3VisitToDoctor.addNewVitals("BT", 102);
        patient3.addVaccinationHistory(covidVaccine,"2023-03-01");
        patient3.addVaccinationHistory(varicella,"2020-09-05");

        System.out.println("Does the patient feel well?\n" +
        patient3VisitToDoctor.areVitalsNormal());
        System.out.println(patient3VisitToDoctor.performCovidTestResult());
        System.out.println(patient3VisitToDoctor.getPrescribeMedication());
        for (VaccinationHistory record : patient3.getVaccinationHistory()) {
            System.out.println("VaccineHistory: " + record.getVaccine().getName() + ", Date: " + record.getDate());
        }


        //#4
        System.out.println("==========Patient 4============");
        Encounter patient4VisitToDoctor = patient4.newEncounter("Sore throat", patriotsWeekendPatientScreening4);
        patient4VisitToDoctor.addNewVitals("HR", 110);
        patient4VisitToDoctor.addNewVitals("BP", 100);
        patient4VisitToDoctor.addNewVitals("BT", 101);
        patient4.addVaccinationHistory(covidVaccine,"2022-09-01");

        System.out.println("Does the patient feel well?\n" +
        patient4VisitToDoctor.areVitalsNormal());
        System.out.println(patient4VisitToDoctor.performCovidTestResult());
        System.out.println(patient4VisitToDoctor.getPrescribeMedication());
        for (VaccinationHistory record : patient4.getVaccinationHistory()) {
            System.out.println("VaccineHistory: " + record.getVaccine().getName() + ", Date: " + record.getDate());
        }


        //#5
        System.out.println("==========Patient 5============");
        Encounter patient5VisitToDoctor = patient5.newEncounter("Fever", patriotsWeekendPatientScreening5);
        patient5VisitToDoctor.addNewVitals("HR", 87);
        patient5VisitToDoctor.addNewVitals("BP", 98);
        patient5VisitToDoctor.addNewVitals("BT", 103);
        patient5.addVaccinationHistory(covidVaccine,"2022-03-01");

        System.out.println("Does the patient feel well?\n" +
        patient5VisitToDoctor.areVitalsNormal());
        System.out.println(patient5VisitToDoctor.performCovidTestResult());
        System.out.println(patient5VisitToDoctor.getPrescribeMedication());
        for (VaccinationHistory record : patient5.getVaccinationHistory()) {
            System.out.println("VaccineHistory: " + record.getVaccine().getName() + ", Date: " + record.getDate());
        }


        //#6
        System.out.println("==========Patient 6============");
        Encounter patient6VisitToDoctor = patient6.newEncounter("Asthma", patriotsWeekendPatientScreening1);
        patient6VisitToDoctor.addNewVitals("HR", 103);
        patient6VisitToDoctor.addNewVitals("BP", 130);
        patient6VisitToDoctor.addNewVitals("BT", 97);
        patient6.addVaccinationHistory(covidVaccine,"2023-01-01");
        patient6.addVaccinationHistory(mumps,"2016-03-01");


        System.out.println("Does the patient feel well?\n" +
        patient6VisitToDoctor.areVitalsNormal());
        System.out.println(patient6VisitToDoctor.performCovidTestResult());
        System.out.println(patient6VisitToDoctor.getPrescribeMedication());
        for (VaccinationHistory record : patient6.getVaccinationHistory()) {
            System.out.println("VaccineHistory: " + record.getVaccine().getName() + ", Date: " + record.getDate());
        }


        //#7
        System.out.println("==========Patient 7============");
        Encounter patient7VisitToDoctor = patient7.newEncounter("Cold", patriotsWeekendPatientScreening2);
        patient7VisitToDoctor.addNewVitals("HR", 80);
        patient7VisitToDoctor.addNewVitals("BP", 110);
        patient7VisitToDoctor.addNewVitals("BT", 97);
        patient7.addVaccinationHistory(covidVaccine,"2021-04-01");
        patient7.addVaccinationHistory(influenzaVaccine,"2021-05-01");


        System.out.println("Does the patient feel well?\n" +
        patient7VisitToDoctor.areVitalsNormal());
        System.out.println(patient7VisitToDoctor.performCovidTestResult());
        System.out.println(patient7VisitToDoctor.getPrescribeMedication());
        for (VaccinationHistory record : patient7.getVaccinationHistory()) {
            System.out.println("VaccineHistory: " + record.getVaccine().getName() + ", Date: " + record.getDate());
        }

        //#8
        System.out.println("==========Patient 8============");
        Encounter patient8VisitToDoctor = patient8.newEncounter("Upper respiratory tract infection", patriotsWeekendPatientScreening3);
        patient8VisitToDoctor.addNewVitals("HR", 90);
        patient8VisitToDoctor.addNewVitals("BP", 107);
        patient8VisitToDoctor.addNewVitals("BT", 103);
        patient8.addVaccinationHistory(covidVaccine,"2022-03-01");

        System.out.println("Does the patient feel well?\n" +
        patient8VisitToDoctor.areVitalsNormal());
        System.out.println(patient8VisitToDoctor.performCovidTestResult());
        System.out.println(patient8VisitToDoctor.getPrescribeMedication());
        for (VaccinationHistory record : patient8.getVaccinationHistory()) {
            System.out.println("VaccineHistory: " + record.getVaccine().getName() + ", Date: " + record.getDate());
        }

        //#9
        System.out.println("==========Patient 9============");
        Encounter patient9VisitToDoctor = patient9.newEncounter("Neuralgia", patriotsWeekendPatientScreening4);
        patient9VisitToDoctor.addNewVitals("HR", 94);
        patient9VisitToDoctor.addNewVitals("BP", 100);
        patient9VisitToDoctor.addNewVitals("BT", 102);
        patient9.addVaccinationHistory(covidVaccine,"2022-11-01");
        patient9.addVaccinationHistory(bacilleCalmetteGuerinvaccine,"2020-03-01");

        System.out.println("Does the patient feel well?\n" +
        patient9VisitToDoctor.areVitalsNormal());
        System.out.println(patient9VisitToDoctor.performCovidTestResult());
        System.out.println(patient9VisitToDoctor.getPrescribeMedication());
        for (VaccinationHistory record : patient9.getVaccinationHistory()) {
            System.out.println("VaccineHistory: " + record.getVaccine().getName() + ", Date: " + record.getDate());
        }


        //#10
        System.out.println("==========Patient 10=============");
        Encounter patient10VisitToDoctor = patient10.newEncounter("Unconsciousness", patriotsWeekendPatientScreening5);
        patient10VisitToDoctor.addNewVitals("HR", 94);
        patient10VisitToDoctor.addNewVitals("BP", 140);
        patient10VisitToDoctor.addNewVitals("BT", 98);
        patient10.addVaccinationHistory(covidVaccine,"2022-08-12");

        System.out.println("Does the patient feel well?\n" +
        patient10VisitToDoctor.areVitalsNormal());
        System.out.println(patient10VisitToDoctor.performCovidTestResult());
        System.out.println(patient10VisitToDoctor.getPrescribeMedication());
        for (VaccinationHistory record : patient10.getVaccinationHistory()) {
            System.out.println("VaccineHistory: " + record.getVaccine().getName() + ", Date: " + record.getDate());
        }


        //#11
        System.out.println("==========Patient 11============");
        Encounter patient11VisitToDoctor = patient11.newEncounter("Nausea", patriotsWeekendPatientScreening1);
        patient11VisitToDoctor.addNewVitals("HR", 110);
        patient11VisitToDoctor.addNewVitals("BP", 130);
        patient11VisitToDoctor.addNewVitals("BT", 102);
        patient11.addVaccinationHistory(covidVaccine,"2023-02-27");
        patient11.addVaccinationHistory(influenzaVaccine,"2021-05-01");
        

        System.out.println("Does the patient feel well?\n" +
        patient11VisitToDoctor.areVitalsNormal());
        System.out.println(patient11VisitToDoctor.performCovidTestResult());
        System.out.println(patient11VisitToDoctor.getPrescribeMedication());
        for (VaccinationHistory record : patient11.getVaccinationHistory()) {
            System.out.println("VaccineHistory: " + record.getVaccine().getName() + ", Date: " + record.getDate());
        }
        


        //#12
        System.out.println("==========Patient 12============");
        Encounter patient12VisitToDoctor = patient12.newEncounter("Tonsilitis", patriotsWeekendPatientScreening2);
        patient12VisitToDoctor.addNewVitals("HR", 120);
        patient12VisitToDoctor.addNewVitals("BP", 100);
        patient12VisitToDoctor.addNewVitals("BT", 102);
        patient12.addVaccinationHistory(covidVaccine,"2020-12-11");
        patient12.addVaccinationHistory(bacilleCalmetteGuerinvaccine,"2020-03-01");

        System.out.println("Does the patient feel well?\n" +
        patient12VisitToDoctor.areVitalsNormal());
        System.out.println(patient12VisitToDoctor.performCovidTestResult());
        System.out.println(patient12VisitToDoctor.getPrescribeMedication());
        for (VaccinationHistory record : patient12.getVaccinationHistory()) {
            System.out.println("VaccineHistory: " + record.getVaccine().getName() + ", Date: " + record.getDate());
        }
            
        
        
        Map<String, Integer> locationInfectedCount = Patient.getLocationInfectedCount();

        
        List<Map.Entry<String, Integer>> locationInfectedCountList = new ArrayList<>(locationInfectedCount.entrySet());
        
        
        Collections.sort(locationInfectedCountList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        
        System.out.println("\n---------------number of infection incidents by location----------------");
        System.out.println("Location Infected Count (sorted):");
        
        
        for (Map.Entry<String, Integer> entry : locationInfectedCountList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
