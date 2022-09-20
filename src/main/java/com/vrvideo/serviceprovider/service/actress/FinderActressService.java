package com.vrvideo.serviceprovider.service.actress;

import com.vrvideo.serviceprovider.dto.ActressDto;
import com.vrvideo.serviceprovider.model.Actress;
import com.vrvideo.serviceprovider.model.exception.DomainRecordNotFoundException;
import com.vrvideo.serviceprovider.repository.ActressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public final class FinderActressService extends ActressService{

    @Autowired
    public FinderActressService(ActressRepository repository, ModelMapper modelMapper) {
        this.actressRepository = repository;
        this.modelMapper = modelMapper;
    }

    public List<ActressDto> findAll() {

        List<ActressDto> actressDtoList = new ArrayList<>();

        this.actressRepository.findAll().forEach(actress -> actressDtoList.add(this.modelMapper.map(actress, ActressDto.class)));

        return actressDtoList;
    }

    public ActressDto findOneBySlug(String slug) throws DomainRecordNotFoundException {

        Actress actress = this.actressRepository.findBySlug(slug);

        if (actress == null) {
            throw new DomainRecordNotFoundException("Actress requested by slug not found");
        }
        return this.modelMapper.map(actress, ActressDto.class);
    }
}
