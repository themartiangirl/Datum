package project2;

import java.util.ArrayList;
import java.net.URL;


/**
 *This class store all the DataSet objects.
 *This class inherits all of its properties from an ArrayList<DataSet>.
 *It adds DataSet specific methods that allow search by title, description, and URL.
 *
 * @author Bella Chen
 *
 */
public class DataSetList extends ArrayList<DataSet> {

    /**
     * Search through the list of DataSet for an object matching the given title.
     * @param keyword is the name of the DataSet for which to search.
     * @return the reference to a matching DataSet object in the list
     * @throws IllegalArgumentException if keyword is null or empty.
     */
    public DataSetList getByTitle (String keyword) {

        if (keyword == null || keyword == "")
            throw new IllegalArgumentException("Invalid input for title.");

        DataSetList ds = new DataSetList();

        for (DataSet dd : this) {
            String title = dd.getTitle(); 
            if (title.toLowerCase().contains(keyword.toLowerCase()))
                ds.add(dd);
        }
        ds.sort(null);
        if (ds.isEmpty())
          return null;

        return ds;
    }

    /**
     * Search through the list of DataSet for an object matching the given description.
     * @param keyword is the description value of the DataSet for which to search.
     * @return the reference to a matching DataSet object in the list.
     * @throws IllegalArgumentException if the keyword is null or empty.
     */
    public DataSetList getByDescription (String keyword ) {
                
        if (keyword == null || keyword == "")
            throw new IllegalArgumentException("Invalid input for title.");

        DataSetList ds = new DataSetList();

        for (DataSet dd : this) {
            String title = dd.getDescription(); 
            if (title.toLowerCase().contains(keyword.toLowerCase()))
                ds.add(dd);
        }
        ds.sort(null);
        if (ds.isEmpty())
          return null;
        return ds;
    }

    /**
     * Search through the list of DataSet for an object matching the given URL.
     * @param keyword is the URL link of the DataSet for which to search.
     * @return the reference to a matching DataSet object in the list.
     * @throws IllegalArgumentException if the keyword is null or empty.
     */
    public DataSetList getByURL (String keyword) {
        if (keyword == null || keyword == "")
            throw new IllegalArgumentException("Invalid input for URL.");

        DataSetList ds = new DataSetList();

        for (DataSet dd : this) {
            ArrayList<URL> urls = dd.getLinks(); 
            boolean add = false;
            for (URL url : urls) { 
               if (url.toString().toLowerCase().contains(keyword.toLowerCase()))
                 //add = true;
                 ds.add(dd);
            }
            if (add)
              ds.add(dd);
        }
        ds.sort(null);
        if (ds.isEmpty())
          return null;
        return ds;
    }

}
