#### parameter를 받는 방법



* Controller 생성

```java
  @RequestMapping(value = "/paramTest/{firstPathParam}/{secondPathParam}")
  public void paramTest(@PathVariable String firstPathParam, 
                          @PathVariable String secondPathParam, 
                          @RequestParam String queryParam, 
                          @RequestBody NameValuePair nameValuePair){
    
    System.out.println("firstPathParam: " + firstPathParam);
    System.out.println("secondPathParam: " + secondPathParam);
    System.out.println("queryParam: " + queryParam);
    System.out.println("requestBody: " + nameValuePair);
  }
```

코드에서 볼 수 있듯이, @PathVariable 을 사용하여 path parameter를

@RequestParam을 사용하여 query parameter를 @RequestBody를 사용하여 body에 담긴 파라미터를 받을 수 있다.



* NameValuePair

```java
@Data
@ToString
public class NameValuePair {
  private String name;
  private String value;
}
```



* 테스트

![ParameterTest](..\image\2022-01-15\ParameterTest.PNG)