package csi403;

import java.util.*;

/*Class that is used for Json Serializer to be able to recognize
*Json with a field called "inList" that contains an arrayList of MyString (which are just strings)
*/
public class InList {
	
	private ArrayList<MyString> inList = new ArrayList<MyString>();
	
	//Empty Constructor
	public InList() {
		
	}
	
	//Mutator
	public void setInList(ArrayList<MyString> list) {
		inList = list;
	}
	
	//Accessor
	public ArrayList<MyString> getInList() {
		return inList;
	}
}