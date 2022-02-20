---
categories: Spring-boot
author_profile: false
sidebar:
  nav: "docs"
---



#### @Order

* List 형태로 Autowired할때 객체를 넣는 순서를 지정한다.



기존 Component 를 사용하여 테스트

##### Component

```java
public class DependsOnComponentOne {
  public DependsOnComponentOne(){
    System.out.println("Generated DependsOnCompOne");
  }
}

public class DependsOnComponentTwo {
  public DependsOnComponentTwo(){
    System.out.println("Generated DependsOnCompTwo");
  }
}
```



##### Service

```java
@Autowired
private List<ParentsComponent> compList;

public void orderTest(){
  for(ParentsComponent comp : compList){
    System.out.println("comp: " + comp.checkComponent());
  }
}
```



##### 메소드 실행 결과

![OrderTest_1](..\..\image\2022-01-23\OrderTest_1.PNG)



##### Component @Order 작성

> 낮은 순자가 우선순위가 높다.

```java
@Component
@Order(2)
public class ChildComponentOne implements ParentsComponent{
  
  public ChildComponentOne(){
    System.out.println("generated CompOne");
  }
  
  @Override
  public String checkComponent(){
    return "childCompOne";
  }
}


@Component
@Order(1)
public class ChildComponentTwo implements ParentsComponent{
  
  public ChildComponentTwo(){
    System.out.println("generated CompTwo");
  }
  
  @Override
  public String checkComponent(){
    return "childCompTwo";
  }
}
```



##### 테스트 결과

![OrderTest_2](..\..\image\2022-01-23\OrderTest_2.PNG)

순서가 바뀐것을 확인할 수 있음