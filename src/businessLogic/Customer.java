package businessLogic;

public class Customer {

    //instance variables
    private String name;
    private String telNumber;
    private String email;
    private String customerId;

    //static variable to increment with each object created. Forms part of customer ID
    private static int numericId = 1;

    //constructor
    public Customer(String aName, String aTelNumber, String aEmail)
    {
        name = aName;
        telNumber = aTelNumber;
        email = aEmail;
        customerId = "cust" + numericId;
        numericId++;
    }

    public String getName() {
        return name;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCustomerId() {
        return customerId;
    }

}
