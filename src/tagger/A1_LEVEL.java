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
        
    };
    
}
