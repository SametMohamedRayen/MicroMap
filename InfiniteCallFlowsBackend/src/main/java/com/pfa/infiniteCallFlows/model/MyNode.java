package com.pfa.infiniteCallFlows.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
@Node
public class MyNode {
    @Id
    private  String name;
    private String type;
    @Relationship(type = "CALLS", direction = Relationship.Direction.OUTGOING)
    public List<Call> target;
    public MyNode() {
    }

    public MyNode(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Call> getTarget() {
        return target;
    }

    public void setTarget(List<Call> target) {
        this.target = target;
    }
}
