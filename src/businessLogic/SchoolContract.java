package businessLogic;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public abstract class SchoolContract {

    //instance variables
    private Date startDate;
    private Date endDate;
    private int requiredCapacity;
    private String schoolName;
    private Collection<String> pickUpAddresses;

    //associations
    private Coach coach;

    //constructor
    SchoolContract(Date aStartDate, Date aEndDate, int aRequiredCapacity, String aSchoolName, Collection<String> aPickUpAddresses,
                   Coach aCoach) {
        startDate = aStartDate;
        endDate = aEndDate;
        requiredCapacity = aRequiredCapacity;
        schoolName = aSchoolName;
        pickUpAddresses = new HashSet<String>(aPickUpAddresses);
        coach = aCoach;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getRequiredCapacity() {
        return requiredCapacity;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public Coach getCoach()
    {
        return coach;
    }

    @Override
    public String toString()
    {
        String result = this.getSchoolName() + ": " + this.getStartDate() + " to " + this.getEndDate();
        return result;
    }

    public HashSet<String> getPickUpAddresses() {
        HashSet<String> result = new HashSet<String>();
        for (String eachAddress : pickUpAddresses) {
            result.add(eachAddress);
        }
        return result;
    }

    public abstract HashSet<WeeklySlot> getWeeklySlots();

    public abstract String toStringAllDetails();

    public void setCoach(Coach aCoach) {
        this.coach = aCoach;
    }
}