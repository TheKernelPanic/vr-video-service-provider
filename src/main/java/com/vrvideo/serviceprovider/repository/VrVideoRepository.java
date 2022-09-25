package com.vrvideo.serviceprovider.repository;

import com.vrvideo.serviceprovider.model.VrVideo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VrVideoRepository extends CrudRepository<VrVideo, Long> {

    VrVideo findByUuid(String uuid);

    List<VrVideo> findAllByOrderByCreatedAtDesc();
}
