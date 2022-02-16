package com.suhwan.practice.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suhwan.practice.vo.Member;
import com.suhwan.practice.vo.type.Status;

public class EnumTest {

  private static final Logger logger = LoggerFactory.getLogger(EnumTest.class);
  
  public static void main(String[] args) {
    EnumTest et = new EnumTest();
    Member member = et.createMember();
    logger.debug("Member    = {}", member);
    logger.debug("StatusCode= {}", member.getStatus().getCode());
    
    Status status = Status.getStatusByCode(1);
    logger.debug("Status    = {}", status);
    
    status = Status.valueOf("DELETED");
    logger.debug("Status    = {}", status);
    
  }
  
  private Member createMember(){
    Member member = new Member();
    member.setName("홍길동");
    member.setAge("30");
    member.setStatus(Status.ACTIVE);
    
    return member;
  }
}
