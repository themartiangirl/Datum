package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;


/** This class is the program performing
 * opening and reading the data file, obtaining user input,check data validation, handle errors.
 * The program is interactive.
 *
 * @author Bella Chen
 *
  */


public class DataIsPlural {

    /**
     * The main() method of this program.
     * @param args array of Strings provided on the command line when the program is started.
     */

    public static void main(String[] args) {

        //Verify that the command line argument exists
        if(args.length == 0) {
            System.err.println("Usage Error: the program expects file name as an argument.");
            System.exit(1);
        }

        //Verify that command line argument contains a name of an existing file
        File dataFile = new File(args[0]);
        if (!dataFile.exists()){
            System.err.println("Error: the file " + dataFile.getAbsolutePath() + " does not exist.");
            System.exit(1);
        }
        if (!dataFile.canRead()){
            System.err.println("Error: the file " + dataFile.getAbsolutePath() + " cannot be opened for reading.");
            System.exit(1);
        }

        //Open the file for reading
        Scanner inData = null;

        try {
            inData = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            System.out.println("Error: the file " + dataFile.getAbsolutePath() + " cannot be opened for reading.\n");
            System.exit(1);
        }

        //Explain what user can do. 
        System.out.println("\n Welcome to the Data Is Plural data explorer!\n");

        System.out.println("You can use the following queries to search through the data: \n" 
                            + "title KEYWORD \n" + "description KEYWORD \n" + "url KEYWORD \n") ;
        
        System.out.println("You can combine up to two queries to narrow down the results, for example:\n"
                            + "title KEYWORD1  " + "  url KEYWORD2");


		CSV csv = new CSV(inData); 
        DataSetList dataSetList = new DataSetList();

        ArrayList<String> row = csv.getNextRow();
        String title, description, urls, hattips, dateString;
		for (int i = 0; i < csv.getNumOfRows(); i++ ) {
            row = csv.getNextRow();

            title = row.get(2);
            description = row.get(3);
            urls = row.get(4);

            ArrayList<URL> urlList = new ArrayList<URL>(); 
            for (String url : urls.split("\n")) {
                try {
                  urlList.add(new URL(url));
                } catch(Exception e) {
                    break;
                }
            }
            DataSet ds;

            try {
               ds = new DataSet(title, description, urlList); 
            } catch (Exception e) {
                break;
            }

            dateString = row.get(0);
            if (!dateString.isEmpty()) {
                String[] dateArray = dateString.split("\\.");
                if (dateArray.length == 3) {
                  Date date = new Date(Integer.valueOf(dateArray[0]), Integer.valueOf(dateArray[1]), Integer.valueOf(dateArray[2]));
                  ds.setDate(date);
                }
            }

            if (row.size() > 5) {
              hattips = row.get(5);
              if (!hattips.isEmpty()) {
                  ds.setHatTips(hattips);
              }
            }
        
            dataSetList.add(ds);
		}

        //Interactive mode :
        Scanner userInput = new Scanner(System.in);
        String userValue = "";

        do {
            System.out.println("Enter query or \"quit\" to stop:");
            //get the value from user
            userValue = userInput.nextLine();
            if (!userValue.equalsIgnoreCase("quit")) {
                String[] input = userValue.split(" ");
                DataSetList filtered = new DataSetList();
                if (input.length > 1) {
                    if (input[0].equalsIgnoreCase("title")) {
                        filtered = dataSetList.getByTitle(input[1]);
                    } else if (input[0].equalsIgnoreCase("description")) {
                        filtered = dataSetList.getByDescription(input[1]);
                    } else if (input[0].equalsIgnoreCase("url")) {
                        filtered = dataSetList.getByURL(input[1]);
                    } else {
                        System.out.println("No matches found. Try again.");
                    }
                    if (input.length > 3) {
                        if (input[2].equalsIgnoreCase("title")) {
                           filtered = filtered.getByTitle(input[3]);
                       }
                       if (input[2].equalsIgnoreCase("description")) {
                           filtered = filtered.getByDescription(input[3]);
                        }
                        if (input[2].equalsIgnoreCase("url")) {
                           filtered = filtered.getByURL(input[3]);
                        }
                    }
                    if (filtered != null) {
                      for (DataSet f : filtered) {
                         System.out.println(f + "\n" + "-----");
                      }
                    }
                }
            }
        } while (!userValue.equalsIgnoreCase("quit"));
    }

}
