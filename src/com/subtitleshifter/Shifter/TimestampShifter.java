package com.subtitleshifter.Shifter;

public class TimestampShifter {
    private int startingHour;
    private int startingMinute;
    private int startingSecond;
    private int startingMilliSecond;
    private int endingHour;
    private int endingMinute;
    private int endingSecond;
    private int endingMilliSecond;

    /**
     * Initialize the timestamp
     * Example: 00:00:00,000 --> 01:59:59,999
     * @param startingHour is the first hour in the timestamp
     * @param startingMinute is the first minute in the timestamp
     * @param startingSecond is the first second in the timestamp
     * @param startingMilliSecond is the first millisecond in the timestamp
     * @param endingHour is the second hour in the timestamp
     * @param endingMinute is the second minute in the timestamp
     * @param endingSecond is the second second in the timestamp
     * @param endingMilliSecond is the second millisecond in the timestamp
     */
    public TimestampShifter(int startingHour,
                            int startingMinute,
                            int startingSecond,
                            int startingMilliSecond,
                            int endingHour,
                            int endingMinute,
                            int endingSecond,
                            int endingMilliSecond){
        this.startingHour = startingHour;
        this.startingMinute = startingMinute;
        this.startingSecond = startingSecond;
        this.startingMilliSecond = startingMilliSecond;
        this.endingHour = endingHour;
        this.endingMinute = endingMinute;
        this.endingSecond = endingSecond;
        this.endingMilliSecond = endingMilliSecond;
    }

    /**
     * Shift the time in the timestamp (both sides).
     * If the time would be negative after the shift, it'll be set to 0.
     * Example:
     * > shiftTimes(1, 1, 1, 1)
     *  00:01:21,540 --> 00:01:30,000   will be   01:02:22,541 --> 01:02:31,001
     * @param h is the offset in hours
     * @param m is the offset in minutes
     * @param s is the offset in seconds
     * @param mi is the offset in milliseconds
     */
    public void shiftTimes(int h, int m, int s, int mi) {
        long changeTimeMilliSec = (h * 3600000) + (m * 60000) + (s * 1000) + mi;
        long startTimeMilliSec = (this.startingHour * 1000 * 60 * 60) +
                    (this.startingMinute * 1000 * 60) +
                    (this.startingSecond * 1000) +
                    this.startingMilliSecond;
        long endTimeMilliSec = (this.endingHour * 1000 * 60 * 60) +
                    (this.endingMinute * 1000 * 60) +
                    (this.endingSecond * 1000) +
                    this.endingMilliSecond;

        startTimeMilliSec += changeTimeMilliSec;
        endTimeMilliSec += changeTimeMilliSec;

        this.startingMilliSecond = ((startTimeMilliSec % 1000) < 0 ? 0 : (int)(startTimeMilliSec % 1000));
        this.startingSecond = ((startTimeMilliSec / 1000) % 60) < 0 ? 0 : (int)(startTimeMilliSec / 1000) % 60;
        this.startingMinute = ((startTimeMilliSec / (1000 * 60)) % 60) < 0 ? 0 : (int)(startTimeMilliSec / (1000 * 60)) % 60;
        this.startingHour = ((startTimeMilliSec / (1000 * 60 * 60)) % 24) < 0 ? 0 : (int)(startTimeMilliSec / (1000 * 60 * 60)) % 24;

        this.endingMilliSecond = ((endTimeMilliSec % 1000)) < 0 ? 0 : (int)(endTimeMilliSec % 1000);
        this.endingSecond = ((endTimeMilliSec / 1000) % 60) < 0 ? 0 : (int)(endTimeMilliSec / 1000) % 60;
        this.endingMinute = ((endTimeMilliSec / (1000 * 60)) % 60) < 0 ? 0 : (int)(endTimeMilliSec / (1000 * 60)) % 60;
        this.endingHour = ((endTimeMilliSec / (1000 * 60 * 60)) % 24) < 0 ? 0 : (int)(endTimeMilliSec / (1000 * 60 * 60)) % 24;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d,%03d --> %02d:%02d:%02d,%03d",
                startingHour, startingMinute, startingSecond, startingMilliSecond, endingHour, endingMinute, endingSecond, endingMilliSecond);
    }
}