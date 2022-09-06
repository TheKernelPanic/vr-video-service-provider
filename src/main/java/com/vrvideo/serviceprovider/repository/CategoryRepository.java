package com.vrvideo.serviceprovider.repository;

import com.vrvideo.serviceprovider.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findBySlug(String slug);
}
