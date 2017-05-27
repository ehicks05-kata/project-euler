package net.ehicks.euler;

public class Problem19Day
{
    private int dayID;
    private String dayOfWeek;
    private String month;
    private int dayOfMonth;
    private int year;
    private int dayOfYear;

    public int getDayID()
    {
        return dayID;
    }
    public String getDayOfWeek()
    {
        return dayOfWeek;
    }
    public String getMonth()
    {
        return month;
    }
    public int getDayOfMonth()
    {
        return dayOfMonth;
    }
    public int getYear()
    {
        return year;
    }
    public int getDayOfYear()
    {
        return dayOfYear;
    }

    public void setDayID(int newDayID)
    {
        dayID = newDayID;
    }
    public void setDayOfWeek(String newDayOfWeek)
    {
        dayOfWeek = newDayOfWeek;
    }
    public void setMonth(String newMonth)
    {
        month = newMonth;
    }
    public void setDayOfMonth(int newDayOfMonth)
    {
        dayOfMonth = newDayOfMonth;
    }
    public void setYear(int newYear)
    {
        year = newYear;
    }
    public void setDayOfYear(int newDayOfYear)
    {
        dayOfYear = newDayOfYear;
    }

    public void displayProblem19Day()
    {
        System.out.printf("%-6d %-10s %-5s %-4d %5d %4d %s", dayID, dayOfWeek, month, dayOfMonth+1, year+1901, dayOfYear+1,"\n");
    }
}