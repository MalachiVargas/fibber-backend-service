package com.malonbc.twitterbackendservice.controller;

import com.malonbc.twitterbackendservice.model.Tweet;
import com.malonbc.twitterbackendservice.service.TweetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TweetController {
    private TweetService tweetService;
    public TweetController(TweetService tweetService) { this.tweetService = tweetService; }

    @PostMapping("api/addTweet")
    public Tweet addTweet(@RequestBody Tweet tweetBody) throws Exception {

        Tweet tweet = Tweet.builder().id(tweetBody.getId()).createdAt(tweetBody.getCreatedAt())
                .username(tweetBody.getUsername()).image(tweetBody.getImage()).profileImg(tweetBody.getProfileImg())
                .text(tweetBody.getText()).build();
        tweet = tweetService.addTweet(tweet);
        return tweet;
    }
    @GetMapping("api/tweets")
    public List<Tweet> getTweets() { return tweetService.getTweets(); }

}
