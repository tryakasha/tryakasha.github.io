package com.suhwan.practice.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suhwan.practice.dao.MemberDao;
import com.suhwan.practice.vo.BeanOne;
import com.suhwan.practice.vo.ComponentClass;
import com.suhwan.practice.vo.ParentsComponent;

@Service
public class TaskService {
  
  @Autowired
  private ValuePropertyManager valPropertyManager;
  
  @Autowired
  private EnvironmentPropertyManager envPropertyManager;
  
  @Autowired
  private BeanOne beanOne;
  
  @Autowired
  private ComponentClass componentClass;
  
  @Autowired
  @Qualifier("component")
  private ParentsComponent component;
  
  @Autowired
  private List<ParentsComponent> compList;
  
  @Autowired
  private MemberDao memberDao;
  
  @PostConstruct
  private void init(){
    System.out.println("postConsturct init");
  }
  
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

  public String checkClassPath() {
    String strClassPath = System.getProperty("java.class.path");
    String[] classPathArray = strClassPath.split(";");
    
    StringBuilder classPathBuilder = new StringBuilder();
    for(String classPath : classPathArray){
      classPathBuilder.append(classPath + "<br>");
    }
    return classPathBuilder.toString();
  }

  public void beanTest() {
    System.out.println(beanOne.returnName());
    System.out.println(componentClass.returnName());
  }
  
  public void primaryTest(){
    System.out.println(component.checkComponent());
  }
  
  public void orderTest(){
    for(ParentsComponent comp : compList){
      System.out.println("comp: " + comp.checkComponent());
    }
  }

  public void getMemberList() {
    System.out.println(memberDao.getUserList());
  }
}
