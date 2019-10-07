package businessLogic;

import java.text.SimpleDateFormat;
import java.util.*;

public class Coach {

    //instance variables
    private int capacity;
    private String registrationNumber;

    //associations
    private ArrayList<IndividualBooking> bookings;
    private SchoolContract schoolContract;
    private MaintenanceLog maintenanceLog = null;

    //constructor
    public Coach(int aCapacity, String aRegistration)
    {
        capacity = aCapacity;
        registrationNumber = aRegistration;
        bookings = new ArrayList<IndividualBooking>();
    }

    public int getCapacity()
    {
        return capacity;
    }

    public String getRegistrationNumber()
    {
        return registrationNumber;
    }

    public SchoolContract getSchoolContract()
    {
        return schoolContract;
    }

    @Override
    public String toString()
    {
        String result = "capacity: " + capacity + ", registration: " + registrationNumber;
        return result;
    }

    public HashSet<IndividualBooking> getBookings()
    {
        HashSet<IndividualBooking> result = new HashSet<IndividualBooking>();
        for(IndividualBooking eachBooking : bookings)
        {
            result.add(eachBooking);
        }
        return result;
    }

    void addBooking(IndividualBooking aBooking)
    {
        bookings.add(aBooking);
    }

    void removeBooking(IndividualBooking aBooking)
    {
        bookings.remove(aBooking);
    }

    boolean isOnSchoolContract(Date aDate, int aStartTime, int aDuration)
    {
        boolean result = false;

        //check if aDate is a weekday, to check if coach is booked on a school contract
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        String dateDayOfWeek = simpleDateformat.format(aDate);
        //convert to capitals to match enumerated types in class businessLogic.WeeklySlot
        dateDayOfWeek = dateDayOfWeek.toUpperCase();

        if(dateDayOfWeek.equals(WeeklySlot.dayOfWeek.MONDAY.name()) ||
                dateDayOfWeek.equals(WeeklySlot.dayOfWeek.TUESDAY.name()) ||
                dateDayOfWeek.equals(WeeklySlot.dayOfWeek.WEDNESDAY.name()) ||
                dateDayOfWeek.equals(WeeklySlot.dayOfWeek.THURSDAY.name()) ||
                dateDayOfWeek.equals(WeeklySlot.dayOfWeek.FRIDAY.name())) {

            //get school contract for aCoach
            SchoolContract aSchoolContract = this.getSchoolContract();

            //check if aCoach is assigned to a school contract
            if (aSchoolContract != null) {
                //check if aDate is during the school contract
                if (!aDate.before(aSchoolContract.getStartDate()) && !aDate.after(aSchoolContract.getEndDate())) {
                    //check if the time clashes with the times of the school contract
                    int aEndTime = aStartTime + aDuration;
                    HashSet<WeeklySlot> weeklySlots = aSchoolContract.getWeeklySlots();
                    for (WeeklySlot eachSlot : weeklySlots) {
                        int eachSlotStart = eachSlot.getStartTime();
                        int eachSlotEnd = eachSlotStart + eachSlot.getDuration();
                        if ((aStartTime >= eachSlotStart && aStartTime <= eachSlotEnd) ||
                                (aEndTime >= eachSlotStart && aEndTime <= eachSlotEnd)) {
                            result = true;
                        }

                    }
                }
            }
        }
        return result;
    }

    public String addMaintenanceLog(int aMileage, Date aServiceDate,
            List<String> aDeficiencies, Date aMotDate, HashMap<Date, String> aMechanicTasksAndDates)
    {
        String result;
        if(maintenanceLog == null)
        {
            maintenanceLog = new MaintenanceLog(aMileage, aServiceDate, aDeficiencies, aMotDate,
                    aMechanicTasksAndDates);
            result = "New maintenance log created.";
        }
        else
        {
            result = "Failed: Maintenance log already exists.";
        }
        return result;
    }

    public MaintenanceLog getMaintenanceLog()
    { 
        return maintenanceLog;
    }

    public String updateMaintenanceLog(int aMileage, Date aServiceDate,
            List<String> aDeficiencies, Date aMotDate, HashMap<Date, String> aMechanicTasksAndDates)
    {
        String result;
        if(maintenanceLog != null)
        {
            maintenanceLog.setCurrentMileage(aMileage);
            maintenanceLog.setServiceDate(aServiceDate);
            maintenanceLog.setDeficiencies(aDeficiencies);
            maintenanceLog.setMotDate(aMotDate);
            maintenanceLog.setMechanicTasksAndDates(aMechanicTasksAndDates);

            result = "Maintenance log updated.";
        }
        else
        {
            maintenanceLog = new MaintenanceLog(aMileage, aServiceDate, aDeficiencies,
                    aMotDate, aMechanicTasksAndDates);
            result = "New maintenance log created.";
        }
        return result;
    }

}
