package com.malonbc.twitterbackendservice.repository;

import com.malonbc.twitterbackendservice.entity.CommentEntity;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CommentEntityRepository extends JpaRepository<CommentEntity, String> {
    List<CommentEntity> findAllByTweetRef(String tweetRef, Sort sort);
}
