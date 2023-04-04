package com.pfa.microMap.repository;

import com.pfa.microMap.model.Call;
import com.pfa.microMap.model.MyNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Set;

public interface NodeRepository extends Neo4jRepository<MyNode, String> {
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
}
