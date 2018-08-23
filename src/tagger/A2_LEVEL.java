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
public enum A2_LEVEL implements TagType {
    
    ADVERBS {
        
        @Override
        public String toString() {
            return "Adverbs";
        }         
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "adverb";
        }
        
    },
    
    ADJECTIVES_ED_ING {
        
        @Override
        public String toString() {
            return "Adj ed / ing";
        }         
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "adj_ed_ing";
        }
        
    },
    
    COMPARATIVES {
        
        @Override
        public String toString() {
            return "Comparatives";
        }         
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "compar";
        }
        
    },
    
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
    
    IS_GOING_TO {
        
        @Override
        public String toString() {
            return "Is going to";
        }         
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "is_going_to";
        }
        
    },
    
    MY_MINE {
        
        @Override
        public String toString() {
            return "My / mine";
        }         
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "my_mine";
        }
        
    }, 
    
    INFINITIVE {
        
        @Override
        public String toString() {
            return "Infinitive";
        }         
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "infin";
        }
        
    }, 
    
    HAVE_GOT {
        
        @Override
        public String toString() {
            return "Have got";
        }         
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "have_got";
        }
        
    }, 
    
    RELATIVE_PRONOUNS {
        
        @Override
        public String toString() {
            return "Relatives Pronouns";
        }         
        
        // returns a tag which is used to tag questions in SQL database
        @Override
        public String getTag() {
            return "rel_prons";
        }
        
    };
    
}
