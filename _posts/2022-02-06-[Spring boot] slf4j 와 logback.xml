**SLF4J (simple logging facade for java)**

다양한 로깅 프레임워크에 대한 추상화 역할을 하는 라이브러리



**Log Level** (아래로 내려갈수록 심각한 오류를 의미)

* trace

  모든 레벨에 대한 로깅이 추적되어 보통 개발단계에서 사용

* debug

  개발 단게에서 사용하고 SQL로깅 가능

* info

  운영에 참고할만한 사항, 중요한 비즈니스 프로세스가 완료

* warn

  로직상에 유효성을 확인, 예상 가능한 문제로 예외처리, 주의해야할 부분

* error

  예상하지 못한 심각한 문제가 발생하는 경우



 **logback**은 slf4j 의 구현체로 로그에 대한 상세 설정을 할 수 있다.

> 뻘짓 포함한 자세한 설명은 아래에서 다시 하겠다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration debug="true">
    
  <appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <Pattern>[%d{yyyy-MM-dd HH:mm:ss}:%-3relative][%thread] %-5level %M %logger{35} - %msg%n</Pattern>
    </encoder>
  </appender>
    
  <appender name="basicFileAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>./server.log</file>
    <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
      <pattern>[%d{yyyy-MM-dd HH:mm:ss}:%-3relative][%thread] %-5level %M %logger{35} - %msg%n</pattern>
    </encoder>
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>error</level>
      <onMatch>ACCEPT</onMatch>
      <onMismatch>DENY</onMismatch>
    </filter>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>server.%d{yyyyMMdd}.%i.log</fileNamePattern>
      <!-- 50MB를 초과하면 새로운 로그파일 생성. ex) server.200903.2.log -->
      <maxFileSize>50MB</maxFileSize>
      <!-- 60일이 지난 파일은 삭제한다 -->
      <!-- <maxHistory>60</maxHistory> -->
    </rollingPolicy>
  </appender>

  <logger name="FileLog" level="info" additivity="false">
    <appender-ref ref="basicFileAppender" />
  </logger>

  <root level="debug">
    <appender-ref ref="Console" />
    <!-- <appender-ref ref="basicFileAppender" /> -->
  </root>
</configuration>

```



* <configuration> : configuration 안에 추가할 기능을 넣어준다.

* <appender> : 콘솔에 출력되는 형식을 지정한다. 위의 작성한 xml에서는 2개의 (console, file) appender를 작성 했다.

  > name="basicFileAppender" appender를 보면, class에 RolloingFileAppender를 사용하였는데 이는 파일 용량, 개수를 지정할 수 있는 기능을 제공

* <encoder> : 인코딩한 로그를 기록하며, <pattern> 패턴에 따라 기록한다.



* <logger> : name에 따라 로깅 방식을 지정할 수 있다. name을 Test로 지정한다면, getLogger("Test")로 불러와서 사용해야 설정한 내용을 불러올 수 있다.

  level 은 ConsoleLog 의 로그레벨을 지정하며, additivity 가 true로 되어있으면 root를 상속받는다는 의미

  즉,  <logger name="FileLog" level="info" additivity="true">  </logger> 로 내용을 비워 지정한다면 결국 root랑 다를게 없다.

* <root> : default 로그 설정이다. 위의 설정은 Console Appender 만 추가하여 로그를 남길때 console에만 로그를 남게 하였다.



##### 테스트

```java
public class Logging {

  public static void main(String[] args) {
    Logging log = new Logging();
    log.logTest();
  }
  
  private void logTest(){
    Logger logger = LoggerFactory.getLogger(Logging.class);
    
    logger.trace("traceLog");
    logger.debug("debugLog");
    logger.info("infoLog");
    logger.warn("warnLog");
    logger.error("errorLog");
  }

}
```



![LoggingTest_1](..\image\2022-02-06\LoggingTest_1.PNG)



로그를 불러올때 Logger logger = LoggerFactory.getLogger(Logging.class); 를 사용했고(log Name을 따로 지정하지 않았음) <root> 태그 안에 Console만 추가되어 있기에 콘솔에 로그를 남기고 있는 모습.

**trace로그가 남지않는 이유는 root level 을 debug**로 설정하였기 때문이다.

즉,  아래 설정이 적용되고 있다.

```xml
  <appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <Pattern>[%d{yyyy-MM-dd HH:mm:ss}:%-3relative][%thread] %-5level %M %logger{35} - %msg%n</Pattern>
    </encoder>
  </appender>
  
  <root level="debug">
    <appender-ref ref="Console" />
    <!-- <appender-ref ref="basicFileAppender" /> -->
  </root>
