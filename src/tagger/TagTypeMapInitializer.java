/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tagger;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ALEXXX
 */
public class TagTypeMapInitializer {
    
    static Map<String, TagType> tagTypeMap = new HashMap<>();
    static {
        // ========== A1_LEVEL ===========
        tagTypeMap.put("articles", A1_LEVEL.ARTICLES);
        tagTypeMap.put("can_could", A1_LEVEL.CAN_COULD);
        tagTypeMap.put("conj", A1_LEVEL.CONJUNCTIONS);
        tagTypeMap.put("like_doing", A1_LEVEL.LIKE_DOING);
        tagTypeMap.put("much_many", A1_LEVEL.MUCH_MANY);
        tagTypeMap.put("numbers", A1_LEVEL.NUMBERS);
        tagTypeMap.put("obj_pron", A1_LEVEL.OBJ_PRONOUN);
        tagTypeMap.put("ordinals", A1_LEVEL.ORDINALS);
        tagTypeMap.put("past_simple", A1_LEVEL.PAST_SIMPLE);
        tagTypeMap.put("possessives", A1_LEVEL.POSSESSIVES);
        tagTypeMap.put("preposition", A1_LEVEL.PREPOSITIONS);
        tagTypeMap.put("present_simple", A1_LEVEL.PRESENT_SIMPLE);
        tagTypeMap.put("question_word", A1_LEVEL.QUESTION_WORDS);
        tagTypeMap.put("some_any", A1_LEVEL.SOME_ANY);
        tagTypeMap.put("there_is", A1_LEVEL.THERE_IS);
        tagTypeMap.put("these_those", A1_LEVEL.THESE_THOSE);
        tagTypeMap.put("time_word", A1_LEVEL.TIME_WORDS);
        tagTypeMap.put("to_be", A1_LEVEL.TO_BE);
        tagTypeMap.put("voc", A1_LEVEL.VOCABULARY);
        tagTypeMap.put("was_were", A1_LEVEL.WAS_WERE);
        tagTypeMap.put("would_like", A1_LEVEL.WOULD_LIKE);
        
        // ========== A2_LEVEL ===========
        
        tagTypeMap.put("is_going_to", A2_LEVEL.IS_GOING_TO);
        tagTypeMap.put("present_perfect", A2_LEVEL.PRESENT_PERFECT);
        
        // ========== MixType ===========
        
        tagTypeMap.put("passive", MixType.PASSIVE);
        tagTypeMap.put("phrasal_verb", MixType.PHRASAL_VERBS);
        tagTypeMap.put("undefined", MixType.UNDEFINED);
        
    }
    
}
