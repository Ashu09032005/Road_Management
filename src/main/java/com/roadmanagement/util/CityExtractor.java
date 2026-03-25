package com.roadmanagement.util;

public class CityExtractor {

    public static String extract(String title) {

        title = title.toLowerCase();

        if(title.contains("mumbai")) return "MUMBAI";
        if(title.contains("chennai")) return "CHENNAI";
        if(title.contains("bihar")) return "BIHAR";
        if(title.contains("surat")) return "SURAT";
        if(title.contains("uttarakhand")) return "UTTARAKHAND";
        if(title.contains("gujarat")) return "GUJARAT";
        if(title.contains("andhra")) return "ANDHRA";
        if(title.contains("bengal")) return "BENGAL";

        return "OTHER";
    }
}