/** The class is a test of SQLTagWriter 
 * 
 */

package tagger;

public class SQLTagWriterTest {
    public static void main(String[] args) {
        TagType tag1 = TagType.PASSIVE;
        TagType tag2 = TagType.PHRASAL_VERBS;
        TagType tag3 = TagType.PREPOSITIONS;
        TagType tag4 = TagType.PRESENT_PERFECT;
        TagType tag5 = TagType.TIME_WORDS;
        TagType tag6 = TagType.UNDEFINED;
        TagType tag7 = TagType.VOCABULARY;
        
        TagType[] tags = new TagType[7];
        
        tags[0] = tag1;
        tags[1] = tag2;
        tags[2] = tag3;
        tags[3] = tag4;
        tags[4] = tag5;
        tags[5] = tag6;
        tags[6] = tag7;
                
        
        SQLTagWriter.writeTags(tags);
    }
}