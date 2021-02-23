package com.cg.lib.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.lib.entity.Books;

@Repository
public interface IBookDAO extends JpaRepository<Books, String>{

}
