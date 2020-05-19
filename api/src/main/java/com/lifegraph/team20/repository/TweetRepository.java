package com.lifegraph.team20.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lifegraph.team20.model.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {}