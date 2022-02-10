package com.suhwan.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suhwan.practice.service.RestService;
import com.suhwan.practice.vo.Member;

@RestController
public class RestTestController {
  
  @Autowired
  private RestService restService;
  
  @RequestMapping(method = RequestMethod.POST, value = "getExMember")
  public Member getMember(@RequestParam("id") String id){
    return restService.getTestMember(id);
  }
}
