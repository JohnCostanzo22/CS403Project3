package csi403;

// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.json.*;

// Extend HttpServlet class
public class DiscernJsonService extends HttpServlet {

  // Standard servlet method 
    public void init() throws ServletException { 
        // Do any required initialization here - likely none
    }
    
    // Standard servlet method - we will handle a POST operation
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException { 
        doService(request, response); 
    }

    // Standard Servlet method
    public void destroy() { 
        // Do any required tear-down here, likely nothing.
    }

    // Standard servlet method - we will not respond to GET
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException { 
        // Set response content type and return an error message
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        // We can always create JSON by hand just concating a string.  
        out.println("{ 'message' : 'Use POST!'}");
    }
    
    // Our main worker method
	/* Takes in Json with a list of Strings
	* Then it adds it to a hashtable and returns 
	* Json with a list of a list of all hashtable collisions
	*/
    private void doService(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException { 
    
		PrintWriter out = response.getWriter();
		//handle any errors in receiving the Json and processing it
		try {
			//get the input
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			//Use a Stringbuffer to append multiple lines of input
			StringBuffer bufferedJson = new StringBuffer();
			String line = null;
			while((line = br.readLine()) != null) {
				bufferedJson.append(line);
			}
			String jsonStr = bufferedJson.toString();
        
			// Create inList object from the Json input
			InList inList = new JsonClassDiscerner().discern(jsonStr); 
			
			//Get the arrayList from inList
			ArrayList<MyString> myStringList= inList.getInList();
		
			//Hashtable of MyString (for the hashcode override) and a LinkedList for collisions
			Hashtable<MyString, LinkedList<String>> hashTable = new Hashtable<MyString, LinkedList<String>>(); 
		
			//For each MyString in the list
			for(MyString word: myStringList) {
				//If that hashcode value already exists then it is a collision
				if(hashTable.containsKey(word)) {
					LinkedList<String> tempLink = hashTable.get(word);
					boolean duplicate = false;
					
					//Test first if the String already exists in the hashTable
					for(String s: tempLink) {
						if(s.equals(word.getValue()))
							duplicate = true;
					}
					//if it isnt a duplicate then add it to the linkedList
					if(!duplicate)
						hashTable.get(word).add(word.getValue());
				} else {
					//not already in the hashtable so create a new LinkedList with the MyString to add to hashTable
					LinkedList<String> linkedList = new LinkedList<String>();
					linkedList.add(word.getValue());
					hashTable.put(word, linkedList);
				}
			}
        
			//everything is in the hashtable now
			//iterate through and get all collisions
			Set<MyString> keys = hashTable.keySet();
			//get an arrayList of String lists
			List<List<String>> outList = new ArrayList<List<String>>();
		
			for(MyString key: keys) {
				//get each LinkedList in hashtable
				LinkedList<String> tempLinked = hashTable.get(key);
				//if the linkedList size is > 1 then there must be collisions in this list
				if(tempLinked.size() > 1) {
					outList.add(tempLinked);
				}
			}
		
			//Format the output as Json Array of Json arrays
			JsonArrayBuilder outArrayBuilder = Json.createArrayBuilder();
			for(List<String> tempList: outList) {
				JsonArrayBuilder temp = Json.createArrayBuilder();
				//add all values in LinkedList to tempList
				for(String value: tempList) {
					temp.add(value);
				}
				//Add all tempLists to the outArray
				outArrayBuilder.add(temp);
			}
		
			// Set response content type to be JSON
			response.setContentType("application/json");
			//send back the outList of collisions
			out.println("{\"outList\": " + outArrayBuilder.build().toString() + "}"); 
		
		} catch(Exception e) {
			 response.setContentType("application/json");
			//An error occurred (probably bad Json) so send an error message
			out.println("{\"message\": " + "\"Malformed or invalid Json\"" + "}"); 
		}
    }
}

