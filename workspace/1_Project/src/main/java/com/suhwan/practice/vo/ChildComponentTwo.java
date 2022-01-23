package com.suhwan.practice.vo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Qualifier("component")
@Order(1)
public class ChildComponentTwo implements ParentsComponent{
  
  @Override
  public String checkComponent(){
    return "childCompTwo";
  }
}
