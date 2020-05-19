package com.lifegraph.team20.repository;

import java.util.List;

import com.lifegraph.team20.entity.Search;





public interface SearchRepositoryCustom {
	public List<Search> search(String userId, String userName);
}