package com.lifegraph.team20.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class Tweet {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String message;
}