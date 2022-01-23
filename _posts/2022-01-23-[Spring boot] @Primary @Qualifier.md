같은 타입의 bean이 여러개인 경우 주입할때 에러가 발생하는데, 이때 bean의 우선순위를 지정하여 해결할 수 있다.



#### @Primary

- 같은 타입의 bean이 여러개 존재하는 경우 @Primary 어노테이션을 사용하면 특정 bean의 우선순위를 높여 사용할 수 있음
- 단, @Primary가 사용된 bean이 여러개인 경우 동일하게 에러 발생



에러가 발생하는 경우를 확인

##### Component

```java
// 부모
public interface ParentsComponent {

}

// 자식
@Component
public class ChildComponentOne implements ParentsComponent{

}

@Component
public class ChildComponentTwo implements ParentsComponent{

}
```



##### Service 에서 @Autowired 사용

```java
@Autowired
  private ParentsComponent componentOne;
```



##### 에러 확인

![CheckErrorForPrimaryAnnotation](..\image\2022-01-23\CheckErrorForPrimaryAnnotation.PNG)





##### ComponentOne 에 @Primary 사용하여 해결

`````java
// 부모
public interface ParentsComponent {

  public String checkComponent();
}

// 자식
@Component
@Primary
public class ChildComponentOne implements ParentsComponent{
  
  @Override
  public String checkComponent(){
    return "childCompOne";
  }
}

@Component
public class ChildComponentTwo implements ParentsComponent{
 
  @Override
  public String checkComponent(){
    return "childCompTwo";
  }
}
`````



##### Service

```java
@Autowired
private ParentsComponent component;


public void primaryTest(){
    System.out.println(component.checkComponent());
}
```



##### 테스트 결과

![PrimaryTest](..\image\2022-01-23\PrimaryTest.PNG)



* **@Primary를 모두 붙이면 에러가 발생**합니다.

  org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name ....





#### @Qualifier

* @Primary 대신 @Qualifier를 사용하여 이름을 지정할 수 있음
* @Primary 대비 상세한 설정이 가능하여 여러개의 빈을 다룰 수 있음



##### ComponentTwo에 @Qualifier 사용

```java
// 부모
public interface ParentsComponent {

  public String checkComponent();
}

// 자식
@Component
@Primary
public class ChildComponentOne implements ParentsComponent{
  
  @Override
  public String checkComponent(){
    return "childCompOne";
  }
}

@Component
@Qualifier("component")
public class ChildComponentTwo implements ParentsComponent{
 
  @Override
  public String checkComponent(){
    return "childCompTwo";
  }
}
```



##### Service

```java
@Autowired
@Qualifier("component")
private ParentsComponent component;

public void primaryTest(){
  System.out.println(component.checkComponent());
}
```



##### 테스트

![QualifierTest](..\image\2022-01-23\QualifierTest.PNG)

> @Primary 어노테이션을 일부로 제거하지 않았도 ComponentTwo를 제대로 주입하고 있다.
>
> @Qualifier 가 @Primary 보다 우선순위가 높음을 확인할 수 있음

