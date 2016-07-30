package com.cver.team.persistence.helper;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by User on 7/30/2016.
 */
@Component
public class MailEncoder {
    private MessageDigest md;

    @PostConstruct
    private void init() throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance("SHA-1");
    }

    public String encode(String email) {
        return new String(md.digest(email.getBytes()));
    }
}
