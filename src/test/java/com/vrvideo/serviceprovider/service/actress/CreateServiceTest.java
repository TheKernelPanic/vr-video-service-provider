package com.vrvideo.serviceprovider.service.actress;

import com.vrvideo.serviceprovider.dto.ActressDto;
import com.vrvideo.serviceprovider.model.exception.DomainAlreadyExistsException;
import com.vrvideo.serviceprovider.model.exception.DomainValidationException;
import com.vrvideo.serviceprovider.model.Actress;
import com.vrvideo.serviceprovider.repository.ActressRepository;
import com.vrvideo.serviceprovider.repository.ImageActressRepository;
import com.vrvideo.serviceprovider.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CreateServiceTest {

    @Mock
    private ActressRepository actressRepositoryMock;

    @Mock
    private ImageActressRepository imageActressRepository;

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private ModelMapper modelMapperMock;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testCreate() {

        ActressDto actressDtoDummy = new ActressDto();
        actressDtoDummy.setName("Something");
        actressDtoDummy.setSlug("something");
        actressDtoDummy.setUuid("cc6ce752-59b1-4eaf-94c9-90d020c7ade6");

        Actress actress = this.modelMapper.map(actressDtoDummy, Actress.class);

        when(this.actressRepositoryMock.save(actress)).thenReturn(actress);
        when(this.actressRepositoryMock.findBySlug(actressDtoDummy.getSlug())).thenReturn(null);
        when(this.modelMapperMock.map(actressDtoDummy, Actress.class)).thenReturn(actress);

        CreateActressService service = new CreateActressService(
                this.actressRepositoryMock,
                this.imageActressRepository,
                this.imageRepository,
                this.modelMapperMock
        );

        try {
            service.create(actressDtoDummy);
        } catch (DomainValidationException | DomainAlreadyExistsException exception) {
            fail(exception.getMessage());
        }
    }
}
