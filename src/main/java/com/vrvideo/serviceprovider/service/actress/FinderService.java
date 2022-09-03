package com.vrvideo.serviceprovider.service.actress;

import com.vrvideo.serviceprovider.model.Actress;
import com.vrvideo.serviceprovider.repository.ActressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinderService extends ActressService{

    @Autowired
    public FinderService(ActressRepository repository) {
        this.repository = repository;
    }

    public Iterable<Actress> findAll() {
        return this.repository.findAll();
    }

    public Actress findOneBySlug(String slug) {

        /**
         * TODO: Validate slug
         */
        return this.repository.findBySlug(slug);
    }
}
