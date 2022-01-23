#### @Bean

* 보통 외부 라이브러리를 사용할때 이용한다.
* @Configuration을 선언한 클래스 내부에서 사용하며, 개발자가 작성한 메소드를 통하여 반환되는 객체를 Bean으로 만든다.
* @Bean(name="myBean") 처럼 이름을 명시하지 않으면 메소드명(소문자)로 사용



@Bean 사용 예

##### Configuration

```java
@Configuration
public class BeanConfService {
  
  @Bean
  public BeanOne generateBeanOne(){
    return new BeanOne();
  }
}
```



##### BeanOne Class

```java
public class BeanOne {

  private String name;
  
  public BeanOne() {
    this.name = "BeanOne";
  }
  
  public String returnName(){
    return name;
  }
}
```



##### Service

```java
@Autowired
  private BeanOne beanOne;

public void beanTest() {
    System.out.println(beanOne.returnName());
  }
```



##### beanTest() 메소드 실행결과

정말 간단하게 테스트 가능

![BeanTest](..\image\2022-01-23\BeanTest.PNG)



#### @Component

* 개발자가 작성한 class를 bean으로 등록할 수 있게 만들어 준다.
* @Service, @Repository, @Controller는 @Component를 상속받고 있다.



@Component 사용 예

##### Component Class

```java
@Component
public class ComponentClass {
  
  private String name;
  
  public ComponentClass(){
    this.name = "ComponentClass";
  }
  
  public String returnName(){
    return name;
  }
}
```



##### Service

```java
@Autowired
  private ComponentClass componentClass;

public void beanTest() {
    //System.out.println(beanOne.returnName());
    System.out.println(componentClass.returnName());
  }
```



##### 테스트

![ComponentTest](..\image\2022-01-23\ComponentTest.PNG)