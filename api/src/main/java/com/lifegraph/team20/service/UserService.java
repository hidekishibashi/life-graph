package com.lifegraph.team20.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifegraph.team20.entity.User;
import com.lifegraph.team20.repository.UserRepository;
import com.lifegraph.team20.repository.UserRepositoryCustom;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	@Autowired
	UserRepositoryCustom UserrepositoryCustom;

	public List<User> search(String goodsId) {
		List<User> result;
		if ("".equals(goodsId)) {
			result = repository.findAll();
		} else {
			result = UserrepositoryCustom.search( goodsId);
		}
		return result;
	}

	public List<org.apache.catalina.User> getUser() {
		return null;
	}

}