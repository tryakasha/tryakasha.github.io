package com.suhwan.practice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
@PropertySource("classpath:other.properties")
public class ValuePropertyManager {
  
  @Value("${first.property.value}")
  private String firstValue;
  
  @Value("${second.property.value}")
  private String secondValue;
}