```



```xml
  <appender name="basicFileAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>./server.log</file>
    <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
      <pattern>[%d{yyyy-MM-dd HH:mm:ss}:%-3relative][%thread] %-5level %M %logger{35} - %msg%n</pattern>
    </encoder>
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>error</level>
      <onMatch>ACCEPT</onMatch>
      <onMismatch>DENY</onMismatch>
    </filter>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>server.%d{yyyyMMdd}.%i.log</fileNamePattern>
      <!-- 50MB를 초과하면 새로운 로그파일 생성. ex) server.200903.2.log -->
      <maxFileSize>50MB</maxFileSize>
      <!-- 60일이 지난 파일은 삭제한다 -->
      <!-- <maxHistory>60</maxHistory> -->
    </rollingPolicy>
  </appender>

  <logger name="FileLog" level="info" additivity="false">
    <appender-ref ref="basicFileAppender" />
  </logger>
```

위의 로거를 사용하여 로그를 남겨보겠다.



참고로, FileLog는 basicFileAppender를 참조하고있고, basicFileAppender는 파일명을 <file>./server.log</file> 로 지정하고있고,

<filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>error</level>
      <onMatch>ACCEPT</onMatch>
      <onMismatch>DENY</onMismatch>
 </filter>

설정을 통하여 error 로그만 파일에 기록하고있다.



##### 테스트

> getLogger("FileLog") 를 통하여 Logger를 가져옴.

````java
public class Logging {

  public static void main(String[] args) {
    Logging log = new Logging();
    log.logTest();
  }
  
  private void logTest(){
    Logger logger = LoggerFactory.getLogger("FileLog");
    
    logger.trace("traceLog");
    logger.debug("debugLog");
    logger.info("infoLog");
    logger.warn("warnLog");
    logger.error("errorLog");
  }

}
````



![LogError](..\image\2022-02-06\LogError.PNG)



>  ch.qos.logback.core.rolling.TimeBasedRollingPolicy 대신 SizeAndTimeBasedRollingPolicy 로 변경하여 위의 에러를 해결했습니다..



XML 설정 변경 후

```xml
    <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
      <fileNamePattern>server.%d{yyyyMMdd}.%i.log</fileNamePattern>
      <!-- 50MB를 초과하면 새로운 로그파일 생성. ex) server.200903.2.log -->
      <maxFileSize>50MB</maxFileSize>
      <!-- 60일이 지난 파일은 삭제한다 -->
      <!-- <maxHistory>60</maxHistory> -->
    </rollingPolicy>
```



##### 실행 결과

![FileLog](..\image\2022-02-06\FileLog.PNG)



파일생성되고 필터에 맞게 error 로그만 기록되는것을 확인할 수 있음



```xml
  <root level="debug">
    <appender-ref ref="Console" />
    <appender-ref ref="basicFileAppender" />
  </root>
```



root 설정에 두개의 appender를 추가하여 테스트

![LoggingTest_2](..\image\2022-02-06\LoggingTest_2.PNG)

콘솔, 파일 모두 저장됨

몇번의 테스트를 해보니, 두개를 추가한 경우 앞 로그패던을 따라 뒤의 Appender에서 사용하는듯 보임.

~~왜지..~~



참고로, 아래처럼 두개를 추가하지않고 additivity를 true로 하고 getLogger("FileLog") 해도 동일한 결과.

```xml
  <logger name="FileLog" level="info" additivity="true">
    <appender-ref ref="basicFileAppender" />
  </logger>

  <root level="debug">
    <appender-ref ref="Console" />
  </root>
```

