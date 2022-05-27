package com.poeticdrunkencat.batch.model;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Data
@Document
public class GenomDuplicates {

    @Id
    String  id;

    @NotNull
    String GenomID;
    @NotNull
    String dnow;


    public GenomDuplicates(String genomID) {
        GenomID = genomID;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.dnow =format.format(timestamp);
    }
}
