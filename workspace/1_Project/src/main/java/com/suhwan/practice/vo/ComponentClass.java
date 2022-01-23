package com.suhwan.practice.vo;

import org.springframework.stereotype.Component;

@Component
public class ComponentClass {
  
  private String name;
  
  public ComponentClass(){
    this.name = "ComponentClass";
  }
  
  public String returnName(){
    return name;
  }
}
