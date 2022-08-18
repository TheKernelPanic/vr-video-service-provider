package com.vrvideo.serviceprovider.repository;

import com.vrvideo.serviceprovider.model.Actress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActressRepository extends CrudRepository<Actress, Long> {
}
