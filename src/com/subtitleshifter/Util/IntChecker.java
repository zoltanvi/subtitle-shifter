package com.subtitleshifter.Util;

public class IntChecker {

    /**
     * Checks a string to see if it is an integer
     * @param s is the string
     * @return true if it is an integer, false if not
     */
    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    /**
     * Checks a string to see if it is an integer
     * @param s is the string
     * @param radix is the radix of the number
     * @return true if it is an integer, false if not
     */
    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()){
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if ((i == 0) && (s.charAt(i) == '-')) {
                if (s.length() == 1){
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), radix) < 0){
                return false;
            }
        }
        return true;
    }
}
