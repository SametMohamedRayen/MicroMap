package com.pfa.simplifyBack.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class Microservice {
    @Id
   private  String name;
    private String type;
    @Relationship(type = "CALLS", direction = Relationship.Direction.OUTGOING)
    public List<Microservice> target;

    public Microservice() {
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

    public List<Microservice> getTarget() {
        return target;
    }

    public void setTarget(List<Microservice> target) {
        this.target = target;
    }
}
