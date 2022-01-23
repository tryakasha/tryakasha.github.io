package com.suhwan.practice.vo;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component("depOne")
@DependsOn("depTwo")
public class DependsOnComponentOne {
  public DependsOnComponentOne(){
    System.out.println("Generated DependsOnCompOne");
  }
}
