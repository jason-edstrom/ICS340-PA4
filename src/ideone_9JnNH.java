import java.util.*;

class Test {
    private static final Map<Integer, List<String>> dictionary = new HashMap<Integer, List<String>>();
    //private static final
    static {
		List<String> words = new ArrayList<String>();
                /*words.add("cake");
                words.add("rake");
                words.add("make");
                words.add("fake");
                words.add("poke");
                words.add("joke");
                words.add("pole");
                words.add("pone");
                words.add("pony");
                words.add("coke");
               */

                WordCollection wordCollection = new WordCollection("d1.txt");
                WordCollection wordLength = wordCollection.getCollectionAtSpecificLength(5);
                ArrayList<String> wordsAL = wordLength.getWords();
                for (String s : wordsAL){
                    words.add(s);
                }
                dictionary.put(5, words);
        System.out.println(dictionary);
	}

	public static List<String> findLadder(String word1, String word2) {
	    //invalid request
	    if (word1 == null || word2 == null || word1.length() != word2.length()) {
	        return Collections.emptyList();
	    }
        char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	    List<String> result = new ArrayList<String>();
	    List<String> words = dictionary.get(word1.length());
	    result.add(word1);
        ArrayList checks = new ArrayList();
        for (int index = 0; index < word1.length(); index++) {

            if (word1.charAt(index) != word2.charAt(index)) {
                boolean match = false;
                checks.add(index, match);
            }else{
                boolean match = true;
                checks.add(index, match);
            }
        }
	    while (! word1.equals(word2)) {
	        String nextWord = null;

	        int index = checks.indexOf(false);
            for (char c : letters){
            String testWord =  word1.substring(0, index-1) + c + word1.substring(index + 1);


            }
	        /*
	        for (int index = 0; index < word1.length(); index++) {

	            if (word1.charAt(index) != word2.charAt(index)) {
	                //String testWord = word1.substring(0, index) + word2.charAt(index) + word1.substring(index + 1);
                    String testWord =  word1.substring(0, index);
	                if (words.contains(testWord)) { 
	                    nextWord = testWord;
	                    word1 = nextWord;
	                    result.add(word1);
	                    break;
	                }
	            }
	        } */
	        if (nextWord == null) {
	            //no mapping exists
	            return Collections.emptyList();
	        }
	    }

            return result;
	}

	public static void main(String[] args) {
		System.out.println(findLadder("bleed", "blood"));
	}

}