package com.suhwan.practice.vo.type;

public enum Status {
  ACTIVE(1),
  SUSPEND(2),
  DELETED(3);
  
  private int code;
  
  Status(int code){
    this.code = code;
  }
  
  public int getCode(){
    return code;
  }
  
  public static Status getStatusByCode(int code){
    for(Status status : Status.values()){
      if(status.getCode() == code){
        return status;
      }
    }
    return null;
  }
}
