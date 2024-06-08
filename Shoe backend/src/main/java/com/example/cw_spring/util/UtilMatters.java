package com.example.cw_spring.util;

import java.util.Base64;

public class UtilMatters {
    public static String convertBase64(String base64) {
        return Base64.getEncoder().encodeToString(base64.getBytes());
    }
}
