package com.vrvideo.serviceprovider.service.vrVideo;

import com.vrvideo.serviceprovider.dto.VrVideoDto;
import com.vrvideo.serviceprovider.model.VrVideo;
import com.vrvideo.serviceprovider.repository.VrVideoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class FinderVrVideoService extends VrVideoService {

    @Autowired
    public FinderVrVideoService(VrVideoRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<VrVideoDto> findAll() {

        Iterable<VrVideo> vrVideos = this.repository.findAllByOrderByCreatedAtDesc();
        List<VrVideoDto> vrVideoDtoList = new ArrayList<>();

        vrVideos.forEach(vrVideo -> vrVideoDtoList.add(this.modelMapper.map(vrVideo, VrVideoDto.class)));

        return vrVideoDtoList;
    }
}
