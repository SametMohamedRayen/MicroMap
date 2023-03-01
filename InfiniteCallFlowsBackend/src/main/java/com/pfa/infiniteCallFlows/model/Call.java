package com.pfa.infiniteCallFlows.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class Call {
    @Id
    @GeneratedValue
    private Long id;
    @TargetNode
    private MyNode targetNode;
    private String type;
    //For Async Calls Only
    private String topic;
    //For Async Calls Only
    private String eventProduced;
    //For Sync Calls Only
    private String api;
    private String description;

    public MyNode getTargetNode() {
        return targetNode;
    }

    public void setTargetNode(MyNode targetNode) {
        this.targetNode = targetNode;
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
