package com.cver.team.services.impl;

import com.cver.team.model.entity.Call;
import com.cver.team.persistence.CallRepository;
import com.cver.team.services.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by PC on 05/09/2016.
 */
@Service
public class CallServiceImpl implements CallService {
    @Autowired
    CallRepository callRepository;

    @Override
    public Call getCall(String id) {
        return callRepository.getCall(id);
    }

    @Override
    public void apply(String cvId, String callId) {
        callRepository.apply(cvId, callId);
    }

    @Override
    public Call insertCall(Call call, String organizationId) {
        return callRepository.insertCall(call, organizationId);
    }
}
