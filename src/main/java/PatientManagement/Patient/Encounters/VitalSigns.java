/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Encounters;

import java.util.ArrayList;

import PatientManagement.Catalogs.Limits;
import PatientManagement.Patient.Patient;

/**
 *
 * @author kal bugrara
 */
public class VitalSigns {
    Encounter encounter;
    ArrayList<VitalSignMetric> vitalSigns;

    public VitalSigns(Encounter e) {
        encounter = e;
        vitalSigns = new ArrayList<VitalSignMetric>();
    }

    public VitalSignMetric addNewVitals(String name, int value) {
        Patient patient = encounter.getEncounterHistory().getPatient();
        int age = patient.getPerson().getAge();
        Limits limits = encounter.getVitalSignLimits(age, name);
        if (limits == null)
            return null;
        VitalSignMetric newVitals = new VitalSignMetric(patient, name, limits, value);
        vitalSigns.add(newVitals);
        return newVitals;
    }

    // public Boolean areNormal() {
    //     boolean normal = true;
    //     for (VitalSignMetric vsm : vitalSigns) {
    //         if (!vsm.isNormal())
    //             normal = false;
    //     }

    //     return normal;
    // }

    public VitalSignMetric getVitalSignByName(String name) {
        for (VitalSignMetric vsm : vitalSigns) {
            if (vsm.name.equalsIgnoreCase(name)) {
                return vsm;
            }
        }
        return null;
    }
    
    
    public String areNormal() {
        StringBuilder sb = new StringBuilder();
        for (VitalSignMetric vsm : vitalSigns) {
            if (!vsm.isNormal())
                sb.append(vsm.getNormalStatus()).append(", ");
        }
    
        if (sb.length() == 0)
            return "Yes, The patient's vital signs are normal.";
        else
            return sb.toString().substring(0, sb.length() - 2);
    }

    public class COVIDTest {
        private boolean result;
    
        public COVIDTest(boolean result) {
            this.result = result;
        }
    
        public boolean getResult() {
            return result;
        }
    }

    public String performCovidTest() {
        VitalSignMetric temp = getVitalSignByName("BT");
        if (temp == null || temp.isNormal()) {
            return "Normal body temperature, No COVID-19 risk detected.";
        } else {
            COVIDTest test = new COVIDTest(Math.random() < 0.5);
            if (test.getResult()) {
                Patient patient = encounter.getEncounterHistory().getPatient();
                String location = patient.getPerson().getLocation();
                patient.updateLocationInfectedCount(location);
                return "Because the body temperature is too high, there is a possibility of COVID-19 infection. A PCR test is required.\nPCR test result is positive! Patient located:" + location + " is at risk of COVID-19 infection! \nPlease check as soon as possible who has been to the location within 7 days, and who has been in close contact with the patient within 7 days!";
            } else {
                return "Because the body temperature is too high, there is a possibility of COVID-19 infection. A PCR test is required.\nPCR test result is negative. Patient is not infected with COVID-19.";
            }
        }
    }

    public String prescribeMedication() {
        VitalSignMetric bp = getVitalSignByName("BP");
        VitalSignMetric hr = getVitalSignByName("HR");
        VitalSignMetric temp = getVitalSignByName("BT");
        String prescription = "Prescription: ";
        if (bp != null && !bp.isNormal()) {
            if (bp.getValue() > 120) {
                prescription += "Hydrochlorothiazide, ";
            }
        }
        if (hr != null && !hr.isNormal()) {
            if (hr.getValue() > 100) {
                prescription += "Bunaftine, ";
            }
        }
        if (temp != null && !temp.isNormal()) {
            if (temp.getValue() > 100) {
                prescription += "ibuprofen, ";
            }
        }
        if (bp != null && hr != null && temp != null && bp.isNormal() && hr.isNormal() && temp.isNormal()) {
            prescription += "Vitamin C, Drink more water, ";
        }
        if (prescription.equals("Prescription: ")) {
            prescription = "No medication prescribed.";
        } else {
            prescription = prescription.substring(0, prescription.length() - 2);
        }
        return prescription;
    }        

}
