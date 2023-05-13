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
  @DeleteMapping()
  public void deleteAll() {
    this.nodeService.deleteAll();
  }


}
