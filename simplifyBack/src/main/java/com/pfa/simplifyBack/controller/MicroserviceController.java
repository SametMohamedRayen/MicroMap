package com.pfa.simplifyBack.controller;

import com.pfa.simplifyBack.model.Microservice;
import com.pfa.simplifyBack.service.MicroserviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/microservice")
public class MicroserviceController {
    @Autowired
    MicroserviceService microserviceService;
    @GetMapping
    public List<Microservice> getAll(){
        return this.microserviceService.getAll();
    }
    @GetMapping("{name}")
    public Microservice getByName(@PathVariable String name){
        return this.microserviceService.getByName(name);
    }
    @PostMapping
    public void add(@RequestPart("name") String name , @RequestPart("type") String type , @RequestPart(value = "target",required = false) String target){
        microserviceService.add(name,type,target);
    }
    @DeleteMapping("{name}")
    public void delete(@PathVariable String name){
        this.microserviceService.delete(name);
    }
}
