package com.cg.lib.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.lib.entity.BookIssue;

public interface IBookIssueDAO extends JpaRepository<BookIssue, Integer> {

}
