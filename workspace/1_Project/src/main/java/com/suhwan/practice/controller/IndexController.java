package com.suhwan.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suhwan.practice.service.TaskService;
import com.suhwan.practice.vo.NameValuePair;

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
  
  @RequestMapping(value = "/checkClassPath")
  public String checkClassPath(){
    return taskService.checkClassPath();
  }
  
  @RequestMapping(value = "/paramTest/{firstPathParam}/{secondPathParam}")
  public void paramTest(@PathVariable String firstPathParam, 
                          @PathVariable String secondPathParam, 
                          @RequestParam String queryParam, 
                          @RequestBody NameValuePair nameValuePair){
    
    System.out.println("firstPathParam: " + firstPathParam);
    System.out.println("secondPathParam: " + secondPathParam);
    System.out.println("queryParam: " + queryParam);
    System.out.println("requestBody: " + nameValuePair);
  }
  
  @RequestMapping(value = "/beanTest")
  public void beanTestMethod(){
    taskService.beanTest();
  }
  
  @RequestMapping(value = "/primaryTest")
  public void primaryTestMethod(){
    taskService.primaryTest();
  }
  
  @RequestMapping(value = "/orderTest")
  public void orderTestMethod(){
    taskService.orderTest();
  }
  
  
}
