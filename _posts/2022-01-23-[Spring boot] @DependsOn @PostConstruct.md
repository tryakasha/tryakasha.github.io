---
categories: Spring-boot
author_profile: false
sidebar:
  nav: "docs"
---



#### @DependsOn

* bean이 생성되는 과정에서 다른 bean을 의존하고 있을때 의존하고 있는 bean이 있다고 명시해주는 어노테이션

* 만약 명시하지 않은경우 상황에 따라서 Exception이 발생할 수 있다.

  > 의존 되고있는 bean 이 아직 context에 로드되지 않은 상태인 상황에서 사용되려하는 경우



사용법

##### Component 작성

```java
@Component("depOne")
public class DependsOnComponentOne {
  public DependsOnComponentOne(){
    System.out.println("Generated DependsOnCompOne");
  }
}

@Component("depTwo")
public class DependsOnComponentTwo {
  public DependsOnComponentTwo(){
    System.out.println("Generated DependsOnCompTwo");
  }
}
```



##### Bean 생성 순서 확인

![DependsOnTest_1](..\..\image\2022-01-23\DependsOnTest_1.PNG)



##### @DependsOn 추가

> one > two 순서로 생성되고 있으니, 순서를 뒤집어주기위해 one component 에 dependsOn을 작성하여 의존관계를 알려줌

```java
@Component("depOne")
@DependsOn("depTwo")
public class DependsOnComponentOne {
  public DependsOnComponentOne(){
    System.out.println("Generated DependsOnCompOne");
  }
}
```



##### Bean 생성 순서 확인

![DependsOnTest_2](..\..\image\2022-01-23\DependsOnTest_2.PNG)



Two > One 으로 Bean 생성 순서가 바뀌는것 확인



#### @PostConstruct

* 스프링에서 초기화 작업을 할때 사용되는 어노테이션
* 가장 먼저 동작하기때문에 생명주기에 주의하여 사용하자
* 딱 한번만 동작하기에 key값등을 가져올때 사용



사용법은 메소드 위에 @PostConstruct를 붙여주면 된다.

```java
@PostConstruct
private void init(){
  System.out.println("init method");
}
```



##### 서버 실행

![PostConstruct](..\..\image\2022-01-23\PostConstruct.PNG)



PostConstruct 가 실행된 후 bean생성되는것으로 확인됨