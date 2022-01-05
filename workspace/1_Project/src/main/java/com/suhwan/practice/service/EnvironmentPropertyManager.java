package com.suhwan.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:other.properties")
public class EnvironmentPropertyManager {
  
  public static final String FIRST_VALUE = "first.property.value";
  public static final String SECOND_VALUE = "second.property.value";
  
  @Autowired
  private Environment environment;
  
  public String getProperty(String key){
    return environment.getProperty(key);
  }
}
