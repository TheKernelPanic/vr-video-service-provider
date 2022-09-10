package com.vrvideo.serviceprovider.service.category;

import com.vrvideo.serviceprovider.repository.CategoryRepository;
import org.modelmapper.ModelMapper;

public abstract class CategoryService {

    protected CategoryRepository repository;

    protected ModelMapper modelMapper;
}
