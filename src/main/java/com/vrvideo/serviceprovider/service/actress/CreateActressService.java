package com.vrvideo.serviceprovider.service.actress;

import com.vrvideo.serviceprovider.dto.ActressDto;
import com.vrvideo.serviceprovider.model.Image;
import com.vrvideo.serviceprovider.model.exception.DomainAlreadyExistsException;
import com.vrvideo.serviceprovider.model.exception.DomainValidationException;
import com.vrvideo.serviceprovider.model.Actress;
import com.vrvideo.serviceprovider.repository.ActressRepository;
import com.vrvideo.serviceprovider.repository.ImageActressRepository;
import com.vrvideo.serviceprovider.repository.ImageRepository;
import com.vrvideo.serviceprovider.utils.SlugGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class CreateActressService extends ActressService{

    private final ImageActressRepository imageActressRepository;

    private final ImageRepository imageRepository;

    @Autowired
    public CreateActressService(
            ActressRepository actressRepository,
            ImageActressRepository imageActressRepository,
            ImageRepository imageRepository,
            ModelMapper modelMapper
    ) {

        this.actressRepository = actressRepository;
        this.imageActressRepository = imageActressRepository;
        this.imageRepository = imageRepository;
        this.modelMapper = modelMapper;
    }

    public void create(ActressDto actressDto) throws DomainValidationException, DomainAlreadyExistsException {

        Actress actress = this.modelMapper.map(actressDto, Actress.class);

        if (actress.getName().length() == 0)  {
            throw new DomainValidationException("Invalid value for actress name");
        }
        if (actress.getSlug() == null || actress.getSlug().isEmpty()) {
            actress.setSlug(SlugGenerator.generate(actress.getName()));
        }
        Actress actressFound = this.actressRepository.findBySlug(actress.getSlug());
        if (actressFound != null) {
            throw new DomainAlreadyExistsException(String.format("Actress already exists with slug %s", actress.getSlug()));
        }
        actress.setUuid(String.valueOf(UUID.randomUUID()));
        this.actressRepository.save(actress);

        try {
            actress.getImagesActress().forEach(imageActress -> {

                Image image = this.imageRepository.findByFilename(imageActress.getImage().getFilename());

                if (image != null) {
                    throw new RuntimeException(String.format("Image already exists with filename %s", image.getFilename()));
                }
                this.imageRepository.save(imageActress.getImage());
                imageActress.setActress(actress);
                this.imageActressRepository.save(imageActress);
            });
        } catch (RuntimeException exception) {
            throw new DomainAlreadyExistsException(exception.getMessage());
        }
    }
}
