// Name: Saasha Mor
// Date: 01/11/2018
// Section: AO
// TA: Benjamin Macmillan

// Class LetterInventory can be used to store the count of each letter in a string

public class LetterInventory {
	private int[] inventory; // count of each alphabet
	private int size;		 // current number of characters in the list

	public static final int DEFAULT_CAPACITY = 26;

	// post: constructs a list of counts of each alphabet present in the string
	// 		 It isn't case sensitive and it ignores all elements that aren't letters
	// parameters needed: 
	//		data - type String 
	public LetterInventory(String data) {
      	inventory = new int[DEFAULT_CAPACITY];
      	char currentLetter;
		data = data.toLowerCase();
		for(int i = 0; i < data.length(); i++) {
			currentLetter = data.charAt(i);
			if(isLetter(currentLetter)) {
				inventory[currentLetter -'a']++;
				size++;
			}
		}
	}

	// pre: (a or A) <= letter <= (z or Z )(throws IllegalArgumentException if not)
	// post: returns  the count of the given character in the inventory
	// parameters: 
	//		letter - type char
	public int get(char letter) {
		isValid(letter);
		return inventory[Character.toLowerCase(letter) - 'a'];
	}

	// pre: (a or A) <= letter <= (z or Z) (throws IllegalArgumentException if not)
	// post: sets the count of the given letter to the value given
	// parameters: 
	//		letter - type char
	//		value - type int
	public void set(char letter, int value) {
		isValid(letter);
		letter = Character.toLowerCase(letter);
		size += (value - inventory[letter - 'a']);
		inventory[letter - 'a'] =  value;
	}

	// post: returns the current number of characters in the inventory 
	public int size() {
		return size;
	}

	// post: returns true if the size is zero
	//		 false otherwise
	public boolean isEmpty() {
		return (size == 0);
	}

	// post: creates a ordered, bracketed list of each letter appearing
	// 		 as many times as it occurs in the inventory
	public String toString() {
		String inventoryString = "[";
		for(int i = 0;  i < DEFAULT_CAPACITY; i++) {
			for(int j = 0; j < inventory[i]; j++) {
				inventoryString += (char)('a' + i);
			}
		}
      	inventoryString += "]";
      	return inventoryString;
	}
   
  	// post: creates a new LetterInventory by adding the number of
  	// 		 occurences of each letter from this LetterInventory and 
  	//		 the other given LetterInventory
  	// parameters needed:
  	//		other - type LetterInventory
	public LetterInventory add(LetterInventory other) {
		LetterInventory result = new LetterInventory("");
		char currentLetter;
		for(int i = 0; i < DEFAULT_CAPACITY; i++) {
			currentLetter = (char) ('a' + i);
			result.set(currentLetter, other.get(currentLetter) + this.get(currentLetter));
		}
		return result;
	}

  	// pre: the resulting subtraction must be non-negative
  	//		returns null if it does
  	// post: creates a new LetterInventory by subtracting the number of
  	// 		 occurences of each letter of other given LetterInventory from
  	// 		 the this LetterInventory.
  	// parameters needed: 
  	//		other - type LetterInventory
	public LetterInventory subtract(LetterInventory other) {
      	LetterInventory result = new LetterInventory("");
      	char currentLetter;
		for(char i = 0;  i < DEFAULT_CAPACITY; i++) {
			currentLetter = (char) ('a' + i);
			int num = this.get(currentLetter) - other.get(currentLetter);
			if(num < 0) {
				return null;
			}
			result.set(currentLetter, num);
		}
      	return result;
	}

	// post: returns true if the given character is a letter
	//		 false otherwise
	// parameters needed: 
	// 		givenCharacter - type char
	private boolean isLetter(char givenCharacter) {
    	givenCharacter = Character.toLowerCase(givenCharacter);
		return (givenCharacter >= 'a' && givenCharacter <= 'z');
	}

	// pre: (a or A) <= letter <= (z or Z )(throws IllegalArgumentException if not)
	// parameters needed: 
	//		letter - type char
	private void isValid(char letter) {
		if(!isLetter(letter)) {
			throw new IllegalArgumentException("letter: " + letter);
		} 
	}
}