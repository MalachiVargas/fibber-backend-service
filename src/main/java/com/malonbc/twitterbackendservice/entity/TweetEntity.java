package com.malonbc.twitterbackendservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tweets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String id;
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    String createdAt;
    String username;
    @Lob
    String text;
    @Lob
    String profileImg;
    @Lob
    String image;
}
