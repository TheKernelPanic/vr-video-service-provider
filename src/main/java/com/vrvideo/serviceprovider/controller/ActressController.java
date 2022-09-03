package com.vrvideo.serviceprovider.controller;

import com.vrvideo.serviceprovider.dto.ActressDto;
import com.vrvideo.serviceprovider.exception.DomainValidationException;
import com.vrvideo.serviceprovider.model.Actress;
import com.vrvideo.serviceprovider.service.actress.CreateService;
import com.vrvideo.serviceprovider.service.actress.FinderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/actress")
public class ActressController extends BaseController{

    private final CreateService createService;

    private final FinderService finderService;

    @Autowired
    public ActressController(
            ModelMapper modelMapper,
            CreateService createActressService,
            FinderService findAllService
    ) {
        super(modelMapper);
        this.createService = createActressService;
        this.finderService = findAllService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create")
    public void create(@RequestBody ActressDto actressDto) {

        Actress actress = this.modelMapper.map(actressDto, Actress.class);
        try {
            this.createService.create(actress);
        } catch (DomainValidationException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ActressDto> list() {
        List<ActressDto> actressDtoList = new ArrayList<>();

        this.finderService.findAll().forEach(actress -> actressDtoList.add(this.modelMapper.map(actress, ActressDto.class)));

        return actressDtoList;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/getBySlug/{slug}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ActressDto getBySlug(@PathVariable String slug) {

        Actress actress = this.finderService.findOneBySlug(slug);

        if (actress == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return this.modelMapper.map(actress, ActressDto.class);
    }
}
