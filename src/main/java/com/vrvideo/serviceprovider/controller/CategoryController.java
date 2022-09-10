package com.vrvideo.serviceprovider.controller;

import com.vrvideo.serviceprovider.dto.CategoryDto;
import com.vrvideo.serviceprovider.model.exception.DomainValidationException;
import com.vrvideo.serviceprovider.service.category.CreateCategoryService;
import com.vrvideo.serviceprovider.service.category.FinderCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController extends BaseController{

    private final FinderCategoryService finderCategoryService;

    private final CreateCategoryService createCategoryService;

    @Autowired
    public CategoryController(
            FinderCategoryService finderCategoryService,
            CreateCategoryService createCategoryService
    ) {
        this.finderCategoryService = finderCategoryService;
        this.createCategoryService = createCategoryService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {

        try {
            return this.createCategoryService.create(categoryDto);
        } catch (DomainValidationException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listing", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> listing() {

        return this.finderCategoryService.findAll();
    }
}
