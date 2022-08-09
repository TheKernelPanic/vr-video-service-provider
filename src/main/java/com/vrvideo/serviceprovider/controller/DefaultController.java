package com.vrvideo.serviceprovider.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void index() {}
}
