package csi403; 

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.util.List; 

public class JsonClassDiscerner {

    public JsonClassDiscerner() {
    }

    public InList discern(String jsonStr) {
        ObjectMapper mapper = new ObjectMapper();
        // mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		InList inList = null;
		
        try { 
            inList = mapper.readValue(jsonStr, InList.class);
            return inList; 
        }
        catch (Exception e) {
            // e.printStackTrace(); 
        }
        
        return inList; 
    }


    /*// test app 
    public static void main(String[] args) {
        String msg;
        JsonClassDiscerner discerner = new JsonClassDiscerner();
        System.out.println("************************************"); 
        
        msg = "{ \"name\" : \"Fido\", \"species\" : \"Dog\" }";
        System.out.println(msg);
        System.out.println(discerner.discern(msg));

        System.out.println("************************************");
        
        msg = "{ \"name\" : \"Fido\", \"lastName\" : \"Dog\" }";
        System.out.println(msg);
        System.out.println(discerner.discern(msg));

        System.out.println("************************************");
                
        msg = "{ \"lastName\" : \"Smith\", \"firstName\" : \"Mary\" }";
        System.out.println(msg);
        System.out.println(discerner.discern(msg));

        System.out.println("************************************");
                
        msg = "{ \"name\" : \"Fido\", \"species\" : \"Dog\", " +
            "\"owner\" : " + "{ \"lastName\" : \"Smith\", \"firstName\" : \"Mary\" }" + " }";
        System.out.println(msg); 
        System.out.println(discerner.discern(msg));

        System.out.println("************************************");

        msg = "{ \"name\" : \"Fido\", \"species\" : \"Dog\", " +
            "\"person\" : " + "{ \"lastName\" : \"Smith\", \"firstName\" : \"Mary\" }" + " }";
        System.out.println(msg); 
        System.out.println(discerner.discern(msg));

        System.out.println("************************************");
    }*/
}
