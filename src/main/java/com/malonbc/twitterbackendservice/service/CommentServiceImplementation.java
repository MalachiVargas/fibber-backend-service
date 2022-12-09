package com.malonbc.twitterbackendservice.service;

import com.malonbc.twitterbackendservice.entity.CommentEntity;
import com.malonbc.twitterbackendservice.model.Comment;
import com.malonbc.twitterbackendservice.model.Tweet;
import com.malonbc.twitterbackendservice.repository.CommentEntityRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
            comment.setCreatedAt(commentEntity.getCreatedAt());
        } catch (Exception e) {
            throw new Exception("could not save comment" + e);
        }
        return comment;
    }

    @Override
    public List<Comment> getComments(String tweetId) {
        List<CommentEntity> commentEntities = commentEntityRepository.findAllById(tweetId, Sort.by("_createdAt").descending());
        List<Comment> comments = commentEntities.stream().map((commentEntity) -> Comment.builder().id(commentEntity.getId())
                .createdAt(commentEntity.getCreatedAt()).username(commentEntity.getUsername())
                .comment(commentEntity.getComment()).profileImg(commentEntity.getProfileImg()).build()).toList();
        return comments;

    }




}
