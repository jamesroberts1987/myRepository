package businessLogic;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

public class Fleet {

    //instance variables
    private HashSet<Coach> coaches;
    private CoachCompany coachCompany;

    private Format formatter = new SimpleDateFormat("MM/dd/yyyy");

    //constructor
    Fleet(CoachCompany aCoachCompany)
    {
        coaches = new HashSet<Coach>();
        coachCompany = aCoachCompany;
    }

    public String addCoach(String aRegistration, int aCapacity)
    {
        Coach aCoach = new Coach(aCapacity, aRegistration);
        coaches.add(aCoach);
        String result = "Successfully added coach with capacity of " + aCapacity +
                " and registration " + aRegistration + ".";
        //coachCompany.coaches.add(aCoach);
        return result;
    }

    String removeCoach(Coach aCoach)
    {
        String result;

        SchoolContract aSchoolContract = aCoach.getSchoolContract();

        //find another suitable coach for the school contract
        if(aSchoolContract != null)
        {
            Date aStartDate = aSchoolContract.getStartDate();
            Date aEndDate = aSchoolContract.getEndDate();
            int aRequiredCapacity = aSchoolContract.getRequiredCapacity();

            Coach alternateCoach = this.findAvailableForSchool(aStartDate, aEndDate, aRequiredCapacity);
            aSchoolContract.setCoach(alternateCoach);
        }

        HashSet<IndividualBooking> thisBookings = aCoach.getBookings();
        if(thisBookings != null)
        {
            result = "Coach removed. The following bookings have been cancelled:\n";
            for(IndividualBooking eachBooking : thisBookings)
            {
                result.concat(eachBooking.toString() + "\n");
                coachCompany.cancelBooking(eachBooking);
            }
        }
        else
        {
            result = "Coach removed.";
        }

        coaches.remove(aCoach);
        //coachCompany.coaches.remove(aCoach);

        return result;
    }

    public HashSet<Coach> getCoaches()
    {
        HashSet<Coach> result = new HashSet<Coach>();
        for(Coach eachCoach : coaches)
        {
            result.add(eachCoach);
        }
        return result;
    }

    public Coach findAvailableForBooking(Date aDate, int aStartTime, int aDuration, int aCapacity)
    {
        //create list of available coaches
        ArrayList<Coach> candidateList = new ArrayList<Coach>();

        int aFinishTime = aStartTime + aDuration;

        for (Coach eachCoach : coaches) {
            boolean coachAvailable = true;
            if (eachCoach.getCapacity() >= aCapacity)
            {
                if(eachCoach.isOnSchoolContract(aDate, aStartTime, aDuration))
                {
                    coachAvailable = false;
                }
                HashSet<IndividualBooking> eachCoachBookings;
                eachCoachBookings = eachCoach.getBookings();
                if(eachCoachBookings != null) {
                    for (IndividualBooking eachBooking : eachCoachBookings) {
                        if (eachBooking.getDate() == aDate) {
                            if ((aStartTime >= eachBooking.getStartTime() && aStartTime <= eachBooking.getFinishTime()) ||
                                    aFinishTime <= eachBooking.getFinishTime() && aFinishTime >= eachBooking.getStartTime()) {
                                coachAvailable = false;

                            }
                        }
                    }
                }
            }
            else
            {
                coachAvailable = false;
            }
            if (coachAvailable) {
                candidateList.add(eachCoach);
            }
        }

        //if no coach available, throw exception
        if(candidateList.isEmpty())
        {
            throw new IllegalStateException("No coach available");
        }
        else {
            //find most suitable coach - least capacity in candidateList
            Coach result = findMostSuitable(candidateList);
            return result;
        }
    }

    //helper method to find most suitable coach (lowest capacity greater than or equal to required capacity)
    private Coach findMostSuitable(ArrayList<Coach> aList)
    {
        Coach result = aList.get(0);
        for(Coach eachCoach : aList)
        {
            if(eachCoach.getCapacity() < result.getCapacity())
            {
                result = eachCoach;
            }
        }
        return result;
    }

    public Coach findAvailableForSchool(Date aStartDate, Date aEndDate, int aRequiredCapacity)
    {
        ArrayList<Coach> candidateList = new ArrayList<Coach>();
        for(Coach eachCoach : coaches)
        {
            boolean coachAvailable = true;
            if(eachCoach.getSchoolContract() != null)
            {
                if((aStartDate.after(eachCoach.getSchoolContract().getStartDate()) && aStartDate.before(eachCoach.getSchoolContract().getEndDate())) ||
                        (aEndDate.before(eachCoach.getSchoolContract().getEndDate()) && aEndDate.after(eachCoach.getSchoolContract().getStartDate())))
                {
                    coachAvailable = false;
                }
            }
            if(coachAvailable && eachCoach.getCapacity() >= aRequiredCapacity)
            {
                candidateList.add(eachCoach);
            }
        }

        //if no coach available, throw exception
        if(candidateList.isEmpty())
        {
            throw new IllegalStateException("No coach available");
        }
        else {
            //find most suitable coach - least capacity in candidateList
            Coach result = findMostSuitable(candidateList);
            return result;
        }
    }

    public String addMaintenanceLog(Coach aCoach, int aMileage, Date aServiceDate,
            List<String> aDeficiencies, Date aMotDate, HashMap<Date, String> aMechanicTasksAndDates)
    {
        String result = aCoach.addMaintenanceLog(aMileage, aServiceDate, aDeficiencies, aMotDate,
                aMechanicTasksAndDates);
        return result;
    }

    public MaintenanceLog getMaintenanceLog(Coach aCoach)
    {
        MaintenanceLog result = aCoach.getMaintenanceLog();
        return result;
    }

    public String updateMaintenanceLog(Coach aCoach, int aMileage, Date aServiceDate,
            List<String> aDeficiencies, Date aMotDate, HashMap<Date, String> aMechanicTasksAndDates)
    {
        String result = aCoach.updateMaintenanceLog(aMileage, aServiceDate,
                aDeficiencies, aMotDate, aMechanicTasksAndDates);
        return result;
    }

    public String getUpcomingServices(Date aDate)
    {
        StringBuilder result = new StringBuilder("");
        for(int i = 0; i < 7; i++)
        {
            
            String dateString = formatter.format(aDate);
            //TEST
            System.out.println(dateString);
            result.append("\nDate: " + dateString + "\n\n");
            for(Coach eachCoach : coaches)
            {
                boolean hasMot = false;
                MaintenanceLog theMaintenanceLog = eachCoach.getMaintenanceLog();
                if(theMaintenanceLog != null)
                {
                    if(theMaintenanceLog.getMotDate().equals(aDate))
                    {
                        result.append("Coach with registration " + eachCoach.getRegistrationNumber() + " due for MOT.\n");
                        hasMot = true;
                    }
                    if(theMaintenanceLog.getServiceDate().equals(aDate))
                    {
                        if(hasMot)
                        {
                            result.append("\n" + eachCoach.getRegistrationNumber() + " also due for service.\n");
                        }
                        else
                        {
                            result.append("Coach with registration " + eachCoach.getRegistrationNumber() + " due for service.\n");
                        }
                    }
                }
            }
            Calendar c = Calendar.getInstance();
            c.setTime(aDate);
            c.add(Calendar.DATE, 1);
            aDate = c.getTime();
        }
        return result.toString();
    }


}
