/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tagger;

import question.topics.A1_LEVEL;
import question.topics.A2_LEVEL;
import question.topics.TagType;

/**
 *
 * @author ALEXXX
 */
public class TagSortTest {
    
    public static void main(String[] args) {
        
        TagType[] tagEnums  = new TagType[2];
        int count = 0;
        for (A1_LEVEL a1 : A1_LEVEL.values()) {
            tagEnums[count] = (TagType) a1;
            count++;
        }
        
        System.out.println("A1 Level");
        for (TagType tagSort : tagEnums) {
            System.out.println(tagSort);
            System.out.println(tagSort.getClass());
            
        }
        
        System.out.println("==============");
        
        System.out.println("A2 Level");
        
        
        
        count = 0;
        for (A2_LEVEL a2 : A2_LEVEL.values()) {
            tagEnums[count] = (TagType) a2;
            count++;
        }
        
        for (TagType tagSort : tagEnums) {
            System.out.println(tagSort);
            System.out.println(tagSort.getClass());
        }
        
        
        A1_LEVEL[] a1Array = new A1_LEVEL[2];
        a1Array[0] = A1_LEVEL.PAST_SIMPLE;
        a1Array[1] = A1_LEVEL.PRESENT_SIMPLE;
        
        for (TagType tagSort : a1Array) {
            System.out.println(tagSort);
            System.out.println(tagSort.getClass());
        }
        
        
        
        tagEnums[0] = A1_LEVEL.PAST_SIMPLE;
        
        tagEnums = A2_LEVEL.values();
        for (TagType tagSort : tagEnums) {
            System.out.println(tagSort);
            System.out.println(tagSort.getClass());
        }
        
    }
    
}
