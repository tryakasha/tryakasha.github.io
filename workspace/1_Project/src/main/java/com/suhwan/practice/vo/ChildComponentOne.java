package com.suhwan.practice.vo;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Primary
@Order(2)
public class ChildComponentOne implements ParentsComponent{

  @Override
  public String checkComponent(){
    return "childCompOne";
  }
}
