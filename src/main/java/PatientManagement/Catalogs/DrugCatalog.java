/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Catalogs;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class DrugCatalog {
    ArrayList<Drug> drugs;

    public DrugCatalog() {
        drugs = new ArrayList<Drug>();
    }

    public Drug newDrug(String name) {
        Drug drug =new Drug(name);
        drugs.add(drug);
        return drug;
    }
    
    public Drug getDrugByName(String name) {
        for (Drug drug : drugs) {
            if (drug.getName().equals(name)) {
                return drug;
            }
        }
        return null;
    }    

}
