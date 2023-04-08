package com.pfa.microMap.controller;

import com.pfa.microMap.model.Call;
import com.pfa.microMap.model.MyNode;
import com.pfa.microMap.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/node")
// test
public class NodeController {
    @Autowired
    NodeService nodeService;

    @GetMapping
    public List<MyNode> getAll() {
        return this.nodeService.getAll();
    }

    @GetMapping("/names")
    public Set<String> getAllNames() {
        return this.nodeService.getName();
    }

    @GetMapping("{name}")
    public MyNode getByName(@PathVariable String name) {
        return this.nodeService.getByName(name);
    }

    @PostMapping
    public MyNode add(@RequestPart("name") String name,
                      @RequestPart(value = "type") String type

    ) {
        return this.nodeService.add(name, type);
    }

    @DeleteMapping("{name}")
    public void delete(@PathVariable String name) {
        this.nodeService.delete(name);
    }

    @GetMapping("/call")
    public List<Call> findAllCalls() {
        return this.nodeService.findAllCalls();
    }

    @PostMapping("/call")
    public Call addCall(
            @RequestPart("startNode") String startNode,
            @RequestPart("endNode") String endNode,
            @RequestPart("type") String type,
            @RequestPart(value = "topic", required = false) String topic,
            @RequestPart(value = "eventProduced", required = false) String eventProduced,
            @RequestPart(value = "api", required = false) String api,
            @RequestPart(value = "description", required = false) String description) {
        return this.nodeService.addCall(startNode, endNode, type, topic, eventProduced, api, description);
    }

    @PutMapping("/call/{id}")
    public Call updateCall(@PathVariable("id") Long id,
                           @RequestPart(value = "type", required = false) String type,
                           @RequestPart(value = "topic", required = false) String topic,
                           @RequestPart(value = "eventProduced", required = false) String eventProduced,
                           @RequestPart(value = "api", required = false) String api,
                           @RequestPart(value = "description", required = false) String description) {
        return this.nodeService.updateCall(id, type, topic, eventProduced, api, description);
    }

    @DeleteMapping("call/{id}")
    public void deleteCall(@PathVariable Long id) {
        this.nodeService.deleteCall(id);
    }
}
