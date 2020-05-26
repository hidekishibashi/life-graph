package com.lifegraph.team20.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.SearchGraphInfo;

@RestController
public class SearchController2 {

	@RequestMapping(value = "/auth/life-graphs2", method = RequestMethod.GET)
		public ResponseEntity<List<SearchGraphInfo>>SearchGraphInfos( @RequestParam("startDate")String start,
													@RequestParam("endDate") String end){
			String SD = null;
			String ENDT = null;
			for (int i = 0; i < 10; i++){
				if(i == 0) {
					SD = String.valueOf(start.charAt(i));
					ENDT = String.valueOf(end.charAt(i));
				}
				else if(i == 4 || i == 7) {
					//
				}
				else {
					SD += String.valueOf(start.charAt(i));
					ENDT += String.valueOf(end.charAt(i));
				}
			}
			System.out.println(SD);
			System.out.println(ENDT);
			Optional<String> startDate = Optional.of(SD);
			Optional<String> endDate = Optional.of(ENDT);
			List<SearchGraphInfo> SearchGraphInfos = SelectSearchGraphInfo(startDate,endDate);
			return ResponseEntity.ok(SearchGraphInfos);
		}


// 日時検索のためには、変更、編集が必要かも？
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private List<SearchGraphInfo> SelectSearchGraphInfo(Optional<String> startDate, Optional<String> endDate){


// sql="";はsqlが空で指定されてしまう
		String sql = "select `username`,`user_id`,`updated_at`,`created_at` from users INNER JOIN parent_chart on users.id = parent_chart.user_id ";
		if(startDate.isPresent() && endDate.isPresent()) {
			sql += "WHERE `updated_at` BETWEEN "+startDate.get()+" AND "+endDate.get()+"";
		}
		return jdbcTemplate.query(sql, new RowMapper<SearchGraphInfo>() {
			public SearchGraphInfo mapRow(ResultSet rs, int rowNum)throws SQLException {
				return new SearchGraphInfo(rs.getString("username"), rs.getInt("user_id"), rs.getTimestamp("created_at"),rs.getTimestamp("updated_at"));
			}
		});
	}
}
