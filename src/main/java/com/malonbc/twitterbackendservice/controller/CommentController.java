package com.malonbc.twitterbackendservice.controller;


import com.malonbc.twitterbackendservice.model.Comment;

import com.malonbc.twitterbackendservice.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private CommentService commentService;
    public CommentController(CommentService commentService) { this.commentService = commentService; }

    @PostMapping("api/addComment")
    public Comment addComment(@RequestBody Comment commentBody) throws Exception {
        Comment comment = Comment.builder().id(commentBody.getId()).createdAt(commentBody.getCreatedAt())
                .username(commentBody.getUsername()).tweetId(commentBody.getTweetId()).profileImg(commentBody.getProfileImg())
                .tweetRef(commentBody.getTweetRef()).comment(commentBody.getComment()).build();
        comment = commentService.addComment(comment);
        return comment;
    }

    @GetMapping("api/comments?tweetId={_id}")
    public List<Comment> getComments(@PathVariable("id") String tweetId) { return commentService.getComments(tweetId); }


}
