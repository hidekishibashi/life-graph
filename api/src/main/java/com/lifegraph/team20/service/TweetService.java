package com.lifegraph.team20.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifegraph.team20.model.Tweet;
import com.lifegraph.team20.repository.TweetRepository;

@Service
@Transactional
public class TweetService {
    @Autowired
    TweetRepository tweetRepository;

    public Tweet postTweet(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    public List<Tweet> getTweet() {
        return tweetRepository.findAll();
    }
}