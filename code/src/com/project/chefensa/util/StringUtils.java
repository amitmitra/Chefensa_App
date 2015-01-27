package com.project.chefensa.util;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;

import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by harshwardhan.c on 26/01/15.
 */
public class StringUtils {

    public static boolean isValidIndianPin(String pin){
        if(pin == null || pin.length() != 6){
            return false;
        }
        if(pin.matches("^[1-9][0-9]+")){
            return true;
        }
        else{
            return false;
        }
    }

    public static String arrayToCSV(String... params){
        StringBuilder result = new StringBuilder();
        for(String string : params) {
            result.append(string);
            result.append(",");
        }
        return result.length() > 0 ? result.substring(0, result.length() - 1): "";
    }

    public static boolean isNullOrEmpty(String str){
        return str == null || str.length() == 0 || str.equalsIgnoreCase("null");
    }

    public static <T> boolean isNullOrEmpty(ArrayList<T> list){
        return list == null || list.size() == 0;
    }

    public static <T> boolean isNullOrEmpty(List<T> list){
        return list == null || list.size() == 0;
    }

    public static <T,Y> boolean isNullOrEmpty(Map<T,Y> map){
        return map == null || map.size() == 0;
    }

    public static <T> boolean isNull(T object){
        return object == null;
    }


    public static String trim(String str, String pattern){
        while(str.endsWith(pattern)){
            str = str.substring(0, str.length() - pattern.length());
        }
        while(str.startsWith(pattern)){
            str = str.substring(pattern.length(), str.length());
        }
        return str;
    }

    public static boolean isHttps(URL url){
        return url.toString().startsWith("https");
    }

    public static boolean isHttps(String url){
        return url.startsWith("https");
    }

    public static StringBuilder join(ArrayList<String> list, String delimiter){
        ArrayList<String> copiedList = list;
        StringBuilder stringBuilder = new StringBuilder();
        if(copiedList != null){
            for (String string : copiedList) {
                stringBuilder.append(string).append(delimiter);
            }
        }
        return stringBuilder;
    }

    public static Map<String, String> splitIntoMap(String text, String delimiter, String separator){
        HashMap<String, String> ret = new HashMap<String, String>();

        String decodedString = "";
        try {
            decodedString = URLDecoder.decode(text, "UTF-8");
        } catch (Exception e) {}

        String[] split = decodedString.split(delimiter);
        for (String string : split) {
            if(string.contains(separator)){
                String[] element = string.split(separator);
                if(element.length == 2)
                    ret.put(element[0], element[1]);
            }
        }
        return ret;
    }

    public static SpannableString getHyperLinkedText(String text){

        SpannableString ss = new SpannableString (text);
        ss.setSpan (new URLSpan(""), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ss;
    }

}
