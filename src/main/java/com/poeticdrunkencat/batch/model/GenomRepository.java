package com.poeticdrunkencat.batch.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collections;
import java.util.List;

public interface GenomRepository extends MongoRepository<Genom,String> {
    List<Genom> findByGenomID(String GenomID);
}

