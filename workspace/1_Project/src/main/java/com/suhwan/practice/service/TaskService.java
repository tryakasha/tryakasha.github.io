package com.suhwan.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  @Autowired
  private ValuePropertyManager valPropertyManager;
  
  @Autowired
  private EnvironmentPropertyManager envPropertyManager;
  
  public String getValProperty(String key) {
    
    if(key.equals("first")){
      return valPropertyManager.getFirstValue();
    } else if (key.equals("second")) {
      return valPropertyManager.getSecondValue();
    } else {
      return null;
    }
  }
  
  public String getEnvProperty(String key){
    if(key.equals("first")){
      return envPropertyManager.getProperty(EnvironmentPropertyManager.FIRST_VALUE);
    } else if (key.equals("second")) {
      return envPropertyManager.getProperty(EnvironmentPropertyManager.SECOND_VALUE);
    } else {
      return null;
    }
  }
  
}
