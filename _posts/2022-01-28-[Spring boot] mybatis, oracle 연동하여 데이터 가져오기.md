

* Oracle 에 접속할 계정 생성

```sql
CREATE USER sh_test IDENTIFIED BY 1234;

GRANT CONNECT TO sh_test;
GRANT RESOURCE TO sh_test;
GRANT DBA TO sh_test;

COMMIT;
```



* sh_test 계정에 접속한 후 테이블 생성 및 더미데이터 삽입

```sql
CREATE TABLE MEMBER (
    NAME VARCHAR2(20),
    AGE VARCHAR2(20)
);
INSERT INTO MEMBER VALUES ('홍길동', '30');
INSERT INTO MEMBER VALUES ('이순신', '30');
SELECT * FROM MEMBER;
COMMIT;
```



##### Spring boot

oracle 연동



* pom.xml  > dependency 추가

```xml
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
		</dependency>

		<!-- mybatis -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.4.0</version>
		</dependency>
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>1.3.0</version>
		</dependency>

		<!-- oracle jdbc -->
		<dependency>
			<groupId>com.oracle.jdbc</groupId>
			<artifactId>ojdbc8</artifactId>
			<version>12.2.0.1</version>
		</dependency>
```



* application.properties

```properties
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/xe
spring.datasource.username=sh_test
spring.datasource.password=1234
```



* bean 등록

```java
  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception {
    SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
    sqlSessionFactory.setDataSource(datasource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:sql/*.xml"));
    return sqlSessionFactory.getObject();
  }
  @Bean
  public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
```



* sql.xml

```xml-dtd
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.suhwan.practice.dao.MemberDao">

	<resultMap type="com.suhwan.practice.vo" id="memberResult">
		<result property="name"  column="NAME"/>
		<result property="age"   column="AGE"/>
	</resultMap>

	<select	id="getUserList" resultMap="memberResult">
		SELECT *
		  FROM MEMBER;
	</select>
</mapper>
```



* Dao, Service

```java
// dao
@Repository
public interface MemberDao {

  public Member getUserList();
}

// service
@Autowired
private MemberDao memberDao;

public void getMemberList() {
  System.out.println(memberDao.getUserList());
}
```



? repository로 등록한  bean을 못찾음?

```java
Field memberDao in com.suhwan.practice.service.TaskService required a bean of type 'com.suhwan.practice.dao.MemberDao' that could not be found.

The injection point has the following annotations:
	- @org.springframework.beans.factory.annotation.Autowired(required=true)


Action:

Consider defining a bean of type 'com.suhwan.practice.dao.MemberDao' in your configuration.
```



~~여기서 개 뻘짓 1시간~~

* Application

```java
@SpringBootApplication(scanBasePackages = {"com.suhwan.practice"})
@MapperScan(basePackages = {"com.suhwan.practice"}, annotationClass=Repository.class)
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
```

> @MapperScan(basePackages = {"com.suhwan.practice"}, annotationClass=Repository.class) 이게 핵심 포인트



* 결과..

![result](..\image\2022-01-28\result.PNG)