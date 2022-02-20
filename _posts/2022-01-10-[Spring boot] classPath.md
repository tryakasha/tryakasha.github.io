---
categories: Spring-boot
author_profile: false
sidebar:
  nav: "docs"
---



#### ClassPath란?

프로퍼티 파일을 읽을때 classPath: 라고 되어있는 부분이 있는데, classPath는 무엇일까

classPath 는 JVM, java 컴파일러가 class파일을 찾을때 기준이 되는 파일들의 경로를 말한다.



##### java에서 ClassPath확인하기



* classPath를 확인하기 위한 service 작성

> 파일의 경로는 ; 로 구분된다.

```java
  public String checkClassPath() {
    String strClassPath = System.getProperty("java.class.path");
    String[] classPathArray = strClassPath.split(";");
    
    StringBuilder classPathBuilder = new StringBuilder();
    for(String classPath : classPathArray){
      classPathBuilder.append(classPath + "<br>");
    }
    return classPathBuilder.toString();
  }
```



* controller 작성

```java
  @RequestMapping(value = "/checkClassPath")
  public String checkClassPath(){
    return taskService.checkClassPath();
  }
```



* 테스트 결과

![checkClassPath](.\..\..\image\2022-01-10\checkClassPath.PNG)

