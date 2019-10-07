/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businessLogic;

import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author animi
 */
public class Driver {

    //instance variables
    private String name;

    //associations
    private HashSet<Job> jobs;

    //constructor
    Driver(String aName)
    {
        name = aName;
        jobs = new HashSet<Job>();
    }

    public String addJob(Date aDate, int aStartTime, int aDuration)
    {
        Job aJob = new Job(aDate, aStartTime, aDuration);
        jobs.add(aJob);
        String result = "New job created.";
        return result;
    }

    public HashSet<Job> getJobs()
    {
        HashSet<Job> result = new HashSet<Job>();
        for(Job eachJob : jobs)
        {
            result.add(eachJob);
        }
        return result;
    }

    public boolean eligibleToday(Date aDate, int aDuration)
    {
        boolean result = true;

        //check driver not exceeding 9 hours today
        HashSet<Job> todayJobs = new HashSet<Job>();
        for(Job eachJob : jobs)
        {
            if(eachJob.getDate().equals(aDate))
            {
                todayJobs.add(eachJob);
            }
        }
        int totalHoursToday = 0;
        for(Job eachTodayJob : todayJobs)
        {
            totalHoursToday = totalHoursToday + eachTodayJob.getDuration();
        }
        if(totalHoursToday + aDuration > 9)
        {
            result = false;
        }

        return result;
    }

    @Override
    public String toString()
    {
        return name;
    }

}
