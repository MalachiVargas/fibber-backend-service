package com.malonbc.twitterbackendservice.service;

import com.malonbc.twitterbackendservice.entity.CommentEntity;
import com.malonbc.twitterbackendservice.model.Comment;
import com.malonbc.twitterbackendservice.model.Tweet;
import com.malonbc.twitterbackendservice.repository.CommentEntityRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImplementation implements CommentService {
    private CommentEntityRepository commentEntityRepository;

    public CommentServiceImplementation(CommentEntityRepository commentEntityRepository) {
        this.commentEntityRepository = commentEntityRepository;
    }


    @Override
    public Comment addComment(Comment comment) throws Exception {
        try {
            CommentEntity commentEntity = new CommentEntity();
            BeanUtils.copyProperties(comment, commentEntity);
            commentEntity = commentEntityRepository.save(commentEntity);
            comment.setId(commentEntity.getId());
            comment.setCreatedAt(commentEntity.getCreatedAt().toString());
        } catch (Exception e) {
            throw new Exception("could not save comment" + e);
        }
        return comment;
    }

    @Override
    public List<Comment> getComments(String tweetRef) {
        List<CommentEntity> commentEntities = commentEntityRepository.findAllByTweetRef(tweetRef, Sort.by("createdAt").descending());
        List<Comment> comments = commentEntities.stream().map((commentEntity) -> Comment.builder().id(commentEntity.getId())
                .createdAt(commentEntity.getCreatedAt().atZone(ZoneId.of("UTC")).toInstant().toString()).username(commentEntity.getUsername())
                .comment(commentEntity.getComment()).profileImg(commentEntity.getProfileImg()).tweetRef(commentEntity.getTweetRef()).build()).toList();
        return comments;

    }




}
