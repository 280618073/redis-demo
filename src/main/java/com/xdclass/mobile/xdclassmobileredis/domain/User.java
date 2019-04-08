package com.xdclass.mobile.xdclassmobileredis.domain;


import java.io.Serializable;

public class User implements Serializable{

  private String id;
  private String userName;
  private String image;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
