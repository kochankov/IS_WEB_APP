package com.example.IS_WEB_APP.utils;

public class ValidationUtil {

    public static boolean isValidEmailAddress(String email) {
        return patternMatcher(email, "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    }

    public static boolean isDigit(String input) {
        return patternMatcher(input, "^[0-9]+$");
    }

    public static boolean isCyrillicOrLatin(String input) {
        return patternMatcher(input, "^[a-zA-Z_\\p{InCyrillic}\\s-]+$");
    }

    private static boolean patternMatcher(String inputString, String pattern){
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(inputString);
        return m.matches();
    }

}
