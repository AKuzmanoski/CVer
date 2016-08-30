package com.cver.team.web;

import com.cver.team.model.data.Certificate;
import com.cver.team.services.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PC on 25/08/2016.
 */
@RestController
@RequestMapping(value = "/certificate")
public class CertificateController {
    @Autowired
    CertificateService certificateService;

    @RequestMapping(value = "/{id}")
    public Certificate getCertificate(@PathVariable String id) {
        return certificateService.getCertificate(id);
    }
}
