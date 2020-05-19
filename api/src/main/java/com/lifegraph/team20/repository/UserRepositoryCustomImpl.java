package com.lifegraph.team20.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifegraph.team20.entity.User;

@Component
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

	@Autowired
	EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<User> search(String userId, String customerId, String goodsId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT s From Sales s WHERE ");
		boolean andFlg = false;
		boolean salesIdFlg = false;
		boolean customerIdFlg = false;
		boolean goodsIdFlg = false;
		if (!"".equals(userId) && userId != null) {
			sql.append(" s.salesId = :salesId ");
			salesIdFlg = true;
			andFlg = true;
		}
		if (!"".equals(customerId) && customerId != null) {
			if (andFlg) sql.append(" AND ");
			sql.append(" s.customerId = :customerId ");
			customerIdFlg = true;
			andFlg = true;
		}
		if (!"".equals(goodsId) && goodsId != null) {
			if (andFlg) sql.append(" AND ");
			sql.append(" s.goodsId = :goodsId ");
			goodsIdFlg = true;
			andFlg = true;
		}
		Query query = manager.createQuery(sql.toString());
		if (salesIdFlg) query.setParameter("salesId",  userId);
		if (customerIdFlg) query.setParameter("customerId",  customerId);
		if (goodsIdFlg) query.setParameter("goodsId", goodsId);
		return query.getResultList();
	}

	@Override
	public List<User> search(String goodsId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}