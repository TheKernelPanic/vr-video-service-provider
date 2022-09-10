package com.vrvideo.serviceprovider.service.category;

import com.vrvideo.serviceprovider.dto.CategoryDto;
import com.vrvideo.serviceprovider.model.exception.DomainValidationException;
import com.vrvideo.serviceprovider.model.Category;
import com.vrvideo.serviceprovider.repository.CategoryRepository;
import com.vrvideo.serviceprovider.utils.SlugGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryService extends CategoryService{

    @Autowired
    public CreateCategoryService(CategoryRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public CategoryDto create(CategoryDto categoryDto) throws DomainValidationException {

        Category category = this.modelMapper.map(categoryDto, Category.class);

        if (category.getCanonicalName() == null) {
            throw new DomainValidationException("Category name cannot be empty");
        }
        String slug = SlugGenerator.generate(category.getCanonicalName());
        Category categoryFound = this.repository.findBySlug(slug);
        if (categoryFound != null) {
            return this.modelMapper.map(
                    categoryFound,
                    CategoryDto.class
            );
        }
        category.setSlug(slug);
        return this.modelMapper.map(
                this.repository.save(category),
                CategoryDto.class
        );
    }
}
