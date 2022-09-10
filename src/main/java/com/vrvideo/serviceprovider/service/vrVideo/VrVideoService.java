package com.vrvideo.serviceprovider.service.vrVideo;

import com.vrvideo.serviceprovider.repository.VrVideoRepository;
import org.modelmapper.ModelMapper;

public abstract class VrVideoService {

    protected VrVideoRepository repository;

    protected ModelMapper modelMapper;
}
