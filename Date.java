package project2;

/**
 * This class represent a calendar date.
 * It stores the information about year, month, and day.
 *
 * @author Bella Chen
 *
 */

public class Date implements Comparable<Date> {

    private int year;
    private int month;
    private int day;

    /**
     * Construct a new Date Object with specified year, month, and day components.
     * @param year component of this object should be a positive integer.
     * @param month component of this object should be a positive integer in the range of 1-12.
     * @param day component of this object should depends on the month of the year, and the year.
     * @throws IllegalArgumentException if year or month or day parameter is invalid.
     */
    public Date (int year, int month, int day) {
        //validate if the parameters are valid
        if (year < 0)
            throw new IllegalArgumentException ("Invalid value for the year component. Valid value has to be a positive integer.");
        if (month < 1 || month >12)
            throw new IllegalArgumentException ("Invalid value for the year component. Valid value has to be a positive integer from 1-12.");
        if (day < 1 || day > 31)
            throw new IllegalArgumentException ("Invalid value for the day component.");
        if (day == 31 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11))
            throw new IllegalArgumentException ("Invalid value for the day component.");
        if (day == 30  && month == 2)
            throw new IllegalArgumentException ("Invalid value for the day component.");
        if (day == 29 && month == 2) {
            if (!leapYear(year)) {
                throw new IllegalArgumentException("Invalid value for the day component.");
            }
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Method that determines whether year is a leap year or not.
     * @param year variable should be a positive integer.
     */
    private boolean leapYear (int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 100 != 0) {
            return true;
        } else if (year % 400 != 0) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * Return the string representation of this Date.
     * @returns the string representation of this Data object.
     */
    @Override
    public String toString() {
            return String.format("%d-%02d-%02d",
                    this.year, this.month,this.day);
    }

    /**
     * Indicates whether some Object obj is equal to this one.
     * Date objects are considered equal if they represent identical dates.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Date))
            return false;
        if (this.compareTo((Date) obj) == 0 ) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Compare this object with specified object for order.
     * The earlier dates are smaller than the later dates.
     * @param o is the object to be compared.
     * @return a negative integer, or zero, or a positive integer
     */
    @Override
    public int compareTo (Date o) {
        if (this.year > o.year)
            return 1;
        if (this.year < o.year)
            return -1;

        if (this.month > o.month)
            return 1;
        if (this.month < o.month)
            return -1;

        if (this.day > o.day)
            return 1;
        if (this.day < o.day)
            return -1;
        return 0;
    }


}
