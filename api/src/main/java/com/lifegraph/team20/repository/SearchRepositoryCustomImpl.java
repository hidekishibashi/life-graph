package com.lifegraph.team20.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifegraph.team20.entity.Search;



@Component
public class SearchRepositoryCustomImpl implements SearchRepositoryCustom {

	@Autowired
	EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Search> search(String userId, String userName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT g From User g WHERE ");
		boolean andFlg = false;
		boolean userIdFlg = false;
		if (!"".equals(userId) && userId != null) {
			sql.append(" g.userId LIKE % :userId % ");
			userIdFlg = true;
			andFlg = true;
		}
		boolean userNameFlg = false;
		if (!"".equals(userName) && userName != null) {
			if (andFlg) sql.append(" AND ");
			sql.append(" g.userName LIKE % :userName % ");
			userNameFlg = true;
			andFlg = true;
		}
		Query query = manager.createQuery(sql.toString());
		if (userIdFlg) query.setParameter("userId", "%" + userId + "%");
		if (userNameFlg) query.setParameter("userName", "%" + userName + "%");
		return query.getResultList();
	}

}