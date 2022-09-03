package com.vrvideo.serviceprovider.service.actress;

import com.vrvideo.serviceprovider.exception.DomainValidationException;
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

    public void create(Actress actress) throws DomainValidationException {

        if (actress.getName().length() == 0)  {
            throw new DomainValidationException("Invalid value for actress name");
        }
        this.repository.save(actress);
    }
}
