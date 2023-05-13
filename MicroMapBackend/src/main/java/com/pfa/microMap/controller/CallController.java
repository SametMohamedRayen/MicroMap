package com.pfa.microMap.controller;

import com.pfa.microMap.model.Call;
import com.pfa.microMap.service.CallService;
import com.pfa.microMap.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/call")
public class CallController {
    @Autowired
    CallService callService;
    @GetMapping()
    public List<Call> findAllCalls() {
        return this.callService.findAllCalls();
    }

    @PostMapping()
    public Call addCall(
            @RequestPart("startNode") String startNode,
            @RequestPart("endNode") String endNode,
            @RequestPart("type") String type,
            @RequestPart(value = "topic", required = false) String topic,
            @RequestPart(value = "eventProduced", required = false) String eventProduced,
            @RequestPart(value = "api", required = false) String api,
            @RequestPart(value = "description", required = false) String description) {
        return this.callService.addCall(startNode, endNode, type, topic, eventProduced, api, description);
    }

    @PutMapping("/{id}")
    public Call updateCall(@PathVariable("id") Long id,
                           @RequestPart(value = "type", required = false) String type,
                           @RequestPart(value = "topic", required = false) String topic,
                           @RequestPart(value = "eventProduced", required = false) String eventProduced,
                           @RequestPart(value = "api", required = false) String api,
                           @RequestPart(value = "description", required = false) String description) {
        return this.callService.updateCall(id, type, topic, eventProduced, api, description);
    }

    @DeleteMapping("/{id}")
    public void deleteCall(@PathVariable Long id) {
        this.callService.deleteCall(id);
    }
  @DeleteMapping()
  public void deleteAllCalls() {
    this.callService.deleteAllCalls();
  }
}
