package com.suhwan.practice.other;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suhwan.practice.vo.Member;

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
