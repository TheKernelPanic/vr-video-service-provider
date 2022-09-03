package com.vrvideo.serviceprovider.service.actress;

import com.vrvideo.serviceprovider.exception.DomainValidationException;
import com.vrvideo.serviceprovider.model.Actress;
import com.vrvideo.serviceprovider.repository.ActressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CreateServiceTest {

    @Mock
    private ActressRepository repository;

    @Test
    public void testCreate() {

        Actress actressDummy = new Actress();
        actressDummy.setName("Something");
        actressDummy.setSlug("something");
        actressDummy.setUuid("cc6ce752-59b1-4eaf-94c9-90d020c7ade6");

        when(this.repository.save(actressDummy)).thenReturn(actressDummy);

        CreateService service = new CreateService(this.repository);

        try {
            service.create(actressDummy);
        } catch (DomainValidationException exception) {
            fail(exception.getMessage());
        }
    }
}
