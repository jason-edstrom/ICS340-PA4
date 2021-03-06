import javax.swing.*;
import java.awt.*;
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
    JFrame frame;
    private Map<String,Vertex> dictionary = new HashMap<String,Vertex>( );
    String source = null;
    String destination = null;
    int words_size = 5;
    boolean sourceValid = false;
    boolean destinationValid = false;
    //ArrayList<String> leftover = null;
    ArrayList<String> builder = new ArrayList<String>();
    ArrayList<String> words = new ArrayList<String>();
    String buildSource = null;
    String buildDestination = null;
    TestGraph g = new TestGraph();
    String wordLadderError = null;
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

    public WordLadder(ArrayList<String> gui_List){
         setWordList(gui_List);

    }

    public WordLadder(String gui_file_name, int word_length){
        WordCollection wordCollection = new WordCollection(gui_file_name);
        words_size = word_length;
        //before = System.currentTimeMillis();
        WordCollection selectedSizeCollection = wordCollection.getCollectionAtSpecificLength(word_length);
        words = selectedSizeCollection.getWords();

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

        //userInput method allows user command line input for word size, source word, and destination word if no command line arguments
        if ((source == null)||(destination == null)){
        userInput();
        }
        if (source.length() != words_size){
            wordLadderError = "Source word is not the correct length";
            throw new IllegalArgumentException("Source word is not the correct length");
        }
        if (destination.length() != words_size){
            wordLadderError =  "Destination word is not the correct length";
            throw new IllegalArgumentException("Destination word is not the correct length");
        }

        sourceValid = selectedSizeCollection.compareWord(source);
        destinationValid = selectedSizeCollection.compareWord(destination);

        if  ((!sourceValid) && (!destinationValid)){
            wordLadderError = source + " and " + destination + " don't exist in the dictionary.";
            throw new IllegalArgumentException(source + " and " + destination + " don't exist in the dictionary.");
        }

        if  (!sourceValid){
            wordLadderError = source + " doesn't exist in the dictionary.";
            throw new IllegalArgumentException(source + " doesn't exist in the dictionary.");
        }
        if  (!destinationValid){
            wordLadderError = destination + " doesn't exist in the dictionary.";
            throw new IllegalArgumentException(destination + " doesn't exist in the dictionary.");
        }
      System.out.println("Source: " + source);
      System.out.println("Destination: " + destination);
      System.out.println("Word Length: " + words_size);

     words = selectedSizeCollection.getWords();

        dictionary = g.getVertexMap();
        /*leftover = selectedSizeCollection.getWords();
        for (String s : words){
        if (dictionary.containsValue(s)){
            leftover.remove(s);
        }
        }
        */
        buildGraph();
        findPath(source, destination, words_size);




    }
     public void buildGraph(){
     before = System.currentTimeMillis();
     for (String s : words){
                         //
          builder = compareWords(s, words);                 //

          buildSource = s;

          for(String net : builder){
          double cost = Math.random()*100;
          buildDestination = net;
          g.addEdge(buildSource, buildDestination, cost);
          }
      }
      after  = System.currentTimeMillis();
      buildGraphTime = after - before;
      System.out.println( g.getVertexMapSize() + " vertices" );
      System.out.println("Time to build graph: " + buildGraphTime + " milliseconds");
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

    public void findPath(String passedSource, String passedDestination, int size){
        words_size = size;
        before  = System.currentTimeMillis();
        dictionary = g.getVertexMap();
        source = passedSource;
        destination = passedDestination;
        if (source.length() != words_size){
            if (GraphicsEnvironment.isHeadless()){
            throw new IllegalArgumentException("Source word is not the correct length");
            }else{
                JOptionPane.showMessageDialog(frame,"Source word is not the correct length");
            }
        }else
        if (destination.length() != words_size){
            if (GraphicsEnvironment.isHeadless()){
            throw new IllegalArgumentException("Destination word is not the correct length");
            }else{
                JOptionPane.showMessageDialog(frame,"Destination word is not the correct length");
            }
        }else
        if (!(dictionary.containsKey(passedDestination))){
            if (GraphicsEnvironment.isHeadless()){
            throw new IllegalArgumentException(destination + " is unreachable by way of " + source + ".");
            }else{
                JOptionPane.showMessageDialog(frame,destination + " is unreachable by way of " + source + ".");
            }
        }else
        if (!(dictionary.containsKey(passedSource))){
            if (GraphicsEnvironment.isHeadless()){
            throw new IllegalArgumentException(destination + " is unreachable by way of " + source + ".");
            }else{
                JOptionPane.showMessageDialog(frame,destination + " is unreachable by way of " + source + ".");
            }
        }
        g.unweighted(passedSource);
        g.printPath(passedDestination);
        after  = System.currentTimeMillis();
        findPathTime = after - before;
        System.out.println("Time to find path: " + findPathTime  + " milliseconds");
    }

    public String getWordLadderError(){
        return wordLadderError;
    }

    public ArrayList<String>getResults(){
        return g.getResults();
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

    public void resetGraph(){
        g.resetGraph();
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


    public static void main(String[] args) {
        boolean isWindows = false;
        //OS Detection
        if (System.getProperty("os.name").startsWith("Windows")) {
            // includes: Windows 2000,  Windows 95, Windows 98, Windows NT, Windows Vista, Windows XP
            //tfFilePat.setText("c:\\ics340\\words.txt");
            System.out.println("Detected Windows: " + System.getProperty("os.name"));
            isWindows = true;
        } else {
            //tfFilePat.setText("/Users/jasonedstrom/ics340/d1.txt");
            System.out.println("Detected Mac OS X: " + System.getProperty("os.name"));
            isWindows = false;
        }
        if (args.length > 2){
            throw new ArrayStoreException("There are too many arguments for this program to run: " + args.length);
        }else if (args.length == 1){
            throw new ArrayStoreException("There are too few arguments for this program to run: " + args.length);
        }else if (args.length == 0){
            if (isWindows){
                WordLadder wordLadder = new WordLadder("c:\\ics340\\words.txt");
            }else{
                WordLadder wordLadder = new WordLadder("/Users/jasonedstrom/ics340/d1.txt");
            }

        }else{
            if (isWindows){
            WordLadder wordLadder = new WordLadder("c:\\ics340\\words.txt", args[0], args[1]);
            }else{
            WordLadder wordLadder = new WordLadder("/Users/jasonedstrom/ics340/d1.txt", args[0], args[1]);
            }
        }
    }

    public WordCollection buildCollectionForGUI(String guiFileName){
          WordCollection guiCollection = new WordCollection(guiFileName);
        return guiCollection;
    }

    public void setWordList(ArrayList<String> wordList) {
        words = wordList;
    }

    public void setWords_size(int size){
        words_size = size;
    }
}

