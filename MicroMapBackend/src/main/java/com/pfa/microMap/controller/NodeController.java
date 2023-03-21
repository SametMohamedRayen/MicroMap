package com.pfa.microMap.controller;

import com.pfa.microMap.model.Call;
import com.pfa.microMap.model.MyNode;
import com.pfa.microMap.service.NodeService;
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
    @GetMapping("/call")
    public List<Call> findAllCalls(){
        return this.nodeService.findAllCalls();
    }
    @PostMapping("/call")
    public void addCall(
                        @RequestPart("startNode") String startNode,
                        @RequestPart("endNode") String endNode ,
                        @RequestPart("type")  String type ,
                        @RequestPart(value = "topic",required = false) String topic,
                        @RequestPart(value = "eventProduced",required = false)  String eventProduced ,
                        @RequestPart(value = "api",required = false)  String api ,
                        @RequestPart("description")  String description ){
        this.nodeService.addCall(startNode,endNode,type, topic,eventProduced,api,description);
    }
    @PutMapping("/call/{id}")
    public void updateCall(@PathVariable("id")Long id ,
                           @RequestPart("type")  String type ,
                           @RequestPart(value = "topic",required = false) String topic,
                           @RequestPart(value = "eventProduced",required = false)  String eventProduced ,
                           @RequestPart(value = "api",required = false)  String api ,
                           @RequestPart("description")  String description ){
        this.nodeService.updateCall(id,type,topic,eventProduced,api,description);
    }
    @DeleteMapping("call/{id}")
    public void deleteCall(@PathVariable Long id){
        this.nodeService.deleteCall(id);
    }
}
