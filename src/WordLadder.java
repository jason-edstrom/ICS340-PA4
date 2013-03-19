import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

    public WordLadder ( String a_file_name){

        WordCollection wordCollection = new WordCollection(a_file_name);
       WordCollection selectedSizeCollection = wordCollection.getCollectionAtSpecificLength(words_size);
       //System.out.println("done");

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


    }



    }

