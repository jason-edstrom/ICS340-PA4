import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-PA4
 * Date: 3/19/13
 * Time: 12:59 AM
 * Java Class: PACKAGE_NAME
 */
public class WordCollection {
   private ArrayList<String> words = new ArrayList<String>();

    public WordCollection(){

    }


    public WordCollection (String file_name){



        try {

            FileReader fileReader = new FileReader(file_name);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String strLine;

            while ((strLine = bufferedReader.readLine()) != null){


                words.add(strLine);
                //System.out.println("adding");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public WordCollection getCollectionAtSpecificLength(int words_size){
         WordCollection lengthCollection = new WordCollection();
          for (String word: words){
              if ( word.length() == words_size){
                  lengthCollection.addWord(word);
              }
          }



        return lengthCollection;
    }

    public void addWord (String word){
          words.add(word);
    }

    public boolean compareWord (String word){
        for (String compare: words){
           if (word.equals(compare)){
             return true;
           }
        }
        return false;
    }

    public ArrayList<String> getWords(){
        return words;
    }
}
