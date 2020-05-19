package com.lifegraph.team20.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifegraph.team20.entity.Search;
import com.lifegraph.team20.repository.SearchRepository;
import com.lifegraph.team20.repository.SearchRepositoryCustom;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchService {

	@Autowired
	SearchRepository repository;
	@Autowired
	SearchRepositoryCustom repositoryCustom;

	public List<Search> search(String userId, String userName) {
		List<Search> result;
		if ("".equals(userId) && "".equals(userName)) {
			result = repository.findAll();
		} else {
			result = repositoryCustom.search(userId, userName);
		}
		return result;
	}

	public List<Search> getSearch() {
		return repository.findAll();
	}

}

