package com.suhwan.practice;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.spi.http.HttpHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.suhwan.practice.vo.Member;

public class TestRestTemplate {
  
  final static Logger LOGGER = LoggerFactory.getLogger(TestRestTemplate.class);
  
  public static void main(String[] args){
    
    TestRestTemplate trt = new TestRestTemplate();
    trt.executeRestTemplate();
  }
  
  public void executeRestTemplate(){
    
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://127.0.0.1:8090");
    uriBuilder.path("/getExMember");
    UriComponents uriComponents = uriBuilder.build();

    MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
    map.add("id", "99");
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
    
    RestTemplate r = new RestTemplate();
    ResponseEntity<Member> response = r.exchange(uriComponents.toUriString(), HttpMethod.POST, entity, Member.class);
    
    LOGGER.info("response: {}", response.getBody());
    
  }
}
