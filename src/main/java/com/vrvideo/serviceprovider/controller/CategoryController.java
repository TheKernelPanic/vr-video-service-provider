package com.vrvideo.serviceprovider.controller;

import com.vrvideo.serviceprovider.dto.CategoryDto;
import com.vrvideo.serviceprovider.model.exception.DomainValidationException;
import com.vrvideo.serviceprovider.model.Category;
import com.vrvideo.serviceprovider.service.category.CreateCategoryService;
import com.vrvideo.serviceprovider.service.category.FinderCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController extends BaseController{

    private final FinderCategoryService finderCategoryService;

    private final CreateCategoryService createCategoryService;

    @Autowired
    public CategoryController(
            ModelMapper modelMapper,
            FinderCategoryService finderCategoryService,
            CreateCategoryService createCategoryService
    ) {
        super(modelMapper);
        this.finderCategoryService = finderCategoryService;
        this.createCategoryService = createCategoryService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {

        Category category = this.modelMapper.map(categoryDto, Category.class);
        try {
            return this.modelMapper.map(
                    this.createCategoryService.create(category),
                    CategoryDto.class
            );
        } catch (DomainValidationException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listing", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> listing() {
        List<CategoryDto> categoriesDto = new ArrayList<>();

        this.finderCategoryService.findAll().forEach(category -> categoriesDto.add(this.modelMapper.map(category, CategoryDto.class)));

        return categoriesDto;
    }
}
