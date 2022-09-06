package com.vrvideo.serviceprovider.service.category;

import com.vrvideo.serviceprovider.model.exception.DomainValidationException;
import com.vrvideo.serviceprovider.model.Category;
import com.vrvideo.serviceprovider.repository.CategoryRepository;
import com.vrvideo.serviceprovider.utils.SlugGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryService extends CategoryService{

    @Autowired
    public CreateCategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category create(Category category) throws DomainValidationException {

        if (category.getCanonicalName() == null) {
            throw new DomainValidationException("Category name cannot be empty");
        }
        String slug = SlugGenerator.generate(category.getCanonicalName());
        Category categoryFound = this.repository.findBySlug(slug);
        if (categoryFound != null) {
            return categoryFound;
        }
        category.setSlug(slug);
        return this.repository.save(category);
    }
}
