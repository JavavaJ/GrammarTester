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
