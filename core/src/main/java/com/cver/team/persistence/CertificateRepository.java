package com.cver.team.persistence;

import com.cver.team.model.data.Certificate;
import com.cver.team.model.entity.Person;
import com.github.andrewoma.dexx.collection.List;


public interface CertificateRepository {

    Certificate saveNewCertificate(Certificate certificate);

    Certificate deleteCertificate(Certificate certificate);

    List<Certificate> getCertificatesForPerson(Person owner);

}
