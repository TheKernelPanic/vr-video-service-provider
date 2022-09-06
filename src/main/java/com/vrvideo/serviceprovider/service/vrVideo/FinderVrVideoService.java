package com.vrvideo.serviceprovider.service.vrVideo;

import com.vrvideo.serviceprovider.model.VrVideo;
import com.vrvideo.serviceprovider.repository.VrVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class FinderVrVideoService extends VrVideoService {

    @Autowired
    public FinderVrVideoService(VrVideoRepository repository) {
        this.repository = repository;
    }

    public Iterable<VrVideo> findAll() {
        return this.repository.findAll();
    }
}
