package com.vrvideo.serviceprovider.controller;

import com.vrvideo.serviceprovider.dto.VrVideoDto;
import com.vrvideo.serviceprovider.model.exception.DomainRecordNotFoundException;
import com.vrvideo.serviceprovider.model.exception.DomainValidationException;
import com.vrvideo.serviceprovider.service.vrVideo.AdderActressToVrVideoService;
import com.vrvideo.serviceprovider.service.vrVideo.AdderCategoryToVrVideoService;
import com.vrvideo.serviceprovider.service.vrVideo.FinderVrVideoService;
import com.vrvideo.serviceprovider.service.vrVideo.UpdateVrVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/vr-video")
public class VrVideoController extends BaseController {

    private final FinderVrVideoService finderVrVideoService;

    private final AdderActressToVrVideoService adderActressToVrVideoService;

    private final AdderCategoryToVrVideoService adderCategoryToVrVideoService;

    private final UpdateVrVideoService updateVrVideoService;

    @Autowired
    public VrVideoController(
            FinderVrVideoService finderVrVideoService,
            AdderActressToVrVideoService adderActressToVrVideoService,
            AdderCategoryToVrVideoService adderCategoryToVrVideoService,
            UpdateVrVideoService updateVrVideoService

    ) {
        this.finderVrVideoService = finderVrVideoService;
        this.adderActressToVrVideoService = adderActressToVrVideoService;
        this.adderCategoryToVrVideoService = adderCategoryToVrVideoService;
        this.updateVrVideoService = updateVrVideoService;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listing", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VrVideoDto> listing() {

        return this.finderVrVideoService.findAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/add-actress/{uuid}/{actressSlug}", method= RequestMethod.PUT)
    public void addActress(@PathVariable String uuid, @PathVariable String actressSlug) {

        try {
            this.adderActressToVrVideoService.add(uuid, actressSlug);
        } catch (DomainRecordNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/add-category/{uuid}/{categorySlug}", method = RequestMethod.PUT)
    public void addCategory(@PathVariable String uuid, @PathVariable String categorySlug) {

        try {
            this.adderCategoryToVrVideoService.add(uuid, categorySlug);
        } catch (DomainRecordNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/toggle-favourite/{uuid}", method = RequestMethod.PUT)
    public void toggleFavourite(@PathVariable String uuid) {

        try {
            this.updateVrVideoService.toggleFavourite(uuid);
        } catch (DomainRecordNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/set-rating/{uuid}/{value}", method = RequestMethod.PUT)
    public void setRating(@PathVariable String uuid, @PathVariable int value) {

        try {
            this.updateVrVideoService.setRating(uuid, value);
        } catch (DomainRecordNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (DomainValidationException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
