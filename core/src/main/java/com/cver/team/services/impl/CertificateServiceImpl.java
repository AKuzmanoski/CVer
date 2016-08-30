package com.cver.team.services.impl;

import com.cver.team.model.data.Certificate;
import com.cver.team.persistence.CertificateRepository;
import com.cver.team.services.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by PC on 25/08/2016.
 */
@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    CertificateRepository certificateRepository;

    @Override
    public Certificate getCertificate(String id) {
        return certificateRepository.getCertificate(id);
    }
}
