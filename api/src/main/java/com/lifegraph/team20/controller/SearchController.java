package com.lifegraph.team20.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.entity.Search;
import com.lifegraph.team20.repository.SearchRepository;
import com.lifegraph.team20.service.SearchService;
@ComponentScan

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    public SearchService service;
    @Autowired
    public SearchRepository repository;
    @RequestMapping(method = RequestMethod.GET)
    List<Search> getSearch() {
        return service.getSearch();
    }
}