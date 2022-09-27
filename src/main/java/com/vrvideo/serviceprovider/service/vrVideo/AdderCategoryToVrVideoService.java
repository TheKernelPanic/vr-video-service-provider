package com.vrvideo.serviceprovider.service.vrVideo;

import com.vrvideo.serviceprovider.model.Category;
import com.vrvideo.serviceprovider.model.VrVideo;
import com.vrvideo.serviceprovider.model.exception.DomainRecordNotFoundException;
import com.vrvideo.serviceprovider.repository.CategoryRepository;
import com.vrvideo.serviceprovider.repository.VrVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdderCategoryToVrVideoService extends VrVideoService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public AdderCategoryToVrVideoService(VrVideoRepository vrVideoRepository, CategoryRepository categoryRepository) {
        this.vrVideoRepository = vrVideoRepository;
        this.categoryRepository = categoryRepository;
    }

    public void add(String uuid, String categorySlug) throws DomainRecordNotFoundException {

        Category category = this.categoryRepository.findBySlug(categorySlug);

        if (category == null) {
            throw new DomainRecordNotFoundException(String.format("Actress not exists with slug %s", categorySlug));
        }
        VrVideo vrVideo = this.vrVideoRepository.findByUuid(uuid);
        if (vrVideo == null) {
            throw new DomainRecordNotFoundException(String.format("VrVideo not exists with uuid %s", uuid));
        }
        Set<Category> categories = vrVideo.getCategories();
        categories.add(category);

        vrVideo.setCategories(categories);

        this.vrVideoRepository.save(vrVideo);
    }
}
