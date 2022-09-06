package com.vrvideo.serviceprovider.service.actress;

import com.vrvideo.serviceprovider.model.exception.DomainAlreadyExistsException;
import com.vrvideo.serviceprovider.model.exception.DomainValidationException;
import com.vrvideo.serviceprovider.model.Actress;
import com.vrvideo.serviceprovider.repository.ActressRepository;
import com.vrvideo.serviceprovider.utils.SlugGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class CreateService extends ActressService{

    @Autowired
    public CreateService(ActressRepository repository) {
        this.repository = repository;
    }

    public void create(Actress actress) throws DomainValidationException, DomainAlreadyExistsException {

        if (actress.getName().length() == 0)  {
            throw new DomainValidationException("Invalid value for actress name");
        }
        if (actress.getSlug() == null || actress.getSlug().isEmpty()) {
            actress.setSlug(SlugGenerator.generate(actress.getName()));
        }
        Actress actressFound = this.repository.findBySlug(actress.getSlug());
        if (actressFound != null) {
            throw new DomainAlreadyExistsException(String.format("Actress already exists with slug %s", actress.getSlug()));
        }
        actress.setUuid(String.valueOf(UUID.randomUUID()));
        this.repository.save(actress);
    }
}
