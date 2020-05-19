package com.lifegraph.team20.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.model.Tweet;
import com.lifegraph.team20.service.TweetService;

@RestController
@RequestMapping("tweet")
public class TweetController {
    @Autowired
    TweetService tweetService;

    @RequestMapping(method = RequestMethod.POST)
    Tweet postTweet(@RequestBody Tweet tweet) {
        return tweetService.postTweet(tweet);
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Tweet> getTweet() {
        return tweetService.getTweet();
    }
}