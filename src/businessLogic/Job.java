/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businessLogic;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author animi
 */
public class Job {

    //instance variables
    private Date date;
    private int startTime;
    private int duration;

    private Format formatter = new SimpleDateFormat("MM/dd/yyyy");

    //constructor
    Job(Date aDate, int aStartTime, int aDuration)
    {
        date = aDate;
        startTime = aStartTime;
        duration = aDuration;
    }

    public Date getDate()
    {
        return date;
    }

    public int getStartTime()
    {
        return startTime;
    }

    public int getDuration()
    {
        return duration;
    }

    @Override
    public String toString()
    {
        String theDateString = formatter.format(getDate());
        String result = "Date: " + theDateString + " Start Time: " + getStartTime()
                + ":00 Duration: " + getDuration() + " hours.\n";
        return result;
    }

}
