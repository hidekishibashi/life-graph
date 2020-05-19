package com.lifegraph.team20.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Table(name="Parent_Chart")
public class Search {

	@Id
	@Column(name= "id")
	@Getter
	@Setter
	private int userId;

	@Column(name="user_id")
	@Getter
	@Setter
	private String userName;

	@Column(name="updated_at")
	@Getter
	@Setter
	private String updatedAt;

}