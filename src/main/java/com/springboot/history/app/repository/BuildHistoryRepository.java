package com.springboot.history.app.repository;

import com.springboot.history.app.model.entity.BuildHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuildHistoryRepository extends MongoRepository<BuildHistory, String> {
}
