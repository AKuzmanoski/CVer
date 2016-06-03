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
@RequestMapping("/getToken")
public class CsrfController {

    //ovoj metod treba da se povika ednas na pocetokot na sesijata, so cel
    //korisnikot da go dobie prviot csrf token

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Map<String,String> getCSRFtoken() {
        System.out.println("TEST");
        return Collections.singletonMap("token","granted");
    }
}
