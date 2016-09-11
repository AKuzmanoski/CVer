package com.cver.team.persistence;

import com.cver.team.model.entity.Call;

/**
 * Created by PC on 05/09/2016.
 */
public interface CallRepository {
    Call getCall(String id);

    void apply(String cvId, String callId);

    Call insertCall(Call call, String organizationId);
}
