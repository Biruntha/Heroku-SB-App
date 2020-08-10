package com.biruntha.security.basicauth.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.biruntha.security.basicauth.models.Book;

@Repository
public class BookRepositoryCustom {
//	@Autowired
    MongoTemplate mongoTemplate;
 
    public Integer getMaxBookId() {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "id"));
		query.limit(1);
		Book maxObject = mongoTemplate.findOne(query, Book.class);
		if (maxObject == null) {
		    return 0;
		}
		return maxObject.getId();
    }

}
