package com.suhwan.practice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

  @RequestMapping(value = "/hello")
  public String indexMethod() {
    String returnString = "Hello World!!";
    System.out.println(returnString);
    return returnString;
  }
}
