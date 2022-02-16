package com.suhwan.practice.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suhwan.practice.vo.type.Status;

import lombok.Data;

@Data
public class Member {

  @JsonProperty("newName")
  private String name;
  
  @JsonProperty("newAge")
  private String age;
  
  private Status status;
}
