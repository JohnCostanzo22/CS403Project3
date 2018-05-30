package csi403;

//Class that holds a string value and overrides the hashcode and equals function
public class MyString {
	
	private String value;
	
	//Constructor that takes and sets the String value
	public MyString(String value) {
		this.value = value;
	}
	
	//Accessor that returns the String value
	public String getValue() {
		return value;
	}
	
	//Mutator to set the String value
	public String toString() {
		return value;
	}
	
	//returns whether another MyString's value is equal to this value
	//or the hashCodes are equal. This is only intended for use of hashing
	//For any actual comparisons just compare the value fields of the MyStrings
	public boolean equals(Object other) {
		boolean equal = false;
		try{
			if(this.value.equals( ((MyString)other).getValue()) || this.hashCode() == ((MyString) other).hashCode())
				equal = true;
		} catch(Exception e) {
			
		}
		return equal;
	}
	
	//Generates hashcode value for object by suming the ascii values of the String value
	public int hashCode() {
		String lowerCase = value.toLowerCase();
		int total = 0;
		for(int i = 0; i < lowerCase.length(); i++) {
			total += lowerCase.charAt(i);
		}
		return total;
	}
}