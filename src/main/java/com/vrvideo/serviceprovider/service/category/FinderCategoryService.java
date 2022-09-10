package com.vrvideo.serviceprovider.service.category;

import com.vrvideo.serviceprovider.dto.CategoryDto;
import com.vrvideo.serviceprovider.model.Category;
import com.vrvideo.serviceprovider.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public final class FinderCategoryService extends CategoryService {

    @Autowired
    public FinderCategoryService(CategoryRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<CategoryDto> findAll() {

        Iterable<Category> categories = this.repository.findAll();

        List<CategoryDto> categoriesDto = new ArrayList<>();

        categories.forEach(category -> categoriesDto.add(this.modelMapper.map(category, CategoryDto.class)));

        return categoriesDto;
    }
}
