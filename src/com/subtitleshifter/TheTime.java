package com.subtitleshifter;

public class TheTime {
    private int hour1;
    private int minute1;
    private int second1;
    private int milli1;
    private int hour2;
    private int minute2;
    private int second2;
    private int milli2;

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

            this.milli1 = ((newd1 % 1000) < 0 ? 0 : (int)(newd1 % 1000)) ;
            this.second1 = ((newd1 / 1000) % 60) < 0 ? 0 : (int)(newd1 / 1000) % 60;
            this.minute1 = ((newd1 / (1000 * 60)) % 60) < 0 ? 0 : (int)(newd1 / (1000 * 60)) % 60;
            this.hour1 = ((newd1 / (1000 * 60 * 60)) % 24) < 0 ? 0 : (int)(newd1 / (1000 * 60 * 60)) % 24;

            this.milli2 = ((newd2 % 1000)) < 0 ? 0 : (int)(newd2 % 1000);
            this.second2 = ((newd2 / 1000) % 60) < 0 ? 0 : (int)(newd2 / 1000) % 60;
            this.minute2 = ((newd2 / (1000 * 60)) % 60) < 0 ? 0 : (int)(newd2 / (1000 * 60)) % 60;
            this.hour2 = ((newd2 / (1000 * 60 * 60)) % 24) < 0 ? 0 : (int)(newd2 / (1000 * 60 * 60)) % 24;
    }


    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d,%03d --> %02d:%02d:%02d,%03d",
                hour1, minute1, second1, milli1, hour2, minute2, second2, milli2);
    }
}
