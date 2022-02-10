* Spring 3부터 지원
* 스프링에서 제공하는 http 통신에 유용하게 쓸 수 있는 템플릿
* HTTP 서버와의 통신을 단순화하고 RESTful 원칙을 지킨다. (json, xml을 쉽게 받을 수 있음)



| Method          | HTTP Method | Description                                                  |
| --------------- | ----------- | ------------------------------------------------------------ |
| getForObject    | GET         | 주어진 URL 주소로 HTTP GET 요청을 보내며, 결과를 객체로 받는다. |
| getForEntity    | GET         | 주어진 URL 주소로 HTTP GET 요청을 보내며, 결과를 ResponseEntity로 받는다. |
| postForLocation | POST        | POST로 요청을 보내고, 결과로 헤더에 저장된 URI를 받는다.     |
| postForObject   | POST        | POST로 요청을 보내고, 결과를 객체로 받는다.                  |
| postForEntity   | POST        | POST로 요청을 보내고, 결과를 ResponseEntity로 받는다.        |
| delete          | DELETE      | 주어진 URL 주소로 HTTP DELETE 메소드를 실행한다.             |
| headForHeaders  | HEADER      | 헤더의 모든 정보를 얻을 수 있으며, HTTP HEAD 메소드를 사용한다. |
| put             | PUT         | 주어진 URL 주소로 HTTP PUT 요청을 보낸다.                    |
| patchForObject  | PATCH       | 주어진 URL 주소로 HTTP PATCH 요청을 보낸다.                  |
| optionsForAllow | OPTIONS     | 주어진 URL 주소에서 **지원하는 HTTP 메소드를 조회**한다.     |
| exchange        | **ANY**     | HTTP 헤더를 새로 만들 수 있고, 어떤 HTTP 메소드도 사용 가능하다. |
| excute          | **ANY**     | Request/Response 콜백을 수정할 수 있다.                      |



> RESTful 방식에서 put 과 patch 의 용도 차이는 put은 전체 자원 교체시 사용하며, patch는 자원의 부분 교체시 사용한다.



#### resttemplate 테스트를 위한 서버 작성

##### Controller

```java
@RestController
public class RestTestController {
  
  @Autowired
  private RestService restService;
  
  @RequestMapping(method = RequestMethod.POST, value = "getExMember")
  public Member getMember(@RequestParam("id") String id){
    return restService.getTestMember(id);
  }
}
```



##### Service

```java
@Service
public class RestService {
  
  public Member getTestMember(String id){
    Member member = new Member();
    member.setName("호랑이");
    member.setAge("15");
      //body에 받은 parameter를 제대로 수신하고 있는지 확인차 코드를 작성하였음.
    return id.equals("99")?member:null;
  }
}
```



##### RestTemplate 실행을 위한 소스코드

> postForEntity, postForObject 로 해도 상관없지만 저는 exchange로 작성하였습니다.

```java
  public void executeRestTemplate(){
    
    // 주소 작성
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://127.0.0.1:8090");
    // path 추가
    uriBuilder.path("/getExMember");
    UriComponents uriComponents = uriBuilder.build();

    // String 으로 parameter를 보내기위하여 MultiValueMap 을 사용하여 id값을 세팅
    MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
    map.add("id", "99");
    HttpHeaders headers = new HttpHeaders();
      
    // entity 작성
    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
    
    RestTemplate r = new RestTemplate();
      // 순서대로 uri, method, request, responseType
    ResponseEntity<Member> response = r.exchange(uriComponents.toUriString(), HttpMethod.POST, entity, Member.class);
    
    LOGGER.info("response: {}", response.getBody());
    
  }
```





##### 실행 결과

* Client

![ClientLog](..\image\2022-02-10\ClientLog.PNG)



* Server

![ServerLog](..\image\2022-02-10\ServerLog.PNG)