package com.vrvideo.serviceprovider.service.vrVideo;

import com.vrvideo.serviceprovider.model.Actress;
import com.vrvideo.serviceprovider.model.VrVideo;
import com.vrvideo.serviceprovider.model.exception.DomainRecordNotFoundException;
import com.vrvideo.serviceprovider.repository.ActressRepository;
import com.vrvideo.serviceprovider.repository.VrVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdderActressToVrVideoService extends VrVideoService {
    private final ActressRepository actressRepository;

    @Autowired
    public AdderActressToVrVideoService(VrVideoRepository vrVideoRepository, ActressRepository actressRepository) {

        this.vrVideoRepository = vrVideoRepository;
        this.actressRepository = actressRepository;
    }

    public void add(String uuid, String actressSlug) throws DomainRecordNotFoundException {

        Actress actress = this.actressRepository.findBySlug(actressSlug);

        if (actress == null) {
            throw new DomainRecordNotFoundException(String.format("Actress not exists with slug %s", actressSlug));
        }

        VrVideo vrVideo = this.vrVideoRepository.findByUuid(uuid);
        if (vrVideo == null) {
            throw new DomainRecordNotFoundException(String.format("VrVideo not exists with uuid %s", uuid));
        }
        Set<Actress> actresses = vrVideo.getActresses();
        actresses.add(actress);

        vrVideo.setActresses(actresses);

        this.vrVideoRepository.save(vrVideo);
    }
}
