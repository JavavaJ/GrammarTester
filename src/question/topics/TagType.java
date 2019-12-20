/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question.topics;

import java.util.Map;

/**
 *
 * @author ALEXXX
 */
public interface TagType {
     
    Map<String, TagType> TAG_TYPE_MAP = TagTypeMapInitializer.getTagsMap();
    
    @Override
    String toString();
    
    String getTag();
    
    static TagType getTagTypeByTag(String str) {
        return TAG_TYPE_MAP.get(str);
    };
}
