package com.poeticdrunkencat.batch.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class FindFiles {

    String rootPath;

    public FindFiles() {
    }

    public FindFiles(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }


    public List<File> findFasta() {
        File dir = new File(this.rootPath);
        String[] extensions = new String[]{"fna", "fasta"};
        System.out.println("Getting all .fasta and .fna files in " + dir.getAbsolutePath()
                + " including those in subdirectories");
        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
        //for (File file : files) {
        //    System.out.println("file: " + file.getAbsolutePath());
        //}
    return files;
    }



}
