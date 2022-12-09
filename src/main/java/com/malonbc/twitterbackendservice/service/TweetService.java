package com.malonbc.twitterbackendservice.service;

import com.malonbc.twitterbackendservice.model.Tweet;

import java.util.List;

public interface TweetService {
    Tweet addTweet(Tweet tweet) throws Exception;

    List<Tweet> getTweets();
}
