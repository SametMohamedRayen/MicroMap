package com.pfa.infiniteCallFlows.repository;

import com.pfa.infiniteCallFlows.model.MyNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface NodeRepository extends Neo4jRepository <MyNode,String>{
    @Query("MATCH (n) RETURN n.name as name")
    public List<String>getAllNames();
}
