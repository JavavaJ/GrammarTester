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
public interface TagType {
    
    @Override
    public String toString();
    
    public String getTag();
    
    public static TagType getTagTypeByTag(String str) {
        TagType tagType = null;
        switch (str) {                       
            // ========== A1_LEVEL ===========
            case "present_simple":
                tagType = A1_LEVEL.PRESENT_SIMPLE;
                break;
            case "past_simple":
                tagType = A1_LEVEL.PAST_SIMPLE;
                break;
            case "was_were":
                tagType = A1_LEVEL.WAS_WERE;
                break;
            case "can_could":
                tagType = A1_LEVEL.CAN_COULD;
                break;
            case "possessives":
                tagType = A1_LEVEL.POSSESSIVES;
                break;
            case "obj_pron":
                tagType = A1_LEVEL.OBJ_PRONOUN;
                break;
            case "time_word":
                tagType = A1_LEVEL.TIME_WORDS;
                break;
            case "question_word":
                tagType = A1_LEVEL.QUESTION_WORDS;
                break;
            case "voc":
                tagType = A1_LEVEL.VOCABULARY;
                break;
            case "articles":
                tagType = A1_LEVEL.ARTICLES;
                break;
            case "there_is":
                tagType = A1_LEVEL.THERE_IS;
                break;
            case "some_any":
                tagType = A1_LEVEL.SOME_ANY;
                break;
            case "much_many":
                tagType = A1_LEVEL.MUCH_MANY;
                break;
            case "these_those":
                tagType = A1_LEVEL.THESE_THOSE;
                break;
            case "conj":
                tagType = A1_LEVEL.CONJUNCTIONS;
                break;
            case "preposition":
                tagType = A1_LEVEL.PREPOSITIONS;
                break;
            case "to_be":
                tagType = A1_LEVEL.TO_BE;
                break;
            case "ordinals":
                tagType = A1_LEVEL.ORDINALS;
                break;
            case "numbers":
                tagType = A1_LEVEL.NUMBERS;
                break;
            case "would_like":
                tagType = A1_LEVEL.WOULD_LIKE;
                break;
            case "like_doing":
                tagType = A1_LEVEL.LIKE_DOING;
                break;                            
            // ========== A2_LEVEL ===========
            case "present_perfect":
                tagType = A2_LEVEL.PRESENT_PERFECT;
                break;
            case "is_going_to":
                tagType = A2_LEVEL.IS_GOING_TO;
                break;
            // ========== MixType ===========
            case "passive":
                tagType = MixType.PASSIVE;
                break;
            case "phrasal_verb":
                tagType = MixType.PHRASAL_VERBS;
                break;
            case "undefined":
                tagType = MixType.UNDEFINED;
                break;            
        }
        return tagType;
    };
}
