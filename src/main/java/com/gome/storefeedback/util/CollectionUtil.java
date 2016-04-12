package com.gome.storefeedback.util;


import java.util.Collection;

public class CollectionUtil {
    /**
     * 判断集合对象是否为空或者集合内容为空
     * 
     * @param Collection
     * @return boolean
     * @author wagbiao
     */
    public static boolean isEmpty(Collection collection) {
        if (null == collection || collection.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
