package com.lifegraph.team20.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lifegraph.team20.entity.User;
import com.lifegraph.team20.entity.UserKeyId;

public interface UserRepository extends JpaRepository<User, UserKeyId> {}