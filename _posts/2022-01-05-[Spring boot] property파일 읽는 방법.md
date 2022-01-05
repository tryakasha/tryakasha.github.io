#### @Value 사용



* application.properties 파일  작성

```properties
first.property.value=first
second.property.value=second
```



* Property 를 불러올 서비스 생성

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class PropertyManager {
  
  @Value("${first.property.value}")
  private String firstValue;
  
  @Value("${second.property.value}")
  private String secondValue;
  
}
```



* service 생성

``` java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  @Autowired
  private PropertyManager propertyManager;
  
  public String getProperty(String key) {
    
    if(key.equals("first")){
      return propertyManager.getFirstValue();
    } else if (key.equals("second")) {
      return propertyManager.getSecondValue();
    } else {
      return null;
    }
  }
}
```



* controller 생성

``` java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suhwan.practice.service.TaskService;

@RestController
public class IndexController {
  
  @Autowired
  private TaskService taskService;

  @RequestMapping(value = "/getProperty")
  public String getPropertyMethod(@RequestParam String value){
    return taskService.getProperty(value);
  }
}
```



* 테스트 결과

![ValueResult](.\..\image\2022-01-05\ValueResult.PNG)



##### 다른 properties 파일을 읽고 싶은 경우



테스트를 위해 properties 파일 분리

* application.properties

```properties
first.property.value=first
```

* other.properties

```properties
second.property.value=second
```



* 간단하게 @PropertySource("classpath:other.properties") 어노테이션만 추가해주면 된다.

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
@PropertySource("classpath:other.properties")
public class PropertyManager {
  
  @Value("${first.property.value}")
  private String firstValue;
  
  @Value("${second.property.value}")
  private String secondValue;
}
```



---



#### @PropertySource, Environment 사용



* Property 를 불러올 서비스 생성

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:other.properties")
public class EnvironmentPropertyManager {
  
  @Autowired
  private Environment environment;
  
  public String getProperty(String key){
    return environment.getProperty(key);
  }
}
```



* service 생성

```java
public String getEnvProperty(String key){
    return envPropertyManager.getProperty(key);
}
```



* controller 생성

```java
@RequestMapping(value = "/getEnvProperty")
  public String getEnvPropertyMethod(@RequestParam String value){
    System.out.println(value);
    return taskService.getEnvProperty(value);
  }
```



* 테스트 결과

![EnvResult](.\..\image\2022-01-05\EnvResult.PNG)



* 상수로 선언하여 사용하면 좋아보임

```java
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
```



* service 변경

```java
public String getEnvProperty(String key){
    if(key.equals("first")){
      return envPropertyManager.getProperty(EnvironmentPropertyManager.FIRST_VALUE);
    } else if (key.equals("second")) {
      return envPropertyManager.getProperty(EnvironmentPropertyManager.SECOND_VALUE);
    } else {
      return null;
    }
  }
```