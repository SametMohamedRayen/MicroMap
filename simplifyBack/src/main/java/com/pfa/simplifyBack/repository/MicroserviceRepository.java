package com.pfa.simplifyBack.repository;

import com.pfa.simplifyBack.model.Microservice;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MicroserviceRepository extends Neo4jRepository <Microservice,String>{
}
