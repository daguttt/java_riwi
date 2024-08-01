package org.example.utils;

public class Prompts {

    public static String getPromptMessage(Object obj, String prompt) {
        return String.format("""
                %s
                
                %s
                """, obj, prompt);
    }
}
