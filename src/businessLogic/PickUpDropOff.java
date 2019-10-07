package businessLogic;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class PickUpDropOff extends SchoolContract{

    //pickup/dropoff contracts are for set times each weekday
    private static final int MORNING_START_TIME = 7;
    private static final int MORNING_DURATION = 3;
    private static final int AFTERNOON_START_TIME = 14;
    private static final int AFTERNOON_DURATION = 3;

    WeeklySlot mondayMorning;
    WeeklySlot mondayAfternoon;
    WeeklySlot tuesdayMorning;
    WeeklySlot tuesdayAfternoon;
    WeeklySlot wednesdayMorning;
    WeeklySlot wednesdayAfternoon;
    WeeklySlot thursdayMorning;
    WeeklySlot thursdayAfternoon;
    WeeklySlot fridayMorning;
    WeeklySlot fridayAfternoon;


    //constructor
    PickUpDropOff(Date aStartDate, Date aEndDate, int aRequiredCapacity, String aSchoolName, Collection<String> aPickUpAddresses,
                  Coach aCoach)
    {
        super(aStartDate, aEndDate, aRequiredCapacity, aSchoolName, aPickUpAddresses, aCoach);
        mondayMorning = new WeeklySlot(WeeklySlot.dayOfWeek.MONDAY, MORNING_START_TIME, MORNING_DURATION);
        mondayAfternoon = new WeeklySlot(WeeklySlot.dayOfWeek.MONDAY, AFTERNOON_START_TIME, AFTERNOON_DURATION);
        tuesdayMorning = new WeeklySlot(WeeklySlot.dayOfWeek.TUESDAY, MORNING_START_TIME, MORNING_DURATION);
        tuesdayAfternoon = new WeeklySlot(WeeklySlot.dayOfWeek.TUESDAY, AFTERNOON_START_TIME, AFTERNOON_DURATION);
        wednesdayMorning = new WeeklySlot(WeeklySlot.dayOfWeek.WEDNESDAY, MORNING_START_TIME, MORNING_DURATION);
        wednesdayAfternoon = new WeeklySlot(WeeklySlot.dayOfWeek.WEDNESDAY, AFTERNOON_START_TIME, AFTERNOON_DURATION);
        thursdayMorning = new WeeklySlot(WeeklySlot.dayOfWeek.THURSDAY, MORNING_START_TIME, MORNING_DURATION);
        thursdayAfternoon = new WeeklySlot(WeeklySlot.dayOfWeek.THURSDAY, AFTERNOON_START_TIME, AFTERNOON_DURATION);
        fridayMorning = new WeeklySlot(WeeklySlot.dayOfWeek.FRIDAY, MORNING_START_TIME, MORNING_DURATION);
        fridayAfternoon = new WeeklySlot(WeeklySlot.dayOfWeek.FRIDAY, AFTERNOON_START_TIME, AFTERNOON_DURATION);
    }

    @Override
    public HashSet<WeeklySlot> getWeeklySlots()
    {
        HashSet<WeeklySlot> result = new HashSet<WeeklySlot>();
        result.add(mondayMorning);
        result.add(mondayAfternoon);
        result.add(tuesdayMorning);
        result.add(tuesdayAfternoon);
        result.add(wednesdayMorning);
        result.add(wednesdayAfternoon);
        result.add(thursdayMorning);
        result.add(thursdayAfternoon);
        result.add(fridayMorning);
        result.add(fridayAfternoon);

        return result;
    }

    public String toStringAllDetails()
    {
        String result = "Pickup and drop off contract\n\nStart date: " + this.getStartDate() +
                "\nEnd date: " + this.getEndDate() +
                "\nRequired capacity: " + getRequiredCapacity() +
                "\nSchool name: " + getSchoolName() +
                "\nCoach: " + getCoach() +
                "\nPickup addresses: ";
        HashSet<String> addressSet = this.getPickUpAddresses();
        for(String eachAddress : addressSet)
        {
            result.concat("\n     " + eachAddress);
        }
        return result;
    }


}
