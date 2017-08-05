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

        if(h >= 0 && m >= 0 && s >= 0 && mi >= 0){      // TODO: This is just a temporary check
            int rem_h1 = h;
            int rem_h2 = h;
            int rem_m1 = m;
            int rem_m2 = m;
            int rem_s1 = s;
            int rem_s2 = s;

            if ((this.milli1 + mi) >= 1000) {
                rem_s1 += ((this.milli1 + mi) / 1000);
                this.milli1 = ((this.milli1 + mi) % 1000);
            } else {
                this.milli1 += mi;
            }

            if ((this.milli2 + mi) >= 1000) {
                rem_s2 += ((this.milli2 + mi) / 1000);
                this.milli2 = ((this.milli2 + mi) % 1000);
            } else {
                this.milli2 += mi;
            }

            if ((this.second1 + rem_s1) >= 60) {
                rem_m1 += ((this.second1 + rem_s1) / 60);
                this.second1 = ((this.second1 + rem_s1) % 60);
            } else {
                this.second1 += rem_s1;
            }

            if ((this.second2 + rem_s2) >= 60) {
                rem_m2 += ((this.second2 + rem_s2) / 60);
                this.second2 = ((this.second2 + rem_s2) % 60);
            } else {
                this.second2 += rem_s2;
            }

            if ((this.minute1 + rem_m1) >= 60) {
                rem_h1 += ((this.minute1 + rem_m1) / 60);
                this.minute1 = ((this.minute1 + rem_m1) % 60);
            } else {
                this.minute1 += rem_m1;
            }

            if ((this.minute2 + rem_m2) >= 60) {
                rem_h2 += ((this.minute2 + rem_m2) / 60);
                this.minute2 = ((this.minute2 + rem_m2) % 60);
            } else {
                this.minute2 += rem_m2;
            }

            this.hour1 += rem_h1;
            this.hour2 += rem_h2;
        }
    }


    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d,%03d --> %02d:%02d:%02d,%03d",
                hour1, minute1, second1, milli1, hour2, minute2, second2, milli2);
    }
}
