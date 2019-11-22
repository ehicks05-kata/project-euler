package net.ehicks.euler;

/* How many Sundays fell on the first of the month during the twentieth century?...1 Jan 1901 = Tuesday...31 Dec 2000 = Sunday */
public class Problem019
{
    public static void main(String[] args)
    {
        final int DAYS = 36525;
        int sundayTheFirsts = 0;

        int[] monthSize =     {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] monthNames =   {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] weekdays =   {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        Problem019Day[] dayObjects = new Problem019Day[DAYS];

        int currentYear = 0, currentYearMod4, currentDayOfYear = 0;
        int currentMonth = 0, currentDayOfMonth = 0;

        //generate all the days
        for(int i = 0; i < DAYS; i++)
        {
            dayObjects[i] = new Problem019Day();
            dayObjects[i].setDayID(i);
            dayObjects[i].setDayOfWeek(weekdays[(i+2) % 7]);

            dayObjects[i].setDayOfYear(currentDayOfYear);
            dayObjects[i].setYear(currentYear);

            dayObjects[i].setDayOfMonth(currentDayOfMonth);
            dayObjects[i].setMonth(monthNames[currentMonth]);

            currentYearMod4 = currentYear % 4;

            if(currentYearMod4 != 3 && currentDayOfMonth == monthSize[currentMonth] - 1)
            {
                currentDayOfMonth = 0;
                currentMonth++;
            }
            else if(currentYearMod4 == 3 && currentMonth != 1 && currentDayOfMonth == (monthSize[currentMonth] - 1))
            {
                currentDayOfMonth = 0;
                currentMonth++;
            }
            else if(currentYearMod4 == 3 && currentMonth == 1 && currentDayOfMonth == monthSize[currentMonth])
            {
                currentDayOfMonth = 0;
                currentMonth++;
            }
            else
                currentDayOfMonth++;

            if(currentYearMod4 != 3 && currentDayOfYear == 364)
            {
                currentMonth = 0;
                currentYear++;
                currentDayOfYear = 0;
            }
            else if(currentYearMod4 == 3 && currentDayOfYear == 365)
            {
                currentMonth = 0;
                currentYear++;
                currentDayOfYear = 0;
            }
            else
                currentDayOfYear++;

            //check if this day is the first of the month
            if(dayObjects[i].getDayOfMonth() == 0 && dayObjects[i].getDayOfWeek().equals("Sunday"))
                sundayTheFirsts++;
        }

        //print each day
        for(int i = 0; i < DAYS; i++)
            dayObjects[i].displayProblem19Day();

        System.out.println("\nSundays that fell on the first of the month: "+sundayTheFirsts);
    }

    static class Problem019Day
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
}