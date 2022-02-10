package com.suhwan.practice.service;

import org.springframework.stereotype.Service;

import com.suhwan.practice.vo.Member;

@Service
public class RestService {
  
  public Member getTestMember(String id){
    Member member = new Member();
    member.setName("호랑이");
    member.setAge("15");
    return id.equals("99")?member:null;
  }
}
