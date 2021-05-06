package com.springboot.history.app.repository;

import com.springboot.history.app.model.entity.DeployHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuildHistoryRepository extends MongoRepository<DeployHistory, String> {
}
