package com.cver.team.persistence;

import com.cver.team.model.data.Certificate;
import com.cver.team.model.data.CertificateCard;
import com.cver.team.model.entity.Person;
import com.github.andrewoma.dexx.collection.List;


public interface CertificateRepository {

    CertificateCard saveNewCertificate(CertificateCard certificateCard);

    CertificateCard deleteCertificate(CertificateCard certificateCard);

    List<CertificateCard> getCertificatesForPerson(Person owner);

    Certificate getCertificate(String id);
}
