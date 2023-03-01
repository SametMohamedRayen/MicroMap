package com.pfa.infiniteCallFlows.repository;

import com.pfa.infiniteCallFlows.model.Call;
import com.pfa.infiniteCallFlows.model.MyNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CallRepository extends Neo4jRepository<Call,Long> {
}
