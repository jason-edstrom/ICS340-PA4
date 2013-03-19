import javax.print.attribute.standard.Destination;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-PA4
 * Date: 3/19/13
 * Time: 12:21 AM
 * Java Class: PACKAGE_NAME
 */
public class WordLadder {
    String source = "blood";
    String destination = "bleed";
    int words_size = 5;
    boolean sourceValid = false;
    boolean destinationValid = false;


    public WordLadder (){
       this("c:\\ics340\\words.txt");

    }

    public WordLadder(String a_file_name){
        this(a_file_name, null, null);
    }

    public WordLadder (String a_file_name, String passedSource, String passedDestination){

        WordCollection wordCollection = new WordCollection(a_file_name);
       WordCollection selectedSizeCollection = wordCollection.getCollectionAtSpecificLength(words_size);
       //System.out.println("done");

        if (passedSource != null){
            source = passedSource;
        }

        if (passedDestination != null){
            destination = passedDestination;
        }

        //userInput method allows user command line input for word size, source word, and destination word.
       //userInput();

        if (source.length() != words_size){
            throw new IllegalArgumentException("Source word is not the correct length");
        }
        if (destination.length() != words_size){
            throw new IllegalArgumentException("Destination word is not the correct length");
        }

        sourceValid = selectedSizeCollection.compareWord(source);
        destinationValid = selectedSizeCollection.compareWord(destination);

        if  ((!sourceValid) && (!destinationValid)){
            throw new IllegalArgumentException(source + " and " + destination + " don't exist in the dictionary.");
        }

        if  (!sourceValid){
            throw new IllegalArgumentException(source + " doesn't exist in the dictionary.");
        }
        if  (!destinationValid){
            throw new IllegalArgumentException(destination + " doesn't exist in the dictionary.");
        }
      System.out.println("Source: " + source);
      System.out.println("Desitnation: " + destination);
      System.out.println("Word Length: " + words_size);

    }

    private void userInput() {
     Scanner scanner = new Scanner(System.in);
     System.out.print("Word Size: ");
     words_size = scanner.nextInt();
     System.out.print("Source word (" + words_size + " Characters only): ");
     source = scanner.next();
     System.out.print("Destination word (" + words_size + " Characters only): ");
     destination = scanner.next();
    }

    public void findPath(String source, String destination){

    }

    public ArrayList<String> getWordList(){


        return null;
    }

    public double getTimeForGraph(){


        return 0;
    }

    public double getTimeForPath(){

        return 0;
    }

    public void setSource(String passedSource){
        source =  passedSource;
    }

    public void setDestination(String passedDestination){
       destination =  passedDestination;
    }
}

