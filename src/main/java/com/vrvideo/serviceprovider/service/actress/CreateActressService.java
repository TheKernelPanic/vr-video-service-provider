package com.vrvideo.serviceprovider.service.actress;

import com.vrvideo.serviceprovider.dto.ActressDto;
import com.vrvideo.serviceprovider.model.exception.DomainAlreadyExistsException;
import com.vrvideo.serviceprovider.model.exception.DomainValidationException;
import com.vrvideo.serviceprovider.model.Actress;
import com.vrvideo.serviceprovider.repository.ActressRepository;
import com.vrvideo.serviceprovider.utils.SlugGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class CreateActressService extends ActressService{

    @Autowired
    public CreateActressService(ActressRepository repository, ModelMapper modelMapper) {

        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public void create(ActressDto actressDto) throws DomainValidationException, DomainAlreadyExistsException {

        Actress actress = this.modelMapper.map(actressDto, Actress.class);

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
