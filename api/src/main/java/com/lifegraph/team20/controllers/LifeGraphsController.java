package com.lifegraph.team20.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.LifeGraphData;
import com.lifegraph.team20.Service.LifeGraphsService;


@RestController
public class LifeGraphsController {

  @Autowired
  private LifeGraphsService service;


  @PostMapping(value = "/auth/life_graphs")
  public ResponseEntity<Void> postController(@Valid @RequestBody LifeGraphData data) throws URISyntaxException {
    // サービスを呼ぶ
    service.resister(data);
    return ResponseEntity.created(new URI("/life_graphs/" + data.getUserId())).build();
  }

//  @PostMapping(value = "/auth/ref-record")
//  public ChildChart refRecode(@RequestBody LifeGraphData data) {
//	  ChildChart record = service.refRecode(data);
//    return record;
//  }

  @DeleteMapping(value = "/auth/{id}")
	public ResponseEntity<Void> deleteControler(@PathVariable("id") long Id) {
	  // call delete
//    service. deleteChild(Id,data);
		service.deleteChild(Id);
    return ResponseEntity.noContent().build();
  }


}
