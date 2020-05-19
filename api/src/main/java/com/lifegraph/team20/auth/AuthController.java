package com.lifegraph.team20.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ResponseEntity<String> login() {
    return ResponseEntity.ok("OK");
  }
}


//@RequestMapping(value = "/sample", method = RequestMethod.GET)
//public List<GoodsController> userData() {
//	List<GoodsController> goodsController = setGoodsController();
//	return goodsController;
//
//}


//private List<GoodsController> setGoodsController() {
//	final String sql ="select * from User innner join "
//	return null;
//}