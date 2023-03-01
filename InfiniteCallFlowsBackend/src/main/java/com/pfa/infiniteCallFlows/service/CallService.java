package com.pfa.infiniteCallFlows.service;

import com.pfa.infiniteCallFlows.repository.CallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallService {
    @Autowired
    CallRepository callRepository;

    public void add(){

    }
}
