package com.lifegraph.team20.auth;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiSearchGraphController {

	@RequestMapping(value = "/search", method = RequestMethod.GET)
//	メソッドや処理を関連づけるアノテーション
	public List<SearchGraph>searchGraph(){
		List<SearchGraph> SearchGraphs = selectSearchGraph();
//		l.33のselectSearchGraphsを呼び出す
		return SearchGraphs;
	}

	@Autowired
	//MySQLのデータを持ってくるライブラリ
	private JdbcTemplate jdbcTemplate;

	private List<SearchGraph> selectSearchGraph(){
		final String sql = "select name,user_id,updated_at from users inner join parent_graphs on users.id = parent_graphs.user_id";
//		sqlに"select ~"という文字列をいれる
		return jdbcTemplate.query(sql, new RowMapper<SearchGraph>() {
//			quelyの操作
//			RowMapper:JdbcTemplate.queryの処理を実行した際に、DBから取得した結果とJavaのオブジェクトとを紐づける
//			SearchGraph.javaの中にそれぞれのデータを入れている　
//			その後にRowMapper<SearchGraph>に返却される
				public SearchGraph mapRow(ResultSet rs, int rowNum) throws SQLException{
					return new SearchGraph(rs.getString("name"), rs.getInt("user_id"),rs.getTimestamp("updated_at"));
//					取得したidをl.33のSearchGraphに返す
//					さらにそのSearchGraphをl.24のselectSearchGraphに返す。
				}
		});
	}

}

//	public List<Map<String, Object>> uploadSearchGraph(List<SearchGraph> SearchGraphs) {
//		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
//	    Map<String, Object> map;
//	    for (SearchGraph graph: SearchGraphs) {
////	    	繰り返し行う
//	    map.put("id", SearchGraphs.stream()
////	    		DBのid
//    			.filter(s -> s.getParentId()==graph.getId())
//    			.collect(Collectors.toList()));
////	      map.put("name", entry.getKey());
////	      map.put("data", entry.getValue());
//
//	      dataList.add(map);
////	      mapの中身からdatalistにいれる
//	    }
//		return dataList;
//	}

