package com.malonbc.twitterbackendservice.service;

import com.malonbc.twitterbackendservice.model.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment) throws Exception;

    List<Comment> getComments(String tweetId);
}
