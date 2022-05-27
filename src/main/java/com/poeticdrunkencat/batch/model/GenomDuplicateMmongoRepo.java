package com.poeticdrunkencat.batch.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenomDuplicateMmongoRepo extends MongoRepository<GenomDuplicates,String> {
}
