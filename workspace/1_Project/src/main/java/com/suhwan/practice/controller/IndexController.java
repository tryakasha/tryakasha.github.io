package com.suhwan.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suhwan.practice.service.TaskService;

@RestController
public class IndexController {
  
  @Autowired
  private TaskService taskService;

  @RequestMapping(value = "/hello")
  public String indexMethod() {
    String returnString = "Hello World!!";
    System.out.println(returnString);
    return returnString;
  }
  
  @RequestMapping(value = "/getValProperty")
  public String getValPropertyMethod(@RequestParam String value){
    return taskService.getValProperty(value);
  }
  
  @RequestMapping(value = "/getEnvProperty")
  public String getEnvPropertyMethod(@RequestParam String value){
    System.out.println(value);
    return taskService.getEnvProperty(value);
  }
}
