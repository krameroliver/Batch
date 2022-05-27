package com.poeticdrunkencat.batch.model;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.biojava.nbio.core.sequence.DNASequence;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;

import javax.persistence.GeneratedValue;

@Data
@Document
public class Genom {

    @GeneratedValue
    @Id
    String id;

    @Indexed(unique=true)
    @NotNull
    String GenomID;

    String Domain;

    @NotNull
    String Sequence;

    Double GC_content;

    Double AT_content;
    
    String domain;

    String Spezies;

    public Genom(String GenomID, String sequence,String domain,String spezies) {
        this.GenomID = GenomID;
        Sequence = sequence;
        GC_content = getGCContent(String.valueOf(sequence));
        AT_content = getATContent(String.valueOf(sequence));
        this.Domain = domain;
        this.Spezies = spezies;
    }

    public Genom(String GenomID, DNASequence sequence,String domain,String spezies) {
        this.GenomID = GenomID.toString().toUpperCase();
        Sequence = sequence.toString().toUpperCase();
        GC_content = getGCContent(String.valueOf(sequence));
        AT_content = getATContent(String.valueOf(sequence));
        this.Domain = domain;
        this.Spezies = spezies;

    }

    public Genom() {
    }

    public String csvRow(String sep){
        String s = this.GenomID+sep+this.Sequence+sep+this.GC_content+sep+this.AT_content;
        return s;
    }

    public String getHeader(String sep){
        String s = "GenomID"+sep+"Sequence"+sep+"GC_content"+sep+"AT_content"+System.lineSeparator();
        return s;
    }

    public String toJsonString(){
        String s = "{" +System.lineSeparator()+
                "\tID:" + this.GenomID+","+System.lineSeparator()+
                "\tSequence:" + this.Sequence+","+System.lineSeparator()+
                "\tGC_CONTENT:" + this.GC_content+","+System.lineSeparator()+
                "\tAT_CONTENT:" + this.AT_content+System.lineSeparator()+
                "}";

        return s;
    }

    private double getATContent(String seq) {
        Double erg = 0.0;
        int gc = 0;
        char[] entry = seq.toCharArray();
        for (int pos = 1; pos < entry.length; ++pos) {
            if (entry[pos] == 'C' || entry[pos] == 'G') {
                ++gc;
            }
        }
        erg = 1 - ((gc * 100.0) / entry.length) / 100;
        return erg;
    }

    private double getGCContent(String seq) {
        Double erg = 0.0;
        int gc = 0;
        char[] entry = seq.toCharArray();
        for (int pos = 1; pos < entry.length; ++pos) {
            if (entry[pos] == 'C' || entry[pos] == 'G') {
                ++gc;
            }
        }
        erg = ((gc * 100.0) / entry.length) / 100;
        return erg;
    }


}
