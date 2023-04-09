package com.pfa.microMap.service;

import com.pfa.microMap.model.Call;
import com.pfa.microMap.repository.SharedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallService {
    @Autowired
    SharedRepository sharedRepository;
    /**
     * return a list of all avaiable calls
     *
     * @return
     */
    public List<Call> findAllCalls() {
        return this.sharedRepository.findAllCalls();
    }

    /**
     * add a call relationship
     *
     * @param startNode
     * @param endNode
     * @param type
     * @param topic
     * @param eventProduced
     * @param api
     * @param description
     */
    public Call addCall(String startNode
            , String endNode,
                        String type,
                        String topic,
                        String eventProduced,
                        String api,
                        String description) {
        return this.sharedRepository.addCall(startNode, endNode, type, topic, eventProduced, api, description);
    }

    /**
     * update an existing call
     *
     * @param id
     * @param type
     * @param topic
     * @param eventProduced
     * @param api
     * @param description
     */
    public Call updateCall(Long id,
                           String type,
                           String topic,
                           String eventProduced,
                           String api,
                           String description) {
        return this.sharedRepository.updateCall(id, type, topic, eventProduced, api, description);
    }

    /**
     * Delete A call
     *
     * @param id
     */
    public void deleteCall(Long id) {
        this.sharedRepository.deleteCall(id);
    }
}
