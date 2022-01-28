package com.suhwan.practice.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.suhwan.practice.vo.Member;

@Repository
public interface MemberDao {

  public List<Member> getUserList();
}
