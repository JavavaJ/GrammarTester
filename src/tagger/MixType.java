/** The enum is an enum to build JavaFX Choice Box with.
 * 
 */

package tagger;

public enum MixType implements TagType {
    PRESENT_PERFECT {
        // return a String which is used Choice Box name
        @Override
        public String toString() {
            return "Present Perfect";
        }         
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "present_perfect";
        }
        
    }, 
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
    VOCABULARY {
        // return a String which is used Choice Box name
        @Override
        public String toString() {
            return "Vocabulary";
        }
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "voc";
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
    PREPOSITIONS {
        // return a String which is used Choice Box name
        @Override
        public String toString() {
            return "Prepositions";
        }
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "preposition";
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
    
    public abstract String getTag();
    
    
}