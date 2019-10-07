package businessLogic;

public class WeeklySlot {

    public enum dayOfWeek
    {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY;
    }

    //instance variables
    private dayOfWeek day;
    private int startTime;
    private int duration;

    //constructor
    WeeklySlot(dayOfWeek aDay, int aStartTime, int aDuration)
    {
        day = aDay;
        startTime = aStartTime;
        duration = aDuration;
    }

    //getter methods
    public dayOfWeek getDay() {
        return day;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }
    
}
