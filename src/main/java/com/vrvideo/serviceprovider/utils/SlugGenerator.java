package com.vrvideo.serviceprovider.utils;

public class SlugGenerator {
    public static String generate(String input) {
        return input
                .toLowerCase()
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace(" ", "-")
                .replaceAll("[^a-z-\\d]+", "");
    }
}
