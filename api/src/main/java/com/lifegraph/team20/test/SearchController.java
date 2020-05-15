package com.lifegraph.team20.test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.tree.RowMapper;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.search.Search;

import ch.qos.logback.classic.Logger;

@RestController
public class SearchController<Search> {

  private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * Simply selects the home view to render by returning its name.
   *
   * @throuws IOException
   */
  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public String searchName(Locale locale, Model model) {
    logger.info("Welcome home! The client locale is {}.", locale);

    try {
      initialize();
    } catch (IOException e) {
      // TODO 自動生成された catch ブロック
      e.printStackTrace();
    }
    ;

    List<Search> user = searchName();
    searchName(user);

    return "skill-upload";
  }

  // ここからタスク10
  //
  // Listの宣言
  public List<Search> searchName() {
    // sequel proで作ったテーブルからデータを取得する文字列をsqlという変数に入れている
    final String sql = "select * from user";

    return jdbcTemplate.query(sql, new RowMapper<Search>() {
      public Search mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Search(rs.getString("id"),rs.getString("name"));

      }
    });
  }

  private FirebaseApp app;

  //


  //
  public void uploadSkill(List<Skills> skills) {
    // データの保存
    final FirebaseDatabase database = FirebaseDatabase.getInstance(app);
    DatabaseReference ref = database.getReference("skills");
    //
    // Map型のリストを作る。MapはStringで聞かれたものに対し、Object型で返すようにしている
    List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
    Map<String, Object> map;
    Map<String, List<Skills>> skillMap = skills.stream().collect(Collectors.groupingBy(Skills::getSkillCategory));
    for (Map.Entry<String, List<Skills>> entry : skillMap.entrySet()) {
      map = new HashMap<>();
      map.put("category", entry.getKey());
      map.put("skill", entry.getValue());
      dataList.add(map);
    }
    // 非互換オペランド型 String と intとは？↑
    //
    ref.setValue(dataList, new DatabaseReference.CompletionListener() {
      @Override
      public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        if (databaseError != null) {
          System.out.println("Data could be saved" + databaseError.getMessage());
        } else {
          System.out.println("Data save successfully.");
        }
      }
    });
  }
}