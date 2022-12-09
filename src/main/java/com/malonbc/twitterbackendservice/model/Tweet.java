package com.malonbc.twitterbackendservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {
    String id;
    String createdAt;
    String text;
    String username;
    String profileImg;
    String image;
}
