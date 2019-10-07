package businessLogic;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class CoachCompany implements Serializable {

    //singleton
    static CoachCompany coachCompany = null;

    //instance variables
    private static Fleet fleet;
    //Collection<Coach> coaches;
    private Collection<SchoolContract> schoolContracts;
    private Collection<IndividualBooking> individualBookings;
    private Collection<Driver> drivers;

    //constructor
    public CoachCompany()
    {
        fleet = new Fleet(this);
        schoolContracts = new HashSet<SchoolContract>();
        individualBookings = new HashSet<IndividualBooking>();
        drivers = new HashSet<Driver>();
        //coaches = new HashSet<Coach>();
        //readCoachCompanyDetails("coachCompany.csv");
    }

    public static CoachCompany getCoachCompany()
    {
//        CoachCompany coachCompany = null;
//        FileInputStream fis = null;
//
//        try
//        {
//           fis = new FileInputStream("CoachCompany.data");
//           ObjectInputStream ois = new ObjectInputStream(fis);
//           coachCompany = (CoachCompany) ois.readObject();
//        }
//        catch (Exception ex)
//        {
//           // let user know that previous data file does not exist or is not compatible
//           System.out.println("Data file does not exist or is incompatible with this version of the software.");
//           System.out.println("CoachCompany will be initialised to default state");
//           coachCompany = new CoachCompany(); // initialise hospital to default state.
//           coachCompany.save(); //and save it
//        }
//        finally // as we are not exiting make sure the fis stream is closed.
//        {
//           try
//           {
//              if (fis != null)
//              {
//                 fis.close();
//              }
//           }
//           catch (Exception ex)
//           {
//              System.out.println("Error closing file.");
//           }
//        }
//        return coachCompany;


        if(coachCompany == null)
        {
            coachCompany = new CoachCompany();
            System.out.println("new coach company created");
        }
        else
        {
            System.out.println("coach company already exists");
        }
        return coachCompany;
    }

//    public void save()
//    {
//       try
//       {
//          FileOutputStream fos = new FileOutputStream("CoachCompany.data");
//          ObjectOutputStream oos = new ObjectOutputStream(fos);
//          oos.writeObject(this);
//       }
//       catch (Exception ex)
//       {
//          System.out.println("Problem storing state of coach company");
//          System.exit(1);
//       }
//    }

//    private void readCoachCompanyDetails(String setupFile)
//   {
//      Scanner fileScanner = null;
//      String lineDetails;
//      String fieldName;
//      int coachCap = 0;
//      String coachReg = null;
//      Scanner lineScanner;
//      //ConsultantDoctor cd1 = null;
//      //Collection<Doctor> doctors = new HashSet<Doctor>();
//      //List<Object> patientInfo = new ArrayList<Object>();
//      Coach aCoach;
//      try
//      {
//         fileScanner = new Scanner(new BufferedReader(new FileReader(setupFile)));
//
//         while (fileScanner.hasNextLine())
//         {
//            lineDetails = fileScanner.nextLine();
//            lineScanner = new Scanner(lineDetails);
//            lineScanner.useDelimiter(",");
//            try
//            {
//               fieldName = lineScanner.next();
//               if (fieldName.compareToIgnoreCase("Coach") == 0)
//               {
//                   if (coachReg != null)
//                  {
//                     aCoach = new Coach(coachCap, coachReg);
//                     coaches.add(aCoach);
//                     fleet.addCoach(coachReg, coachCap);
//                  }
//                  coachCap = Integer.parseInt(lineScanner.next());
//                  coachReg = lineScanner.next();
//               }
//               else if (fieldName.compareToIgnoreCase("Team") == 0)
//               {
//                  if (teamName != null)
//                  {
//                     aTeam = new Team(teamName, doctors, cd1);
//                     teams.add(aTeam);
//                     addPatients(patientInfo, aTeam);
//                  }
//                  teamName = lineScanner.next();
//                  doctors = new HashSet<Doctor>();
//                  patientInfo = new ArrayList<Object>();
//               }
//               else if (fieldName.compareToIgnoreCase("Consultant") == 0)
//               {
//                  cd1 = new ConsultantDoctor(new Name(lineScanner.next(), lineScanner.next(), lineScanner.next()));
//                  doctors.add(cd1);
//               }
//               else if (fieldName.compareToIgnoreCase("Junior") == 0)
//               {
//                  doctors.add(new JuniorDoctor(new Name(lineScanner.next(), lineScanner.next(), lineScanner.next()), Grade.valueOf(lineScanner.next())));
//               }
//               else if (fieldName.compareToIgnoreCase("Patient") == 0)
//               {
//                  patientInfo.add(new Name(lineScanner.next(), lineScanner.next(), lineScanner.next()));
//                  patientInfo.add(lineScanner.next());
//                  patientInfo.add(new M256Date(lineScanner.next()));
//               }
//            }
//            catch (Exception anException)
//            {
//               System.out.println(anException + ": Data corrupted");
//            }
//         }
//         if (teamName != null)
//         {
//            aTeam = new Team(teamName, doctors, cd1);
//            teams.add(aTeam);
//            addPatients(patientInfo, aTeam);
//         }
//      }
//      catch (Exception anException)
//      {
//         System.out.println("Error: " + anException);
//      }
//      finally
//      {
//         fileScanner.close();
//      }
//
//   }

    public HashSet<IndividualBooking> getIndividualBookings()
    {
        HashSet<IndividualBooking> result = new HashSet<IndividualBooking>();
        for(IndividualBooking eachBooking : individualBookings)
        {
            result.add(eachBooking);
        }
        return result;
    }

    public HashSet<SchoolContract> getSchoolContracts()
    {
        HashSet<SchoolContract> result = new HashSet<SchoolContract>();
        for(SchoolContract eachContract : schoolContracts)
        {
            result.add(eachContract);
        }
        return result;
    }

    public String addCoach(String aRegistration, int aCapacity)
    {
        String result = fleet.addCoach(aRegistration, aCapacity);
        return result;
    }

    public String removeCoach(Coach aCoach)
    {
        String result = fleet.removeCoach(aCoach);
        return result;
    }

    public HashSet<Coach> getCoaches()
    {
        return fleet.getCoaches();
    }

    public IndividualBooking makeBooking(Date aDate, int aStartTime, int aDuration, int aCapacity, boolean aAdditionalDriver,
                              String aPickupAddress, String aDestination)
    {
        Coach aCoach = fleet.findAvailableForBooking(aDate, aStartTime, aDuration, aCapacity);

        double aCost = generateCost(aDuration, aCapacity, aAdditionalDriver);

        IndividualBooking aBooking = new IndividualBooking(aDate, aStartTime, aDuration, aAdditionalDriver,
                aCapacity, aPickupAddress, aDestination, aCoach, aCost);

        individualBookings.add(aBooking);

        System.out.println("Cost for this booking: £" + aCost);
        return aBooking;
    }

    private double generateCost(int aDuration, int aCapacity, boolean aAdditionalDriver)
    {
        //TODO - find out how to calculate the cost of a coach hire
        //placeholder method body

        double aCost = (aDuration * 50) + (aCapacity * 20);
        if(aAdditionalDriver)
        {
            aCost = aCost + (aDuration * 50);
        }
        return aCost;
    }

    public String confirmBooking(IndividualBooking aBooking, String aName, String aTelNumber, String aEmail)
    {
        String customerId = aBooking.confirmBooking(aName, aTelNumber, aEmail);
        return customerId;
    }

    public void declineBooking(IndividualBooking aBooking)
    {
        individualBookings.remove(aBooking);
        aBooking.delete();
        System.out.println("Booking declined");
    }

    public void cancelBooking(IndividualBooking aBooking)
    {
        individualBookings.remove(aBooking);
        aBooking.delete();
        System.out.println("Booking cancelled");
    }

    public void addPayment(IndividualBooking aBooking, int aAmount)
    {
        aBooking.addPayment(aAmount);
    }

    public void addSchoolPickUp(String aSchoolName, Date aStartDate, Date aEndDate, int aRequiredCapacity, HashSet<String> aPickupAddresses)
    {
        //find available coach
        Coach aCoach = fleet.findAvailableForSchool(aStartDate, aEndDate, aRequiredCapacity);

        //create new school contract
        PickUpDropOff aPickupDropOff = new PickUpDropOff(aStartDate, aEndDate, aRequiredCapacity, aSchoolName, aPickupAddresses, aCoach);
        schoolContracts.add(aPickupDropOff);
        System.out.println("School pick up and drop off contract successfully created.");

        //generate list of the existing bookings clashing with the school contract
        HashSet<IndividualBooking> clashingBookings = new HashSet<IndividualBooking>();
        HashSet<IndividualBooking> aCoachAllBookings = aCoach.getBookings();
        for(IndividualBooking eachBooking : aCoachAllBookings)
        {
            //check if any bookings clash with school contract
            Date eachBookingDate = eachBooking.getDate();
            int eachBookingStartTime = eachBooking.getStartTime();
            int eachBookingDuration = eachBooking.getDuration();
            if(aCoach.isOnSchoolContract(eachBookingDate, eachBookingStartTime, eachBookingDuration))
            {
                clashingBookings.add(eachBooking);
            }
        }
        //remove the clashing bookings and print list of cancelled bookings
        for(IndividualBooking eachBooking : clashingBookings)
        {
            System.out.println("The following booking has been cancelled: \n" + "\n" + eachBooking.toString() + "\n");
            this.cancelBooking(eachBooking);
        }
    }

    public void addSchoolBaths(String aSchoolName, Date aStartDate, Date aEndDate, int aRequiredCapacity,
                               HashSet<String> aPickupAddresses, String aDestination, WeeklySlot.dayOfWeek aDayOfWeek,
                               int aStartTime, int aDuration)
    {
        //find available coach
        Coach aCoach = fleet.findAvailableForSchool(aStartDate, aEndDate, aRequiredCapacity);

        //create new contract
        BathsAndPlayingFields aBathsAndPlayingFields = new BathsAndPlayingFields(aStartDate, aEndDate,
                aRequiredCapacity, aSchoolName, aPickupAddresses, aDayOfWeek, aStartTime, aDuration, aCoach);
        schoolContracts.add(aBathsAndPlayingFields);
        System.out.println("School baths and playing fields contract successfully created.");

        //generate list of the existing bookings clashing with the school contract
        HashSet<IndividualBooking> clashingBookings = new HashSet<IndividualBooking>();
        HashSet<IndividualBooking> aCoachAllBookings = aCoach.getBookings();
        for(IndividualBooking eachBooking : aCoachAllBookings)
        {
            //check if any bookings clash with school contract
            Date eachBookingDate = eachBooking.getDate();
            int eachBookingStartTime = eachBooking.getStartTime();
            int eachBookingDuration = eachBooking.getDuration();
            if(aCoach.isOnSchoolContract(eachBookingDate, eachBookingStartTime, eachBookingDuration))
            {
                clashingBookings.add(eachBooking);
            }
        }
        //remove the clashing bookings and print list of cancelled bookings
        for(IndividualBooking eachBooking : clashingBookings)
        {
            System.out.println("The following booking has been cancelled: \n" + "\n" + eachBooking.toString() + "\n");
            this.cancelBooking(eachBooking);
        }
    }

    public HashSet<IndividualBooking> getDateBookings(String aDateString)
    {
        HashSet<IndividualBooking> allBookings = this.getIndividualBookings();
        Date theDate = new Date(aDateString);
        HashSet<IndividualBooking> result = new HashSet<IndividualBooking>();
        for(IndividualBooking eachBooking : allBookings)
        {
            if(eachBooking.getDate().equals(theDate))
            {
                result.add(eachBooking);
            }
        }
        return result;
    }

    public HashSet<SchoolContract> getDateContracts(String aDateString)
    {
        HashSet<SchoolContract> allContracts = this.getSchoolContracts();
        Date theDate = new Date(aDateString);

        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        String dateDayOfWeek = simpleDateformat.format(theDate);
        //convert to capitals to match enumerated types in class businessLogic.WeeklySlot
        String upperDateDayOfWeek = dateDayOfWeek.toUpperCase();
        System.out.println(upperDateDayOfWeek);

        HashSet<SchoolContract> result = new HashSet<SchoolContract>();

        for(SchoolContract eachContract : allContracts)
        {
            if(eachContract.getStartDate().before(theDate) && eachContract.getEndDate().after(theDate))
                {
                    HashSet<WeeklySlot> weeklySlots = eachContract.getWeeklySlots();
                    for(WeeklySlot eachSlot : weeklySlots)
                    {
                        if(upperDateDayOfWeek.equals(eachSlot.getDay().name()))
                        {
                            result.add(eachContract);
                        }
                    }
                }
        }
        return result;
    }

    public String addMaintenanceLog(Coach aCoach, int aMileage, Date aServiceDate,
            List<String> aDeficiencies, Date aMotDate, HashMap<Date, String> aMechanicTasksAndDates)
    {
        String result = fleet.addMaintenanceLog(aCoach, aMileage, aServiceDate, aDeficiencies, aMotDate,
                aMechanicTasksAndDates);
        return result;
    }

    public MaintenanceLog getMaintenanceLog(Coach aCoach)
    {
        MaintenanceLog result = fleet.getMaintenanceLog(aCoach);
        if(result == null)
        {
            System.out.println("No maintenance log exists for this coach.");
        }
        return result;
    }

    public String updateMaintenanceLog(Coach aCoach, int aMileage, Date aServiceDate,
            List<String> aDeficiencies, Date aMotDate, HashMap<Date, String> aMechanicTasksAndDates)
    {
        String result = fleet.updateMaintenanceLog(aCoach, aMileage, aServiceDate,
                aDeficiencies, aMotDate, aMechanicTasksAndDates);
        return result;
    }

    public String getUpcomingServices(Date aDate)
    {
        String result = fleet.getUpcomingServices(aDate);
        return result;
    }

    public String addDriver(String aName)
    {
        Driver aDriver = new Driver(aName);
        drivers.add(aDriver);
        String result = "New driver, " + aName + " successfully added.";
        return result;
    }

    public String addJob(Driver aDriver, Date aDate, int aStartTime, int aDuration)
    {
        String result = aDriver.addJob(aDate, aStartTime, aDuration);
        return result;
    }

    public HashSet<Job> viewJobs(Driver aDriver)
    {
        HashSet<Job> result = aDriver.getJobs();
        return result;
    }

    public String checkAvailability(Driver aDriver, Date aDate, int aStartTime, int aDuration)
    {
        String result;
        HashSet<Job> theJobs = aDriver.getJobs();
        if(!aDriver.eligibleToday(aDate, aDuration))
        {
            result = "Driver will exceed 9 hours.";
        }
        else
        {
            result = "Driver is available.";
        }
        return result;
    }

    public HashSet<Driver> getDrivers()
    {
        HashSet<Driver> result = new HashSet<Driver>();
        for(Driver eachDriver : drivers)
        {
            result.add(eachDriver);
        }
        return result;
    }

}
