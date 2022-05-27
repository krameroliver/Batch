package com.poeticdrunkencat.batch.utils;

public class Domains {

    public String domainMapper(String path){

        if(path.contains("Virus")){
            return "Virus";
        }else if (path.contains("Bacteria")){
            return "Bacteria";
        }

        return null;
    }

}
