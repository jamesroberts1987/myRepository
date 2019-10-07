package businessLogic;

import java.util.Date;

public class IndividualBooking {

    //instance variables
    private Date date;
    private int startTime;
    private int duration;
    private boolean additionalDriver;
    private int requiredCapacity;
    private double remainingBalance;
    private double cost;
    private String pickupAddress;
    private String destination;

    //associations
    private Coach coach;
    private Customer customer;



    //constructor
    IndividualBooking(Date aDate, int aStartTime, int aDuration, boolean aAdditionalDriver,
                      int aRequiredCapacity, String aPickupAddress, String aDestination,
                      Coach aCoach, double aCost)
    {
        date = aDate;
        startTime = aStartTime;
        duration = aDuration;
        additionalDriver = aAdditionalDriver;
        requiredCapacity = aRequiredCapacity;
        pickupAddress = aPickupAddress;
        destination = aDestination;
        cost = aCost;
        remainingBalance = aCost;
        coach = aCoach;

        aCoach.addBooking(this);
    }

    //getter methods for instance variables
    public Date getDate() {
        return date;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public boolean getAdditionalDriver() {
        return additionalDriver;
    }

    public int getRequiredCapacity() {
        return requiredCapacity;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }

    public double getCost() {
        return cost;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public String getDestination() {
        return destination;
    }

    //dervied attribute - finishTime
    public int getFinishTime()
    {
        int result = startTime + duration;
        return result;
    }

    public Coach getCoach()
    {
        return coach;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    @Override
    public String toString()
    {
        String result = this.customer.getCustomerId();
        return result;
    }

    public String toStringAllDetails() {
        String result = "Date: " + getDate() +
                "\nStart time: " + getStartTime() +
                "\nDuration: " + getDuration() +
                "\nAdditional driver: " + getAdditionalDriver() +
                "\nRequired capacity: " + getRequiredCapacity() +
                "\nPick up address: " + getPickupAddress() +
                "\nDestination: " + getDestination() +
                "\nCost: " + getCost() +
                "\nCoach: " + getCoach().toString();
        return result;
    }

//    public String getCoachToString()
//    {
//        return coach.toString();
//    }

    String confirmBooking(String aName, String aTelNumber, String aEmail)
    {
        customer = new Customer(aName, aTelNumber, aEmail);
        String result = customer.getCustomerId();
        return result;
    }

    void delete()
    {
        coach.removeBooking(this);
        coach = null;
        customer = null;
    }

    void addPayment(int aAmount)
    {
        if(getRemainingBalance() >= aAmount)
        {
            remainingBalance = getRemainingBalance() - aAmount;
            if(getRemainingBalance() == 0)
            {
                System.out.println("Full balance paid");
            }
            else
            {
                System.out.println("There is " + getRemainingBalance() + " left to pay.");
            }
        }
        else
        {
            System.out.println("The supplied amount is greater than the remaining balance of " + getRemainingBalance());
        }
    }
}
