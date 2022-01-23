package com.suhwan.practice.vo;

import org.springframework.stereotype.Component;

@Component("depTwo")
public class DependsOnComponentTwo {
  public DependsOnComponentTwo(){
    System.out.println("Generated DependsOnCompTwo");
  }
}
