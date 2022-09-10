package com.vrvideo.serviceprovider.controller;

import com.vrvideo.serviceprovider.dto.ActressDto;
import com.vrvideo.serviceprovider.model.exception.DomainAlreadyExistsException;
import com.vrvideo.serviceprovider.model.exception.DomainRecordNotFoundException;
import com.vrvideo.serviceprovider.model.exception.DomainValidationException;
import com.vrvideo.serviceprovider.service.actress.CreateActressService;
import com.vrvideo.serviceprovider.service.actress.FinderActressService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/actress")
public class ActressController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(ActressController.class);

    private final CreateActressService createService;

    private final FinderActressService finderService;

    @Autowired
    public ActressController(
            ModelMapper modelMapper,
            CreateActressService createActressService,
            FinderActressService findAllService
    ) {
        this.createService = createActressService;
        this.finderService = findAllService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody ActressDto actressDto) {

        try {
            this.createService.create(actressDto);
        } catch (DomainValidationException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (DomainAlreadyExistsException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listing", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ActressDto> listing() {

        return this.finderService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/getBySlug/{slug}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ActressDto getBySlug(@PathVariable String slug) {

        try {
            return this.finderService.findOneBySlug(slug);
        } catch (DomainRecordNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
