package com.poeticdrunkencat.batch;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.mongodb.MongoWriteException;
import com.poeticdrunkencat.batch.model.*;
import com.poeticdrunkencat.batch.utils.ColorCodes;
import com.poeticdrunkencat.batch.utils.Domains;
import com.poeticdrunkencat.batch.utils.FindFiles;
import com.poeticdrunkencat.batch.utils.ParsFasta;
import com.sun.org.omg.CORBA.AttrDescriptionSeqHelper;
import com.zavtech.morpheus.frame.DataFrame;
import org.biojava.nbio.core.sequence.DNASequence;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jpa.repository.Query;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class BatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(GenomRepository repository,GenomDuplicateMmongoRepo gdr) {
        return args -> {
            ColorCodes c = new ColorCodes();
            String rootpath = "/Volumes/UNTITLED/GenomData/Genom/VIRUS";


            //String rootpath = System.getenv("DATA_ROOT");
            //String auth = System.getenv("USER_AUTH");
            FindFiles ff = new FindFiles(rootpath);
            List<File> f = ff.findFasta().stream().sorted().collect(Collectors.toList());


            ParsFasta pf = new ParsFasta();
            Domains dd = new Domains();
            String spezies;
            for (File file : f) {
                spezies = file.getAbsolutePath().split("/")[7];
                System.out.println(c.ANSI_BLUE +spezies);
                //System.out.println(c.ANSI_GREEN + "file: " + file);
                Map<String, DNASequence> tmp = pf.getGenom(file);
                try{
                for (Map.Entry<String, DNASequence> entry : tmp.entrySet()) {
                    String id = entry.getKey().toString().toUpperCase();
                    //System.out.println("ID: "+id);
                    Genom g = new Genom(id, entry.getValue(),dd.domainMapper(file.getAbsolutePath()),spezies);
                    try {
                        repository.save(g);
                    }catch(Exception e){
                        GenomDuplicates gd = new GenomDuplicates(id);
                        gdr.insert(gd);
                    }
                }
                }catch (NullPointerException e){
                    continue;
                }


            }
        };
    }
/*

    @Bean
    CommandLineRunner runner(GenomDBRepository dbrepository){
        return args ->{
            ColorCodes c = new ColorCodes();
            String rootpath= "/Users/oliver/Projects/Privat/Spark/input";


            //String rootpath = System.getenv("DATA_ROOT");
            //String auth = System.getenv("USER_AUTH");
            FindFiles ff = new FindFiles(rootpath);
            List<File> f = ff.findFasta().stream().sorted().collect(Collectors.toList());   ;

            ParsFasta pf = new ParsFasta();

            for (File file : f) {
                System.out.println(c.ANSI_GREEN + "file: "+file);
                Map<String, DNASequence> tmp =pf.getGenom(file);
                for(Map.Entry<String, DNASequence> entry : tmp.entrySet()) {
                    Genom g = new Genom(entry.getKey().toString().toUpperCase(),entry.getValue());
                    dbrepository.save(g);
                }
            }
        };
    }*/
}
