package com.vrvideo.serviceprovider.service.actress;

import com.vrvideo.serviceprovider.model.Actress;
import com.vrvideo.serviceprovider.repository.ActressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateService extends ActressService{

    @Autowired
    public CreateService(ActressRepository repository) {
        this.repository = repository;
    }

    public void create(Actress actress) {
        this.repository.save(actress);
    }
}
