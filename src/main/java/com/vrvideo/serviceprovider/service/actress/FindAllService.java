package com.vrvideo.serviceprovider.service.actress;

import com.vrvideo.serviceprovider.model.Actress;
import com.vrvideo.serviceprovider.repository.ActressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FindAllService extends ActressService{

    @Autowired
    public FindAllService(ActressRepository repository) {
        this.repository = repository;
    }

    public Iterable<Actress> fetch() {
        return this.repository.findAll();
    }
}
