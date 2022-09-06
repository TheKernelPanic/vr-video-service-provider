package com.vrvideo.serviceprovider.service.category;

import com.vrvideo.serviceprovider.model.Category;
import com.vrvideo.serviceprovider.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class FinderCategoryService extends CategoryService {
    @Autowired
    public FinderCategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Iterable<Category> findAll() {
        return this.repository.findAll();
    }
}
