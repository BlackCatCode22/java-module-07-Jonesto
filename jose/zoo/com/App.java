package jose.zoo.com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to my Zoo Program!");

        String filePath = "animalNames.txt";
        AnimalNameListsWrapper animalLists = jose.zoo.com.Utilities.createAnimalNameLists(filePath);

        ArrayList<String> listOfHyenaNames = animalLists.getHyenaNameList();
        ArrayList<String> listOfLionNames = animalLists.getLionNameList();
        ArrayList<String> listOfTigerNames = animalLists.getTigerNameList();
        ArrayList<String> listOfBearNames = animalLists.getBearNameList();

        printAnimalNames(listOfHyenaNames, "Hyena");
        printAnimalNames(listOfLionNames, "Lion");
        printAnimalNames(listOfTigerNames, "Tiger");
        printAnimalNames(listOfBearNames, "Bear");

        try (BufferedReader reader = new BufferedReader(new FileReader("arrivingAnimals.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processAnimalLine(line, listOfHyenaNames);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void printAnimalNames(ArrayList<String> names, String animalType) {
        System.out.println("\n" + animalType + " Names:");
        for (String name : names) {
            System.out.println(name);
        }
    }

    private static void processAnimalLine(String line, ArrayList<String> hyenaNames) {
        System.out.println("\nProcessing: " + line);

        String[] partsComma = line.split(", ");
        if (partsComma.length < 6) {
            System.err.println("Invalid line format: insufficient data");
            return;
        }

        try {

            String aniColor = partsComma[2];
            String aniWeight = partsComma[3];
            String aniOrigin01 = partsComma[4];
            String aniOrigin02 = partsComma[5];
            String aniArrivalDate = jose.zoo.com.Utilities.arrivalDate();


            String[] partsSpace = partsComma[0].split(" ");
            if (partsSpace.length < 5) {
                System.err.println("Invalid format in first segment");
                return;
            }

            int intAniAge = Integer.parseInt(partsSpace[0]);
            String aniSex = partsSpace[3];
            String aniSpecies = partsSpace[4];


            String[] partsSpace02 = partsComma[1].split(" ");
            if (partsSpace02.length < 3) {
                System.err.println("Invalid format in second segment");
                return;
            }

            String animalBirthSeason = partsSpace02[2];


            printAnimalDetails(intAniAge, aniSex, aniSpecies, aniColor,
                    aniWeight, aniOrigin01, aniOrigin02, animalBirthSeason);


            if (aniSpecies.equalsIgnoreCase("hyena")) {
                processHyena(hyenaNames);
            }

        } catch (NumberFormatException e) {
            System.err.println("Error parsing animal age: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error processing line: invalid format");
        }
    }

    private static void printAnimalDetails(int age, String sex, String species,
                                           String color, String weight,
                                           String origin1, String origin2,
                                           String birthSeason) {
        System.out.println("\nAnimal Details:");
        System.out.println("Age: " + age);
        System.out.println("Sex: " + sex);
        System.out.println("Species: " + species);
        System.out.println("Color: " + color);
        System.out.println("Weight: " + weight);
        System.out.println("Origin 1: " + origin1);
        System.out.println("Origin 2: " + origin2);
        System.out.println("Birth Season: " + birthSeason);
    }

    private static void processHyena(ArrayList<String> hyenaNames) {
        System.out.println("\nThe animal is a hyena!");
        String aniID = jose.zoo.com.Utilities.calcAnimalID("hyena");
        System.out.println("Animal ID is: " + aniID);

        if (!hyenaNames.isEmpty()) {
            String myName = hyenaNames.remove(0);
            System.out.println("Assigned name: " + myName);
        } else {
            System.out.println("No more hyena names available!");
        }
    }
}