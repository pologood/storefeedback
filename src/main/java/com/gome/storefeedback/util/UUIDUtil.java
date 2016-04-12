package com.gome.storefeedback.util;

import java.util.UUID;

/**
 * @author Zhang.Mingji
 * @date 2014-1-22上午10:41:49
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class UUIDUtil {

    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
    
    public static void main(String[] args) {
        System.out.println(getUUID());
    }
}
