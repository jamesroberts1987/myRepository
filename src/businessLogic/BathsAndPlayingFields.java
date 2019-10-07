package businessLogic;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class BathsAndPlayingFields extends SchoolContract {

    //instance variables
    private String destination;
    private WeeklySlot weeklySlot;

    //constructor
    BathsAndPlayingFields(Date aStartDate, Date aEndDate, int aRequiredCapacity, String aSchoolName, Collection<String> aPickUpAddresses,
                          WeeklySlot.dayOfWeek aDay, int aStartTime, int aDuration, Coach aCoach)
    {
        super(aStartDate, aEndDate, aRequiredCapacity, aSchoolName, aPickUpAddresses, aCoach);
        weeklySlot = new WeeklySlot(aDay, aStartTime, aDuration);
    }


    public String getDestination() {
        return destination;
    }

    public HashSet<WeeklySlot> getWeeklySlots() {
        HashSet<WeeklySlot> result = new HashSet<WeeklySlot>();
        result.add(weeklySlot);
        return result;
    }

    public String toStringAllDetails()
    {
        String result = "Baths and playing fields contract\n\nStart date: " + this.getStartDate() +
                "\nEnd date: " + this.getEndDate() +
                "\nRequired capacity: " + getRequiredCapacity() +
                "\nSchool name: " + getSchoolName() +
                "\nCoach: " + getCoach() +
                "\nDay of week: " + weeklySlot.getDay() +
                "\nStart time: " + weeklySlot.getStartTime() +
                "\nDuration: " + weeklySlot.getDuration() +
                "\nDestination: " + getDestination() +
                "\nPickup addresses: ";
        HashSet<String> addressSet = this.getPickUpAddresses();
        for(String eachAddress : addressSet)
        {
            result.concat("\n     " + eachAddress);
        }
        return result;
    }
}
