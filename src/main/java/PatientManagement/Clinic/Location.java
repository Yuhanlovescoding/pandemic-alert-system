/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

/**
 *
 * @author kal bugrara
 */
public class Location {
    String name;
    private int infectedCount;
    boolean hasPsychologicalCounseling;
    boolean hasRehabilitationCenter;


    public Location(String name, boolean hasPsychologicalCounseling, boolean hasRehabilitationCenter) {
        this.name = name;
        this.hasPsychologicalCounseling = hasPsychologicalCounseling;
        this.hasRehabilitationCenter = hasRehabilitationCenter;
    }

    public String getLocation() {
        return name;
    }

    public boolean hasPsychologicalCounseling() {
        return hasPsychologicalCounseling;
    }

    public boolean hasRehabilitationCenter() {
        return hasRehabilitationCenter;
    }
    public boolean hasServices(String serviceType) {
        if (serviceType.equals("Psychological Counseling")) {
            return hasPsychologicalCounseling;
        } else if (serviceType.equals("Rehabilitation Center")) {
            return hasRehabilitationCenter;
        } else {
            return false;
        }
    }
}

