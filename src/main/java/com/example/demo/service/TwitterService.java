package com.example.demo.service;

import org.springframework.stereotype.Service;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.Status;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

@Service
public class TwitterService {
    private final Twitter twitter;

    public TwitterService() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("your_consumer_key")
          .setOAuthConsumerSecret("your_consumer_secret")
          .setOAuthAccessToken("your_access_token")
          .setOAuthAccessTokenSecret("your_access_token_secret");
        
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public List<Status> getLatestTweets(String username) throws Exception {
        return twitter.getUserTimeline(username);
    }
} 