package csi403;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Vector;
import java.util.List; 

public class JsonSerializer {

    public JsonSerializer() {
    }

    public String serialize(Object obj) {
        String str = null; 
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(Include.NON_NULL);
            str = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            str = "<error>"; 
        }
        return str;
    }


   /* // test app 
    public static void main(String[] args) {
        JsonSerializer serializer = new JsonSerializer();

        System.out.println("************************************");

        Pet pet = new Pet();
        pet.setName("Fido");
        pet.setSpecies("Dog");
        Person owner = new Person();
        owner.setLastName("Smith");
        owner.setFirstName("Mary");
        pet.setOwner(owner);
        System.out.println(serializer.serialize(pet));

        System.out.println("************************************");
                
        Person marysFriend = new Person();
        marysFriend.setLastName("Crawford");
        marysFriend.setFirstName("Joan");
        List<Person> friends = new Vector<Person>();
        friends.add(marysFriend); 
        owner.setFriends(friends);
        System.out.println(serializer.serialize(pet));

        System.out.println("************************************");

        Student goodStudent = new Student();
        goodStudent.setLastName("Perez");
        goodStudent.setFirstName("Susie");
        int grades[] = { 100, 95, 92, 89, 99 }; 
        goodStudent.setGrades(grades); 
        System.out.println(serializer.serialize(goodStudent)); 
        
        System.out.println("************************************");
    }
    */
}

