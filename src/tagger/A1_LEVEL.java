/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tagger;

/**
 *
 * @author ALEXXX
 */
public enum A1_LEVEL implements TagType { 
    
    PRESENT_SIMPLE {
        
        @Override
        public String toString() {
            return "Present Simple";
        }
        
        @Override
        public String getTag() {
            return "present_simple";
        }            
       
    },
    
    PAST_SIMPLE {
        
        @Override
        public String toString() {
            return "Past Simple";
        }
        
        @Override
        public String getTag() {
            return "past_simple";
        }  
        
    },
    
    POSSESSIVES {
        
        @Override
        public String toString() {
            return "Possessives";
        }
        
        @Override
        public String getTag() {
            return "possessives";
        }  
        
    },
    
    OBJ_PRONOUN {
        
        @Override
        public String toString() {
            return "Obj Pronoun";
        }
        
        @Override
        public String getTag() {
            return "obj_pron";
        }  
        
    },
    
    TIME_WORDS {
        // return a String which is used Choice Box name
        @Override
        public String toString() {
            return "Time words";
        }
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "time_word";
        }
    },
    
    QUESTION_WORDS {
        // return a String which is used Choice Box name
        @Override
        public String toString() {
            return "Question words";
        }
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "question_word";
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
    
    LIKE_DOING {
        
        @Override
        public String toString() {
            return "Like Doing";
        }
        
        @Override
        public String getTag() {
            return "like_doing";
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
    };
    
}
