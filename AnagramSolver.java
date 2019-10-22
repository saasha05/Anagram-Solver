// Name: Saasha Mor
// Date: 05/18/2018
// Section: AR
// TA: Alissa Adornato

// Class AnagramSolver find all combinations of words that have the same letters as a given phrase

import java.util.*;

public class AnagramSolver {
	Map<String, LetterInventory> dictionaryInventory;
	List<String> mainDictionary;
	
	// pre:  dictionary should be a nonempty collection of non empty sequence of letters
	//	     and dictionary should not contain duplicates
	// post: Uses list of words as a dictionary to construct anagram solver
	public AnagramSolver(List<String> list) {
		dictionaryInventory = new HashMap<String, LetterInventory>();
		mainDictionary = list;
		for(String word: list) {
			dictionaryInventory.put(word, new LetterInventory(word));
		}
	}
	
	// pre:  if  max < 0 throws IllegalArgumentException
	// post: prints all anagrams of the given string
	// parameters needed:
	//	s: the string of which anagrams need to be found
	//	max: the maximum number of words in an anagram
	public void print(String s, int max) {
		if(max < 0) {
			throw new IllegalArgumentException();
		}
		List<String> anagrams = new ArrayList<String>();
		LetterInventory word = new LetterInventory(s);
		List<String> newDictionary = new ArrayList<String>();
		for(String dictWord: mainDictionary) {
			if(word.subtract(dictionaryInventory.get(dictWord)) != null) {
				newDictionary.add(dictWord);
			}
		}
		printHelp(max, anagrams, word, newDictionary);
	}
	
	// pre:  if max is equal to 0 or if the number of anagrams found are less than or equal
	//		 to max and the word formed by subtracting the word from the dictionary from the
	//		 given word yields an empty string
	// post: recursively finds combinations of given number of words that have the same
	//		 letters as the given string. It finds all possible combinations if max is
	//		 given as 0.
	// parameters needed:
	//	s: the word of which anagrams need to be found
	//	max: the maximum number of words in an anagram
	//  anagram: the list of all the anagrams
	//  word: the letter inventory of the given word s
	//  newDictionary: the pruned version of the dictionary
	private void printHelp(int max, List<String> anagrams, LetterInventory word,
	List<String> newDictionary) {
		if (word.isEmpty()) {
			System.out.println(anagrams);
		} else if(anagrams.size() < max || max <= 0){
			for(String currentWord: newDictionary) {
				LetterInventory currentWordInv = dictionaryInventory.get(currentWord);
				LetterInventory newWord = word.subtract(currentWordInv);
				if (newWord != null) {
					anagrams.add(currentWord);
					printHelp(max, anagrams, newWord, newDictionary);
					anagrams.remove(anagrams.size() - 1);
				}
			}
		}
	}
}