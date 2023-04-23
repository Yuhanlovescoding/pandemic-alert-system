/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class LocationList {
    ArrayList<Location> locations;

    public LocationList() {
        locations = new ArrayList<Location>();
    }

    public Location getLocation(String locname) {
        // search for a location object by locname;
        Location l = null;
        return l;
    }

    public Location newLocation(String name, boolean hasPsychologicalCounseling, boolean hasRehabilitationCenter) {
        Location newLoc = new Location(name, hasPsychologicalCounseling, hasRehabilitationCenter);
        locations.add(newLoc);
        return newLoc;
    }
    
    public void newLocation(Location location) {
        locations.add(location);
    }
    
    public boolean hasServices(String locname, String serviceType) {
        for (Location location : locations) {
            if (location.getLocation().equals(locname)) {
                return location.hasServices(serviceType);
            }
        }
        return false;
    }
    }