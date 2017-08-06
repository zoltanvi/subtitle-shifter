package com.subtitleshifter.Shifter;

public class TheTime {
    private int hour1;
    private int minute1;
    private int second1;
    private int milli1;
    private int hour2;
    private int minute2;
    private int second2;
    private int milli2;

    /**
     * Initialize the timestamp
     * Example: 00:00:00,000 --> 01:59:59,999
     * @param hour1 is the first hour in the timestamp
     * @param minute1 is the first minute in the timestamp
     * @param second1 is the first second in the timestamp
     * @param milli1 is the first millisecond in the timestamp
     * @param hour2 is the second hour in the timestamp
     * @param minute2 is the second minute in the timestamp
     * @param second2 is the second second in the timestamp
     * @param milli2 is the second millisecond in the timestamp
     */
    public TheTime(int hour1, int minute1, int second1, int milli1, int hour2, int minute2, int second2, int milli2) {
        this.hour1 = hour1;
        this.minute1 = minute1;
        this.second1 = second1;
        this.milli1 = milli1;
        this.hour2 = hour2;
        this.minute2 = minute2;
        this.second2 = second2;
        this.milli2 = milli2;
    }

    /**
     * Shift the time in the timestamp (both sides).
     * If the time would be negative after the shift, it'll be set to 0.
     * Example:
     * > changeTime(1, 1, 1, 1)
     *  00:01:21,540 --> 00:01:30,000   will be   01:02:22,541 --> 01:02:31,001
     * @param h is the offset in hours
     * @param m is the offset in minutes
     * @param s is the offset in seconds
     * @param mi is the offset in milliseconds
     */
    public void changeTime(int h, int m, int s, int mi) {
        long changemillis = (h * 3600000) + (m * 60000) + (s * 1000) + mi;

        long newd1 = (this.hour1 * 1000 * 60 * 60) +
                (this.minute1 * 1000 * 60) +
                (this.second1 * 1000) +
                this.milli1;
        long newd2 = (this.hour2 * 1000 * 60 * 60) +
                (this.minute2 * 1000 * 60) +
                (this.second2 * 1000) +
                this.milli2;

        newd1 += changemillis;
        newd2 += changemillis;

        this.milli1 = ((newd1 % 1000) < 0 ? 0 : (int) (newd1 % 1000));
        this.second1 = ((newd1 / 1000) % 60) < 0 ? 0 : (int) (newd1 / 1000) % 60;
        this.minute1 = ((newd1 / (1000 * 60)) % 60) < 0 ? 0 : (int) (newd1 / (1000 * 60)) % 60;
        this.hour1 = ((newd1 / (1000 * 60 * 60)) % 24) < 0 ? 0 : (int) (newd1 / (1000 * 60 * 60)) % 24;

        this.milli2 = ((newd2 % 1000)) < 0 ? 0 : (int) (newd2 % 1000);
        this.second2 = ((newd2 / 1000) % 60) < 0 ? 0 : (int) (newd2 / 1000) % 60;
        this.minute2 = ((newd2 / (1000 * 60)) % 60) < 0 ? 0 : (int) (newd2 / (1000 * 60)) % 60;
        this.hour2 = ((newd2 / (1000 * 60 * 60)) % 24) < 0 ? 0 : (int) (newd2 / (1000 * 60 * 60)) % 24;
    }


    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d,%03d --> %02d:%02d:%02d,%03d",
                hour1, minute1, second1, milli1, hour2, minute2, second2, milli2);
    }
}
