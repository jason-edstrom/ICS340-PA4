import javax.print.attribute.standard.Destination;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Timestamp;
import java.util.*;

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
    private Map<Integer, ArrayList<String>> dictionary = new HashMap<Integer, ArrayList<String>>();
    String source = "bloom";
    String destination = "green";
    int words_size = 5;
    boolean sourceValid = false;
    boolean destinationValid = false;
    ArrayList<String> results = new ArrayList<String>();
    ArrayList<String> builder = new ArrayList<String>();
    ArrayList<String> words = new ArrayList<String>();
    String buildSource = null;
    String buildDestination = null;
    TestGraph g = new TestGraph();
    long before = 0;
    long after = 0;
    long buildGraphTime = 0;
    long findPathTime = 0;
    public WordLadder (){
       this("c:\\ics340\\words.txt");

    }

    public WordLadder(String a_file_name){
        this(a_file_name, null, null);
    }

    public WordLadder (String a_file_name, String passedSource, String passedDestination){

        WordCollection wordCollection = new WordCollection(a_file_name);
        //before = System.currentTimeMillis();
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
      System.out.println("Destination: " + destination);
      System.out.println("Word Length: " + words_size);

     words = selectedSizeCollection.getWords();


      //dictionary.put(words_size, words);

    before = System.currentTimeMillis();
     for (String s : words){
        //for (int index = 0; index < words.size(); index++){ //Stops concurrent modification error
       // String s = words.get(index);                      //
          builder = compareWords(s, words);                 //
          //Stops double edging.
         // words.remove(s);
          //int indexSource = 0;
          //int indexDest = 0;
         //indexSource = words.indexOf(s);
         //indexDest = indexSource +1;
          //f (buildSource == null){
          //buildSource = s;
          //}else{
          //    buildDestination = s;
          // }

          //if (indexDest > words_size){
           // buildDestination = null;
          //}else{
          //buildDestination = words.get(indexDest);
          //}
          buildSource = s;

          for(String net : builder){
          double cost = Math.random()*100;
          buildDestination = net;
          g.addEdge(buildSource, buildDestination, cost);
          }

          //buildSource = buildDestination;

      }
      after  = System.currentTimeMillis();
      buildGraphTime = after - before;
      System.out.println( g.getVertexMapSize() + " vertices" );
      System.out.println("Time to build graph: " + buildGraphTime);

        findPath(source, destination);



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

    public void findPath(String passedSource, String passedDestination){
        before  = System.currentTimeMillis();
        g.unweighted(passedSource);
        g.printPath(passedDestination);
        after  = System.currentTimeMillis();
        findPathTime = after - before;
        System.out.println("Time to find path: " + findPathTime);


    }

    public ArrayList<String> compareWords(String word1, ArrayList<String> wordsToTest){
        ArrayList<String> buildWords = new ArrayList<String>();
        for (String word2 : wordsToTest){
        int mismatchCounter = 0;

        for (int index = 0; index < words_size; index++){
            if (word1.charAt(index) != word2.charAt(index)) {
                mismatchCounter++;
            }
            /*else if ( mismatchCounter == 0){
                words.remove(word1);
            }*/
        }
            if (mismatchCounter == 1){
                buildWords.add(word2);
            }
        }

        return buildWords;
    }



    public ArrayList<String> getWordList(){


        return words;
    }

    public double getTimeForGraph(){


        return buildGraphTime;
    }

    public double getTimeForPath(){

        return findPathTime;
    }

    public void setSource(String passedSource){
        source =  passedSource;
    }

    public void setDestination(String passedDestination){
       destination =  passedDestination;
    }


}

