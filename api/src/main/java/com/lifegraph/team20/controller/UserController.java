package com.lifegraph.team20.controller;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lifegraph.team20.repository.UserRepository;
import com.lifegraph.team20.service.UserService;

@ComponentScan
@Controller
@RequestMapping("/user")
public class UserController {

    public UserService service;
    @Autowired
    public UserRepository repository;
    @RequestMapping(method = RequestMethod.GET)
    List<User> getUser() {
        return service.getUser();
    }
}
