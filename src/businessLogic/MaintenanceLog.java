/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businessLogic;

import java.util.*;

/**
 *
 * @author animi
 */
public class MaintenanceLog {

    //instance variables

    int currentMileage;
    Date serviceDate;
    List<String> deficiencies = new ArrayList<String>();
    Date motDate;
    HashMap<Date, String> mechanicTasksAndDates = new HashMap<Date, String>();

    //constructor

    MaintenanceLog(int aCurrentMileage, Date aServiceDate, List<String> aDeficiencies,
            Date aMotDate, HashMap<Date, String> aMechanicTasksAndDates)
        {
            this.currentMileage = aCurrentMileage;
            this.serviceDate = aServiceDate;
            this.deficiencies = aDeficiencies;
            this.motDate = aMotDate;
            this.mechanicTasksAndDates = aMechanicTasksAndDates;
        }

    public int getCurrentMileage() {
        return currentMileage;
    }

    public List<String> getDeficiencies() {
        List<String> result = new ArrayList<String>();
        for(String eachDeficiency : this.deficiencies)
        {
            result.add(eachDeficiency);
        }
        return result;
    }

    public HashMap<Date, String> getMechanicTasksAndDates() {
        HashMap<Date, String> result = new HashMap<Date, String>();
        for(Date eachDate : this.mechanicTasksAndDates.keySet())
        {
            String eachTask = this.mechanicTasksAndDates.get(eachDate);
            result.put(eachDate, eachTask);
        }
        return result;
    }

    public Date getMotDate() {
        return motDate;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setCurrentMileage(int aCurrentMileage) {
        this.currentMileage = aCurrentMileage;
    }

    public void setDeficiencies(List<String> aDeficiencies) {
        this.deficiencies = aDeficiencies;
    }

    public void setMechanicTasksAndDates(HashMap<Date, String> aMechanicTasksAndDates) {
        this.mechanicTasksAndDates = aMechanicTasksAndDates;
    }

    public void setMotDate(Date aMotDate) {
        this.motDate = aMotDate;
    }

    public void setServiceDate(Date aServiceDate) {
        this.serviceDate = aServiceDate;
    }




}
