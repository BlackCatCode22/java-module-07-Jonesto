package jose.zoo.com;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class Utilities {
    public static String calcAnimalID(String animalSpecies) {
        String myID = "";
        if (animalSpecies.contains("hy")) {
            int myNumOfHyenas = 0;
            myNumOfHyenas = jose.zoo.com.Hyena.numOfHyenas + 1;
            myID = "Hy0" + myNumOfHyenas;
        }
        return myID;
    }
    public static String arrivalDate() {
        Date today = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatterYear = new SimpleDateFormat("yyyy");
// Format the date and store it in a string
        String strTodaysDate = formatter.format(today);
        String strTodaysYear = formatterYear.format(today);
// Return the date as a string
        return strTodaysDate;
    }
    public static String calcAnimalBirthDate(int age, String theSeason) {
// This is all of the date stuff we did last week:
// Create a Date object to represent the current date
        Date today = new Date();
// Define the desired date format
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatterYear = new SimpleDateFormat("yyyy");
// Format the date and store it in a string
        String strTodaysDate = formatter.format(today);
        String strTodaysYear = formatterYear.format(today);
// Output the result
        System.out.println("Today's date in the format yyyy-MM-dd: " +
                strTodaysDate);
        String animalBirthdate = "";
        int todaysYear = Integer.parseInt(strTodaysYear);
        int animalBirthYear = todaysYear - Integer.parseInt(String.valueOf(age));
// Normalize the input season to lower case for case-insensitive comparison
        String season = theSeason.toLowerCase();
        switch (season) {
            case "spring":
                animalBirthdate = Integer.toString(animalBirthYear) + "-03-21";
                break;
            case "fall":
                animalBirthdate = Integer.toString(animalBirthYear) + "-09-21";
                break;
            case "winter":
                animalBirthdate = Integer.toString(animalBirthYear) + "-12-21";
                break;
            case "summer":
                animalBirthdate = Integer.toString(animalBirthYear) + "-06-21";
                break;
            default:
                animalBirthdate = Integer.toString(animalBirthYear) + "-01-01"; //Default case for anything else
                break;
        }
        return animalBirthdate;
    }
    public static AnimalNameListsWrapper createAnimalNameLists(String filePath) {
// Create ArrayLists for each species
        ArrayList<String> hyenaNameList = new ArrayList<>();
        ArrayList<String> lionNameList = new ArrayList<>();
        ArrayList<String> tigerNameList = new ArrayList<>();
        ArrayList<String> bearNameList = new ArrayList<>();
// Try-with-resources to automatically close the reader
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            ArrayList<String> currentList = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim(); // Trim any leading or trailing spaces
// Check for the species names and assign the appropriate list
                if (line.equals("Hyena Names:")) {
                    currentList = hyenaNameList;
                } else if (line.equals("Lion Names:")) {
                    currentList = lionNameList;
                } else if (line.equals("Tiger Names:")) {
                    currentList = tigerNameList;
                } else if (line.equals("Bear Names:")) {
                    currentList = bearNameList;
                } else if (!line.isEmpty()) {

                    String[] names = line.split(",\\s*");
                    for (String name : names) {
                        if (currentList != null) {
                            currentList.add(name);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " +
                    e.getMessage());
        }

        return new AnimalNameListsWrapper(hyenaNameList, lionNameList,
                tigerNameList, bearNameList);
    }
}