package com.suhwan.practice.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.suhwan.practice.vo.BeanOne;

@Configuration
public class BeanConfService {
  
  @Bean
  public BeanOne generateBeanOne(){
    return new BeanOne();
  }
}
