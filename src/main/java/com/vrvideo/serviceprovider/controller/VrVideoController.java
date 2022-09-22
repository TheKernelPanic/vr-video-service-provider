package com.vrvideo.serviceprovider.controller;

import com.vrvideo.serviceprovider.dto.VrVideoDto;
import com.vrvideo.serviceprovider.model.exception.DomainRecordNotFoundException;
import com.vrvideo.serviceprovider.service.vrVideo.AdderActressToVrVideoService;
import com.vrvideo.serviceprovider.service.vrVideo.FinderVrVideoService;
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

    @Autowired
    public VrVideoController(
            FinderVrVideoService finderVrVideoService,
            AdderActressToVrVideoService adderActressToVrVideoService
    ) {
        this.finderVrVideoService = finderVrVideoService;
        this.adderActressToVrVideoService = adderActressToVrVideoService;
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
}
