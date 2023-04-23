package PatientManagement.Patient.Encounters;

import PatientManagement.Catalogs.Limits;
import PatientManagement.Patient.Patient;

public class VitalSignMetric {
    Patient patient;
    String name;
    Limits upperLower;
    int value;

    public VitalSignMetric(Patient p, String n, Limits l, int v) {
        patient = p;
        name = n;
        upperLower = l;
        value = v;
    }

    

    public Boolean isNormal() {
        return upperLower.isWithinLimits(value);
    }


    public String getNormalStatus() {
        if (upperLower.isWithinLimits(value)) {
            return "Yes, The patient's vital signs are normal.";
        } else if (value < upperLower.lower) {
            return name + " is too low (" + value + ")";
        } else {
            return name + " is too high (" + value + ")";
        }
    }



    public int getValue() {
        return value;
    }
    
}
