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
        if (this.end == 0) setEnd();

        long milliseconds = end - start;
        long durationInMinutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        long durationInSeconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
        long leftoverSeconds = durationInSeconds - TimeUnit.MINUTES.toSeconds(durationInMinutes);
        long leftoverMillis = milliseconds - TimeUnit.SECONDS.toMillis(durationInSeconds);
        String duration = String.format("%d min, %d.%03d sec", durationInMinutes, leftoverSeconds, leftoverMillis);
        System.out.println("Duration: " + duration);
    }

    public void setEnd()
    {
        this.end = System.currentTimeMillis();
    }
}
