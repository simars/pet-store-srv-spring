package io.simars.petstore.model.convertor;

import io.simars.petstore.entity.image.Image;
import io.simars.petstore.entity.pet.Pet;
import io.simars.petstore.entity.pet.PetCategory;
import io.simars.petstore.entity.tag.Tag;
import io.simars.petstore.model.IdName;
import io.simars.petstore.model.payload.PetPayload;
import io.simars.petstore.repository.ImageRepository;
import io.simars.petstore.repository.PetCategoryRepository;
import io.simars.petstore.repository.PetRepository;
import io.simars.petstore.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service that transfer states from Pet Payload to Pet entity
 * @author simars
 */
@Service
public class PetInputConverter implements Converter<PetPayload, Pet> {

    private final PetRepository petRepository;

    private final PetCategoryRepository categoryRepository;

    private final TagRepository tagRepository;

    private final ImageRepository imageRepository;

     @Autowired
    public PetInputConverter(final PetRepository petRepository, final PetCategoryRepository categoryRepository,
                             final TagRepository tagRepository, final ImageRepository imageRepository) {
        this.petRepository = petRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public Pet convert(PetPayload payload) {
        Optional<Long> idOptional = Optional.ofNullable(payload.getId());
        Optional<Pet> petOptional = idOptional.flatMap(petRepository::findById);
        final Pet petToUpdate;
        if (idOptional.isPresent()) {
            if (!petOptional.isPresent()) {
                throw new NoSuchElementException("Pet id mismatch");
            }
            petToUpdate = petOptional.get();
        } else {
            petToUpdate = new Pet();
        }
        IdName catIdName = payload.getCategory();

        Optional<PetCategory> category = Optional.ofNullable(catIdName).flatMap(cat -> this.categoryRepository.findById(cat.getId()));
        if (!category.isPresent()) {
            throw new NoSuchElementException("Category not found " + catIdName);
        }
        petToUpdate.setCategory(category.get());
        petToUpdate.setName(payload.getName());
        petToUpdate.setStatus(payload.getStatus());
        Optional.ofNullable(payload.getPhotoUrls()).ifPresent(
                images -> images.forEach(url -> {
                    petToUpdate.getImages().add(imageRepository.findByUrl(url).orElse(new Image(url)));
                })
        );

        Optional.ofNullable(payload.getTags()).ifPresent(tags -> tags.forEach(
                tag -> {
                    Tag tag1 = Optional.ofNullable(tag.getId()).flatMap(tagRepository::findById).orElse(tagRepository.findByName(tag.getName()).orElse(new Tag(tag.getName())));
                    petToUpdate.getTags().add(tag1);
                }

        ));

        return petToUpdate;
    }
}