package com.cg.lib.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.lib.entity.User;

@Repository
public interface IUserDAO extends JpaRepository<User, String> {

}
