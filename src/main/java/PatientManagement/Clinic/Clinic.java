/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;



import PatientManagement.Catalogs.DrugCatalog;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Persona.PersonDirectory;

/**
 *
 * @author kal bugrara
 */
public class Clinic {
    PatientDirectory patientdirectory;
    PersonDirectory persondirectory;
    SiteCatalog sitelist;
    LocationList locationlist;
    DrugCatalog drugcatalog;
    EventSchedule eventschedule;
    VitalSignsCatalog vitalSignsCatalog;
    String name; // name of the clinic
       
    
    
    public Clinic(String n) {
        eventschedule = new EventSchedule();
        locationlist = new LocationList();
        persondirectory = new PersonDirectory();
        patientdirectory = new PatientDirectory(this);
        vitalSignsCatalog = new VitalSignsCatalog();
        drugcatalog = new DrugCatalog();
        name = n;

    }

    public DrugCatalog getDrugcatalog() {
        return drugcatalog;
    }


    public LocationList getLocationList() {
        return locationlist;
    }


    public VitalSignsCatalog getVitalSignsCatalog() {
        return vitalSignsCatalog;
    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public PatientDirectory getPatientDirectory() {
        return patientdirectory;
    }

    
    
   
    public boolean checkServices(String locname) {
        boolean hasPsychologicalCounseling = locationlist.hasServices(locname, "Psychological Counseling");
        boolean hasRehabilitationCenter = locationlist.hasServices(locname, "Rehabilitation Center");
        if (hasPsychologicalCounseling && hasRehabilitationCenter) {
            System.out.println(locname + " supports psychological counseling and rehabilitation center services");
            return true;
        } else if (hasPsychologicalCounseling) {
            System.out.println(locname + " supports psychological counseling services, but does not support rehabilitation center services");
            return false;
        } else if (hasRehabilitationCenter){
            System.out.println(locname + " supports rehabilitation center services, but does not support psychological counseling services");
            return false;
        } else {
            System.out.println(locname + " does not support psychological counseling or rehabilitation center services");
            return false;
        }
    }

}
