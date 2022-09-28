package com.vrvideo.serviceprovider.service.vrVideo;

import com.vrvideo.serviceprovider.model.VrVideo;
import com.vrvideo.serviceprovider.model.exception.DomainRecordNotFoundException;
import com.vrvideo.serviceprovider.model.exception.DomainValidationException;
import com.vrvideo.serviceprovider.repository.VrVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateVrVideoService extends VrVideoService {

    @Autowired
    public UpdateVrVideoService(VrVideoRepository vrVideoRepository) {
        this.vrVideoRepository = vrVideoRepository;
    }

    public void toggleFavourite(String uuid) throws DomainRecordNotFoundException {
        VrVideo vrVideo = this.vrVideoRepository.findByUuid(uuid);
        if (vrVideo == null) {
            throw new DomainRecordNotFoundException(String.format("Vr video not exists with uuid %s", uuid));
        }
        vrVideo.setFavourite(!vrVideo.getFavourite());

        this.vrVideoRepository.save(vrVideo);
    }

    public void setRating(String uuid, int value) throws DomainRecordNotFoundException, DomainValidationException {

        if (value > 5 || value < 0) {
            throw new DomainValidationException("Invalid rating value");
        }
        VrVideo vrVideo = this.vrVideoRepository.findByUuid(uuid);
        if (vrVideo == null) {
            throw new DomainRecordNotFoundException(String.format("Vr video not exists with uuid %s", uuid));
        }
        vrVideo.setRating(value);

        this.vrVideoRepository.save(vrVideo);
    }
}
