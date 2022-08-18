package com.vrvideo.serviceprovider.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vrvideo")
public class VrVideoController {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(@RequestParam int page) {

    }
}
