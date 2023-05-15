package com.pfa.microMap.model;


public class Call {


  private Long id;
  private String issuer;
  private String target;
  private String type;
  //For Async Calls Only
  private String topic;
  //For Async Calls Only
  private String eventProduced;
  //For Sync Calls Only
  private String api;
  private String description;


  public Call(Long id, String issuer, String target, String type, String topic, String eventProduced, String api, String description) {
    this.id = id;
    this.issuer = issuer;
    this.target = target;
    this.type = type;
    this.topic = topic;
    this.eventProduced = eventProduced;
    this.api = api;
    this.description = description;
  }

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getEventProduced() {
    return eventProduced;
  }

  public void setEventProduced(String eventProduced) {
    this.eventProduced = eventProduced;
  }

  public String getApi() {
    return api;
  }

  public void setApi(String api) {
    this.api = api;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
