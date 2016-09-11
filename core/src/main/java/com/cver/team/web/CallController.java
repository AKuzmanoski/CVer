package com.cver.team.web;

import com.cver.team.model.entity.Call;
import com.cver.team.services.CallService;
import com.cver.team.web.ResponseExceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by PC on 05/09/2016.
 */
@RestController
@RequestMapping(value = "/call")
public class CallController {
    @Autowired
    CallService callService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Call getCall(@PathVariable String id) {
        Call call = callService.getCall(id);
        if (call == null)
            throw new ResourceNotFoundException();
        return call;
    }

    @RequestMapping(value = "/{id}/apply", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apply(@RequestBody Call call, @PathVariable String id, @RequestParam String cvId) {
        callService.apply(cvId, id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Call insertCall(@RequestBody Call call, @RequestParam String organizationId) {
        return callService.insertCall(call, organizationId);
    }
}
