package com.vrvideo.serviceprovider.controller;

import com.vrvideo.serviceprovider.dto.ActressDto;
import com.vrvideo.serviceprovider.exception.DomainValidationException;
import com.vrvideo.serviceprovider.model.Actress;
import com.vrvideo.serviceprovider.service.actress.CreateService;
import com.vrvideo.serviceprovider.service.actress.FindAllService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/actress")
public class ActressController extends BaseController{

    private CreateService createService;

    private FindAllService findAllService;

    @Autowired
    public ActressController(
            ModelMapper modelMapper,
            CreateService createActressService,
            FindAllService findAllService
    ) {
        super(modelMapper);
        this.createService = createActressService;
        this.findAllService = findAllService;
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

        this.findAllService.fetch().forEach(actress -> actressDtoList.add(this.modelMapper.map(actress, ActressDto.class)));

        return actressDtoList;
    }
}
