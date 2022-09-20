package com.vrvideo.serviceprovider.repository;

import com.vrvideo.serviceprovider.model.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {

    Image findByFilename(String filename);
}
