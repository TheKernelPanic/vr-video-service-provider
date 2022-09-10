package com.vrvideo.serviceprovider.service.actress;

import com.vrvideo.serviceprovider.repository.ActressRepository;
import org.modelmapper.ModelMapper;

public abstract class ActressService {
    protected ActressRepository repository;

    protected ModelMapper modelMapper;
}
