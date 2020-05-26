package com.lifegraph.team20.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifegraph.team20.LifeGraphData;
import com.lifegraph.team20.models.ParentLifeGraph;
import com.lifegraph.team20.repository.LifeGraphRepository;




@Service
public class LifeGraphsService {


  @Autowired
  private LifeGraphRepository repository;


  /**
   * 登録・編集のメイン処理
   *
   * @param data 登録・編集リクエスト
   */
  public void resister(LifeGraphData data) {
    // parent table
    long parentId = resisterParent(data.getUserId());
    // child table
    registerChild(parentId, data);
  }

  /**
   * 親グラフの登録・更新を行う
   *
   * @param userId ユーザーID
   * @return 親グラフID
   */
  private long resisterParent(long userId) {
    // exists parent by userId
    long parentId;
    Optional<ParentLifeGraph> parent = repository.findByUserId(userId);
//    if (parentRepository.existsByUserId(userId)) {
    if (parent.isPresent()) {
      // update
      parentId = parent.get().getId();
      // 更新日時をアップデートする
      repository.updateNowTime(userId);
    } else {
      // insert
      parentId = repository.insert(userId);

    }
    return parentId;
  }

  private void registerChild(long parentId, LifeGraphData data) {
    // exists parent by userId
    // 子供のIDがあれば、更新する
    if (repository.existsById(data.getId())) {
      // update
    	repository.updateChild(data.getId(), data.getAge(),data.getScore(), data.getComment());
      // 更新した時にすでにageが存在する場合、エラーを出す
        // エラーステータス409が出るかも？？
        // でプリケイどエラーを調べる
    } else {
      // insert
    	repository.addChild(parentId, data.getAge(), data.getScore(), data.getComment());
      // 更新した時にすでにageが存在する場合、エラーを出す
    }
  }

  public void deleteChild(long Id) {
	  repository.deleteChild(Id);
	   }

//  public ChildChart refRecode(LifeGraphData data) {
//	    long parentId = data.getParentId();
//	    int age = data.getAge();
//	    //parentIdとageが一致するレコードを持ってくる
//	    ChildChart record = repository.record(parentId, age);
//	    return record;
//	  }


  // public void selectData(long userId) {
	//   repository.selectData(userId);
  // }
}
