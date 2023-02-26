package com.pfa.simplifyBack.service;

import com.pfa.simplifyBack.model.Microservice;
import com.pfa.simplifyBack.repository.MicroserviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class MicroserviceService {
    @Autowired
    MicroserviceRepository microserviceRepository;

    /**
     * Add a microservice to database
     * @param name
     * @param type
     * @param target
     */
    public void add(String name, String type, String target){
        if(!microserviceRepository.existsById(name)) {
            Microservice microservice = new Microservice();
            microservice.setName(name);
            microservice.setType(type);

            if (target != null) {
                if (!target.isEmpty()) {
                    //split the string to a list of strings wich represents dependencies
                    String[] arrOfStr = target.split(",");
                    microservice.target = new ArrayList<Microservice>();
                    // add each dependency to the target field
                    for (String targetName : arrOfStr) {
                        Microservice targetMicroservice = getByName(targetName);
                        microservice.target.add(targetMicroservice);
                    }
                }
            }
            this.microserviceRepository.save(microservice);
        }
    }

    /**
     * Get one microservice by name
     * @param name
     * @return
     */
    public Microservice getByName(String name){
        return this.microserviceRepository.findById(name).orElse(null);
    }

    /**
     * Get all microservices in database
     * @return
     */
    public List<Microservice>getAll(){
        return microserviceRepository.findAll();
    }

    /**
     * delete a microservice
     * @param name
     */
    public void delete(String name){
        if (microserviceRepository.existsById(name)){
        microserviceRepository.deleteById(name);}
    }
}
