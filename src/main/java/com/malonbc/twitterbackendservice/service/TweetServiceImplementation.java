package com.malonbc.twitterbackendservice.service;

import com.malonbc.twitterbackendservice.entity.TweetEntity;
import com.malonbc.twitterbackendservice.model.Tweet;
import com.malonbc.twitterbackendservice.repository.TweetEntityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

            if (tweet.getImage() != null && !tweet.getImage().equalsIgnoreCase("null")) {
                tweetEntity.setImage(tweet.getImage());
            } else {
                tweetEntity.setImage(null);
            }

            tweetEntity = tweetEntityRepository.save(tweetEntity);
            tweet.setId(tweetEntity.getId());
            tweet.setImage(tweetEntity.getImage());
            tweet.setCreatedAt(tweetEntity.getCreatedAt());
        } catch (Exception e) {
            throw new Exception("could not save tweet: " + e);
        }
        return tweet;
    }

    @Override
    public List<Tweet> getTweets() {

        List<TweetEntity> tweetEntities = tweetEntityRepository.findAll(Sort.by("_createdAt").descending());

        List<Tweet> tweets = tweetEntities.stream().map((tweetEntity) -> Tweet.builder().id(tweetEntity.getId())
                .createdAt(tweetEntity.getCreatedAt()).username(tweetEntity.getUsername())
                .image(tweetEntity.getImage()).profileImg(tweetEntity.getProfileImg())
                .text(tweetEntity.getText()).build()).collect(Collectors.toList());
        return tweets;
    }
}
