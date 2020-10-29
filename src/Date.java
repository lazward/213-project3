/**
 * A date class that is used in all accounts. This class implements the Java
 * Interface Comparable.
 * 
 * @author Aarif Razak ahr58, Julian Lee jl2203
 *
 */

public class Date implements Comparable<Date> {

    private int year;
    private int month;
    private int day;

    // Constants for months and required days.

    private static final int monthJan = 1;
    private static final int monthDec = 12;

    private static final int minDays = 1;
    private static final int maxDays = 31;

    private static final int monthFeb = 2;
    private static final int invalidFebDays = 30;

    private static final int leapDay = 29;

    private static final int leapTestOne = 4;
    private static final int leapTestTwo = 100;
    private static final int leapTestThree = 400;

    private static final int monthApr = 4;
    private static final int monthSep = 9;
    private static final int monthJun = 6;
    private static final int monthNov = 11;

    /**
     * 
     * Compare two dates, accounting for varying month lengths and leap years
     * 
     * @param date Date to compare to.
     * @return Returns 0 if dates are equal, 1 if the first date is greater, and -1
     *         if the second date is greater.
     */
    public int compareTo(Date date) {

        if (this.year < date.year) {

            return -1;

        } else if (this.year > date.year) {

            return 1;

        }

        if (this.month < date.month) {

            return -1;

        } else if (this.month > date.month) {

            return 1;

        }

        if (this.day < date.day) {

            return -1;

        } else if (this.day < date.day) {

            return 1;

        }

        return 0;

    }

    /**
     * 
     * An overridden toString method to return the proper formatted date.
     * 
     * @return String of any given Date.
     */
    @Override
    public String toString() {

        return Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);

    }

    /**
     * 
     * A helper method to check for invalid dates.
     * 
     * @return boolean, true if the date is valid, false if it isnt.
     */
    public boolean isValid() { // Check if these numbers count as magic numbers

        if (month < monthJan || month > monthDec || day < minDays || day > maxDays) {

            return false;

        }

        // Need to check for days of the month (30 vs 31 days, leap year, etc)

        if (month == monthFeb) {

            if (day >= invalidFebDays) {

                return false;

            }

            if (day == leapDay) {

                if (year % leapTestOne != 0) { // Every year that is divisible by four is a leap year

                    return false;

                }

                if (year % leapTestTwo == 0 && year % leapTestThree != 0) { // Years that are divisible by 100 are not
                                                                            // leap years, but
                    // ones that are divisible by 400 are.

                    return false;

                }

            }

            return true;

        }

        if ((month == monthApr || month == monthJun || month == monthSep || month == monthNov) && day == maxDays) {

            return false;

        }

        return true;

    }

    /**
     * 
     * Take a split string array containing the month, date and year, and set the
     * date as such.
     * 
     * @param date String array containing date to be parsed into Date format.
     */
    public void setDate(String[] date) {

        month = Integer.parseInt(date[0]);
        day = Integer.parseInt(date[1]);
        year = Integer.parseInt(date[2]);

    }

}
