package com.lifegraph.team20.controllers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.ChildChart;
@RestController
public class ReferenceController {
//  IDの名は後で明確にします。（林）
    @GetMapping(value = "/auth/reference/{userId}")
    public ResponseEntity<List<ChildChart>> Child(
            @PathVariable("userId") long userId){
//  Optional:その値がnullかもしれないことを表現するクラス/URLが叩かれたときにこれが動く/key IDに何も入らないとhttp400エラーになるから注意
        List<ChildChart> Child = selectChilds(userId);  //子チャートをIDに定義 //selectChildsの定義をl.55へ
//          l.31のselectChildsを定義名Childで呼び出す
        return ResponseEntity.ok(Child);
    }
    @Autowired
    //MySQLのデータを持ってくるライブラリ
    private JdbcTemplate jdbcTemplate;
    public List<ChildChart> selectChilds(long userId) {
        final String sql = "select * from child_chart where parent_id=(select id from parent_chart where user_id ="+userId+")";
        return jdbcTemplate.query(sql, new RowMapper<ChildChart>() {// <ChildChart>の中にそれぞれのデータを入れている
            public ChildChart mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ChildChart(rs.getLong("id"), rs.getLong("parent_id"),
                        rs.getInt("age"), rs.getInt("score"), rs.getString("comment"));
            }
        });
    }
}
