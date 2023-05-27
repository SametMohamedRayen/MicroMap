package com.pfa.microMap.repository;

import com.pfa.microMap.model.Call;
import com.pfa.microMap.model.MyNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SharedRepository extends Neo4jRepository<MyNode, String> {
  @Query("MATCH (n) RETURN n.name as name")
  public Set<String> getAllNames();

  @Query("MATCH (i:MyNode)-[r]->(t:MyNode)\n" +
    "RETURN r.id AS id , " +
    "i.name AS issuer , " +
    "t.name AS target, " +

    " r.type AS type , " +
    "r.topic AS topic ," +
    " r.eventProduced AS eventProduced ," +
    " r.api AS api , " +
    "r.description AS description")
  public List<Call> findAllCalls();

  @Query(" MATCH (a:MyNode), (b:MyNode)\n" +
    "WHERE a.name = $startNode AND b.name = $endNode\n" +
    "CREATE (a)-[r:CALLS ]->(b)\n" +
    "SET r.id = ID(r) , r.type = $type, r.topic = $topic , r.eventProduced = $eventProduced , r.api = $api , r.description = $description\n" +
    "RETURN r")
  public Call addCall(String startNode
    , String endNode,
                      String type,
                      String topic,
                      String eventProduced,
                      String api,
                      String description);

  @Query("MATCH ()-[r]->()\n" +
    "WHERE r.id = $id\n" +
    "SET  r.type = $type, r.topic = $topic , r.eventProduced = $eventProduced , r.api = $api , r.description = $description\n" +
    "RETURN r"
  )
  public Call updateCall(Long id, String type, String topic, String eventProduced, String api,
                         String description);

  @Query("MATCH ()-[r]->()\n" +
    "WHERE r.id = $id\n" +
    "DELETE r")
  public void deleteCall(Long id);

  @Query("MATCH ()-[r]->()\n" +
    "DELETE r")
  public void deleteAllCalls();

  @Query("UNWIND $calls AS call " +
    "MATCH (a:MyNode), (b:MyNode) " +
    "WHERE a.name = call.startNode AND b.name = call.endNode " +
    "CREATE (a)-[r:CALLS]->(b) " +
    "SET r.id = ID(r), r.type = call.type, r.topic = call.topic, r.eventProduced = call.eventProduced, " +
    "r.api = call.api, r.description = call.description "
  )
  void addCalls(@Param("calls") List<Map<String, Object>> calls);

  @Query(
    "UNWIND $calls AS call " +
      "OPTIONAL MATCH (a:MyNode {name: call.startNode}) " +
      "OPTIONAL MATCH (b:MyNode {name: call.endNode}) " +
      "WITH call, a, b "+
      "WHERE a IS NULL OR b IS NULL " +
      "RETURN call.startNode + ' -> ' + call.endNode AS unmatchedNodes"
  )
  Set<String> getUnmatchedNodes(@Param("calls") List<Map<String, Object>> calls);
}
