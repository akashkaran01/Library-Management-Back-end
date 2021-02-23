package com.cg.lib.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.lib.entity.Admin;

@Repository
public interface IAdminDAO extends JpaRepository<Admin, String>{
	
}
