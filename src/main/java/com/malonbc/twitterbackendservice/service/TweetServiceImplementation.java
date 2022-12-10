package com.malonbc.twitterbackendservice.service;

import com.malonbc.twitterbackendservice.entity.TweetEntity;
import com.malonbc.twitterbackendservice.model.Tweet;
import com.malonbc.twitterbackendservice.repository.TweetEntityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetServiceImplementation implements TweetService {
    private TweetEntityRepository tweetEntityRepository;

    public TweetServiceImplementation(TweetEntityRepository tweetEntityRepository) {
        this.tweetEntityRepository = tweetEntityRepository;
    }

    @Override
    public Tweet addTweet(Tweet tweet) throws Exception {
        try {
            TweetEntity tweetEntity = new TweetEntity();
            BeanUtils.copyProperties(tweet, tweetEntity);
            tweetEntity = tweetEntityRepository.save(tweetEntity);
            tweet.setId(tweetEntity.getId());
            tweet.setCreatedAt(tweetEntity.getCreatedAt().toString());
        } catch (Exception e) {
            throw new Exception("could not save tweet: " + e);
        }
        return tweet;
    }

    @Override
    public List<Tweet> getTweets() {

        List<TweetEntity> tweetEntities = tweetEntityRepository.findAll(Sort.by("createdAt").descending());
        List<Instant> tweetTime = tweetEntities.stream().map(TweetEntity::getCreatedAt).collect(Collectors.toList());
        System.out.println(tweetTime);
        List<Tweet> tweets = tweetEntities.stream().map((tweetEntity) -> Tweet.builder().id(tweetEntity.getId())
                .createdAt(tweetEntity.getCreatedAt().atZone(ZoneId.of("UTC")).toInstant().toString()).username(tweetEntity.getUsername())
                .image(tweetEntity.getImage()).profileImg(tweetEntity.getProfileImg())
                .text(tweetEntity.getText()).build()).collect(Collectors.toList());
        return tweets;
    }
}
