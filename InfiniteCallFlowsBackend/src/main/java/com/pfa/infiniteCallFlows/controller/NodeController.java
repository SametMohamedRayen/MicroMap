package com.pfa.infiniteCallFlows.controller;

import com.pfa.infiniteCallFlows.model.MyNode;
import com.pfa.infiniteCallFlows.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/microservice")
public class NodeController {
    @Autowired
    NodeService nodeService;
    @GetMapping
    public List<MyNode> getAll(){
        return this.nodeService.getAll();
    }
    @GetMapping("/names")
    public List<String> getAllNames(){
        return this.nodeService.getName();
    }
    @GetMapping("{name}")
    public MyNode getByName(@PathVariable String name){
        return this.nodeService.getByName(name);
    }
    @PostMapping
    public void add(@RequestPart("name") String name  ,
                    @RequestPart(value = "type") String type

                    ){
        nodeService.add(name,type);
    }
    @DeleteMapping("{name}")
    public void delete(@PathVariable String name){
        this.nodeService.delete(name);
    }
}
