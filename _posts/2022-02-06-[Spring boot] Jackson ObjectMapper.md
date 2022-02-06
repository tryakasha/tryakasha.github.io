

Jackson objectMapper 클래스를 이용하여 Java Object <-> JSON 상호 변환이 가능하다.

Java Object -> JSON 를 **Serialize** JSON -> Java Object 를 **Desirialize**라 한다.



> 테스트 클래스로 작성하였음

##### Serialize

```java
  private String Serialize() throws JsonProcessingException{
    ObjectMapper objectMapper = new ObjectMapper();
    Member member = new Member();
    member.setAge("30");
    member.setName("홍길동");
    
    String memberJson = objectMapper.writeValueAsString(member);
    System.out.println("memberJson: " + memberJson);
    
    return memberJson;
  }
```



##### 결과

![Serialize](..\image\2022-02-06\Serialize.PNG)



##### Deserialize

```java
  private Member Deserialize(String memberJson) throws JsonMappingException, JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    Member member = objectMapper.readValue(memberJson, Member.class);
    
    System.out.println("Java Object: " + member);
    return member;
  }
```



##### 결과

![Deserialize](..\image\2022-02-06\Deserialize.PNG)





##### Serialize 하는 과정에서 null을 제거하고 싶은경우?

> objectMapper.setSerializationInclusion(Include.NON_NULL) 사용

```java
  private String Serialize() throws JsonProcessingException{
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(Include.NON_NULL);
    Member member = new Member();
    //member.setAge("30");
    member.setName("홍길동");
    
    String memberJson = objectMapper.writeValueAsString(member);
    System.out.println("memberJson: " + memberJson);
    
    return memberJson;
  }
```



##### 결과

![ExceptNull](..\image\2022-02-06\ExceptNull.PNG)



##### Deserialize 하는 과정에서 모르는 필드를 무시하고 싶은경우?

> objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) 사용
>
> 참고로 위의 config설정을 생략할 경우 모르는필드가 존재한다면 에러가 발생

```java
  private Member Deserialize(String memberJson) throws JsonMappingException, JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    
    // 임의로 JSON 생성
    String includeUnkwonField = "{\"name\":\"홍길동\", \"addr\":\"서울시\"}";
    Member member = objectMapper.readValue(includeUnkwonField, Member.class);
    
    System.out.println("Java Object: " + member);
    return member;
  }
```



##### 결과

![UnkwonField](..\image\2022-02-06\UnkwonField.PNG)



##### @JsonProperty

> Serialize, Deserialize 상호변환 시 필드명 지정

```java
@Data
public class Member {

  @JsonProperty("newName")
  private String name;
  
  @JsonProperty("newAge")
  private String age;
}
```

##### 테스트 소스

```java
public class JacksonObjectMapper {

  public static void main(String[] args) throws JsonProcessingException {
    JacksonObjectMapper jom = new JacksonObjectMapper();
    String memberJson = jom.Serialize();
    Member member = jom.Deserialize(memberJson);
  }

  private Member Deserialize(String memberJson) throws JsonMappingException, JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    
    //String includeUnkwonField = "{\"name\":\"홍길동\", \"addr\":\"서울시\"}";
    Member member = objectMapper.readValue(memberJson, Member.class);
    
    System.out.println("Java Object: " + member);
    return member;
  }

  private String Serialize() throws JsonProcessingException{
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(Include.NON_NULL);
    Member member = new Member();
    //member.setAge("30");
    member.setName("홍길동");
    
    String memberJson = objectMapper.writeValueAsString(member);
    System.out.println("memberJson: " + memberJson);
    
    return memberJson;
  }
}
```



##### 결과



![JsonProperty](..\image\2022-02-06\JsonProperty.PNG)