package com.cver.team.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Dimitar on 6/3/2016.
 */
@Controller
public class RegistrationController {

@RequestMapping(value = "/testPost", method = RequestMethod.POST)
@ResponseBody
public Map<String,String> post() {
    System.out.println("a post went through");
    return Collections.singletonMap("key","value");
}
}
