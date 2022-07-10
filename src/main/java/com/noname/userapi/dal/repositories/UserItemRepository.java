package com.noname.userapi.dal.repositories;

import com.noname.userapi.dal.documents.UserItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserItemRepository extends MongoRepository<UserItem, String> {
}
