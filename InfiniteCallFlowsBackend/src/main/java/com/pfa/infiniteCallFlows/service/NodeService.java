package com.pfa.infiniteCallFlows.service;

import com.pfa.infiniteCallFlows.model.MyNode;
import com.pfa.infiniteCallFlows.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class NodeService {
    @Autowired
    NodeRepository nodeRepository;


    /**
     * Add a microservice to database
     * @param name
     * @param
     */
    public void add(String name,  String type){
        if(!nodeRepository.existsById(name)) {
            MyNode newNode =new MyNode(name,type);
            this.nodeRepository.save(newNode);
        }
    }

    /**
     * Get one microservice by name
     * @param name
     * @return
     */
    public MyNode getByName(String name){
        return this.nodeRepository.findById(name).orElse(null);
    }
    public List<String> getName(){
    return nodeRepository.getAllNames();
    }
    /**
     * Get all microservices in database
     * @return
     */
    public List<MyNode>getAll(){
        return this.nodeRepository.findAll();
    }

    /**
     * delete a microservice
     * @param name
     */
    public void delete(String name){
        if (nodeRepository.existsById(name)){
        nodeRepository.deleteById(name);}
    }
}
