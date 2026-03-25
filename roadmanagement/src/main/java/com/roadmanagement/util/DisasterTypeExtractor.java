package com.roadmanagement.util;

public class DisasterTypeExtractor {

    public static String extract(String title){

        String t = title.toLowerCase();

        if(t.contains("cyclone")) return "Cyclone";
        if(t.contains("flood")) return "Flood";
        if(t.contains("earthquake")) return "Earthquake";
        if(t.contains("landslide")) return "Landslide";
        if(t.contains("fire")) return "Fire";
        if(t.contains("stampede")) return "Stampede";

        return "Other";
    }
}