package com.malonbc.twitterbackendservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    String id;
    String createdAt;
    String comment;
    String username;
    String profileImg;
    String tweetId;
    String tweetRef;
}
