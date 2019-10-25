/** The enum is an enum to build JavaFX Choice Box with.
 * 
 */

package tagger;

import question.topics.TagType;

public enum MixType implements TagType {
    
    PASSIVE {
        // return a String which is used Choice Box name
        @Override
        public String toString() {
            return "Passive";
        }
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "passive";
        }
    },
    
    
    PHRASAL_VERBS {
        // return a String which is used Choice Box name
        @Override
        public String toString() {
            return "Phrasal verbs";
        }
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "phrasal_verb";
        }
    }, 
    
    UNDEFINED {
        // return a String which is used Choice Box name
        @Override
        public String toString() {
            return "Undefined";
        }
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "undefined";
        }
    };
    
    // public abstract String getTag();
    
    
}