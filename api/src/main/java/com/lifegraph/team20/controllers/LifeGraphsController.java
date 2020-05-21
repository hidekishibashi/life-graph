package com.lifegraph.team20.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.LifeGraphData;
import com.lifegraph.team20.Service.LifeGraphsService;


@RestController
public class LifeGraphsController {

  @Autowired
  LifeGraphsService service;

  @RequestMapping(value = "/auth/life_graphs", method = RequestMethod.POST)

  public ResponseEntity<Void> postController(@Valid @RequestBody LifeGraphData data) {

    // サービスを呼ぶ
    service.resister(data);


    return ResponseEntity.created(null).build();
  }

//  @RequestMapping(value = "/auth/delete", method = RequestMethod.DELETE)
//  public ResponseEntity<Void> deleteController(@Valid @RequestBody LifeGraphData data.getUserId()) {
//
//
//    service.deleteChild(data.getUserId());
//
//      return ResponseEntity.ok("OK");
//  }
}

