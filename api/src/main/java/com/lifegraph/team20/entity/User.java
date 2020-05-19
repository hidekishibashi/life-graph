package com.lifegraph.team20.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@Entity
@Table(name="goods_mst")
@IdClass(value=UserKeyId.class)

public class User {

	@Id
	@Column(name="goods_id")
	@Getter
	@Setter
	private String goodsId;

	@Id
	@Column(name="goods_name")
	@Getter
	@Setter
	private int goodsName;


	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name="User", insertable=false, updatable=false)
	private Search search;

}