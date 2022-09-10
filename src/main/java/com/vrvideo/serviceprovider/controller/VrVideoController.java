package com.vrvideo.serviceprovider.controller;

import com.vrvideo.serviceprovider.dto.VrVideoDto;
import com.vrvideo.serviceprovider.service.vrVideo.FinderVrVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/vr-video")
public class VrVideoController extends BaseController {

    private final FinderVrVideoService finderVrVideoService;

    @Autowired
    public VrVideoController(FinderVrVideoService finderVrVideoService) {
        this.finderVrVideoService = finderVrVideoService;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listing", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VrVideoDto> listing() {

        return this.finderVrVideoService.findAll();
    }
}
