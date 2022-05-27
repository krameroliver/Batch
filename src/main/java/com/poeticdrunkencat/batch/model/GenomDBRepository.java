package com.poeticdrunkencat.batch.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface GenomDBRepository extends CrudRepository<Genom,String> {

}

