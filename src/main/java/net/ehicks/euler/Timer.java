package net.ehicks.euler;

import java.util.concurrent.TimeUnit;

public class Timer
{
    private long start;
    private long end;

    public Timer()
    {
        this.start = System.currentTimeMillis();
    }

    public void printDuration()
    {
        System.out.println("Duration: " + getDuration());
    }

    public String getDuration()
    {
        long milliseconds = System.currentTimeMillis() - start;

        long durationInMinutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        long durationInSeconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
        long leftoverSeconds = durationInSeconds - TimeUnit.MINUTES.toSeconds(durationInMinutes);
        long leftoverMillis = milliseconds - TimeUnit.SECONDS.toMillis(durationInSeconds);

        return String.format("%2d min, %2d.%03d sec", durationInMinutes, leftoverSeconds, leftoverMillis);
    }

    public String getDuration(boolean restart)
    {
        String duration = getDuration();
        if (restart)
            start = System.currentTimeMillis();
        return duration;
    }

    public void setEnd()
    {
        this.end = System.currentTimeMillis();
    }
}
