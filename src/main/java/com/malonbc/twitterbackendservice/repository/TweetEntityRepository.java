package com.malonbc.twitterbackendservice.repository;

import com.malonbc.twitterbackendservice.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetEntityRepository extends JpaRepository<TweetEntity, String> {
}
