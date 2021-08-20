package project2;

import java.util.ArrayList;
import java.net.URL;



/**
 * This class represent a data set with:
 * date, title/headline, description/text, list of link, hatTips.
 *
 * @author Bella Chen
 *
 */

public class DataSet implements Comparable<DataSet> {

    private String title;
    private String description;
    private ArrayList<URL> links;
    private Date date;
    private String hatTips;

    /**
     * Constructs a new DataSet object with specified title, description, and links.
     * @param title cannot be empty or null.
     * @param description cannot be empty or null.
     * @param links cannot be empty or null.
     * @throws IllegalArgumentException if all threee parameters are invalid
     */
    public DataSet (String title, String description, ArrayList<URL> links) {
        if (title == null || title == "" ) 
           throw new IllegalArgumentException ("Invalid input for title component");
        if (description == null || description == "")
           throw new IllegalArgumentException ("Invalid input for description component");
        if (links == null || links.isEmpty())
           throw new IllegalArgumentException ("Invalid input for URL links component");

        this.title = title;
        this.description = description;
        this.links = links;
    }


    /**
     * Returns the date of this DateSet object.
     * @returns the date of this DateSet object.
     */
    public Date getDate() {
       return this.date;
    }

    /**
     * Returns the title of the DataSet object.
     * @returns the title of the DataSet object.
     */
     public String getTitle() {
        return this.title;
     }

     /**
     * Returns the description of the DataSet object.
     * @returns the description of the DataSet object.
     */
     public String getDescription() {
        return this.description;
     }

     /**
     * Returns the links of the DataSet object.
     * @returns the links of the DataSet object.
     */
     public ArrayList<URL> getLinks() {
        return this.links;
     }

    /**
     * Returns the hatTips of this DataSet object.
     * @returns the hatTips of this DataSet object.
     */
    public String getHatTips() {
       return this.hatTips;
    }

    /**
     * Validate and sets the date for this DataSet object.
     * @param date cannot be null and should have a year equal to or after the year 2000.
     * @throws IllegalArgumentException if the date is invalid.
     */
    public void setDate (Date date) {
       if (date == null || date.compareTo(new Date(2000, 1, 1)) < 0)
          throw new IllegalArgumentException ("Invalid input for date, it can't be null and should after year 2000.");
       else {
         this.date = date;
       }
    }

    /**
     * Validate and sets the hatTips for this DataSet object.
     * @param hatTips cannot be null but can be empty.
     */
    public void setHatTips (String hatTips) {
       if (hatTips == null) {
           this.hatTips = "";
        } else {
           this.hatTips = hatTips;
        }
    }

    /**
     * Returns the string representation of this DataSet object.
     * @returns the string representation of this DataSet object.
     */
    @Override
    public String toString() {
       String printString = "";
       if (this.date != null)
         printString += this.date.toString() + "\n";

       printString += this.title + "\n" + this.description + "\n";

       for (URL link : this.links) {
          printString += link.toString() + "\n";
       }

       return printString;
    }

    /**
     * Indicates whether some object obj is "equal to" this one.
     * DataSet objects are considered equal if they have identical dates and titles.(ignoring the case).
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DataSet))
            return false;
        if (this.compareTo((DataSet) obj) == 0 ) {
            return true;
        } else {
            return false;
        }   
    }

    /**
     * Compares this object with the specified object for order.
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(DataSet o) {
        if (this.date != null && o.date != null && this.date.compareTo(o.date) != 0) {
           return this.date.compareTo(o.date);
        } else {
           return this.title.compareToIgnoreCase(o.title);
        } 
    }


}
