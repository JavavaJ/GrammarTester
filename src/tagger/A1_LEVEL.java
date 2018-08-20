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
    
    WAS_WERE {
        
        @Override
        public String toString() {
            return "Was / were";
        }
        
        @Override
        public String getTag() {
            return "was_were";
        }  
        
    },
    
    CAN_COULD {
        
        @Override
        public String toString() {
            return "Can / could";
        }
        
        @Override
        public String getTag() {
            return "can_could";
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
    
    ARTICLES {
        
        @Override
        public String toString() {
            return "Articles";
        }
        
        @Override
        public String getTag() {
            return "articles";
        }  
        
    },
    
    THERE_IS {
        
        @Override
        public String toString() {
            return "There is";
        }
        
        @Override
        public String getTag() {
            return "there_is";
        }  
        
    },
    
    SOME_ANY {
        
        @Override
        public String toString() {
            return "Some / any";
        }
        
        @Override
        public String getTag() {
            return "some_any";
        }  
        
    },    
        
    MUCH_MANY {
        
        @Override
        public String toString() {
            return "Much / many";
        }
        
        @Override
        public String getTag() {
            return "much_many";
        }  
        
    },
    
    THESE_THOSE {
        
        @Override
        public String toString() {
            return "These / those";
        }
        
        @Override
        public String getTag() {
            return "these_those";
        }  
        
    },
    
    CONJUNCTIONS {
        
        @Override
        public String toString() {
            return "Conjunctions";
        }
        
        @Override
        public String getTag() {
            return "conj";
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
    
    TO_BE {
        // return a String which is used Choice Box name
        @Override
        public String toString() {
            return "To Be";
        }
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "to_be";
        }
    },
    
    ORDINALS {
        // return a String which is used Choice Box name
        @Override
        public String toString() {
            return "Ordinals";
        }
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "ordinals";
        }
    },
    
    NUMBERS {
        // return a String which is used Choice Box name
        @Override
        public String toString() {
            return "Numbers";
        }
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "numbers";
        }
    },
    
    WOULD_LIKE {
        // return a String which is used Choice Box name
        @Override
        public String toString() {
            return "Would like";
        }
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "would_like";
        }
    },
    
    LIKE_DOING {
        // return a String which is used Choice Box name
        @Override
        public String toString() {
            return "Like doing";
        }
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "like_doing";
        }
    };
    
}
