package tagger;

public class EnumTest {
    public static void main(String[] args) {
        TagType tag = TagType.VOCABULARY;
        System.out.println(tag);
        System.out.println("Printed");
        
        TagType tagNull = null;
        
        System.out.println(tagNull == null);
    }
    
    

}