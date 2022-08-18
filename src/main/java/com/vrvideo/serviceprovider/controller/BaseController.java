package com.vrvideo.serviceprovider.controller;

import org.modelmapper.ModelMapper;

public abstract class BaseController {

    protected ModelMapper modelMapper;

    public BaseController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
