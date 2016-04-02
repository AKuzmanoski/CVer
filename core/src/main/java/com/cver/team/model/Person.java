package com.cver.team.model;

import com.cver.team.model.literal.Data;
import com.cver.team.model.literal.Identifier;

import java.util.List;

/**
 * Created by User on 3/1/2016.
 */
public class Person extends BaseEntity {
    private Identifier account;
    private List<Data<String>> firstName;
    private List<Data<String>> lastName;
    private List<Data<String>> mail;
    private List<Data<String>> sha1mail;
}