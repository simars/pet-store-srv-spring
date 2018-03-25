package io.simars.petstore;

import io.simars.petstore.entity.image.Image;
import io.simars.petstore.entity.pet.Pet;
import io.simars.petstore.entity.pet.PetCategory;
import io.simars.petstore.entity.pet.PetStatus;
import io.simars.petstore.entity.tag.Tag;
import io.simars.petstore.repository.PetCategoryRepository;
import io.simars.petstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Stream.*;

@Component
public class H2Bootstrap implements CommandLineRunner {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    PetCategoryRepository petCategoryRepository;

    @Override
    public void run(String... args) {

        final PetCategory SMALL  = new PetCategory(1L,"SMALL");
        final PetCategory MEDIUM = new PetCategory(2L,"MEDIUM");
        final PetCategory LARGE = new PetCategory(3L,"LARGE");

        petCategoryRepository.saveAll(Arrays.asList(SMALL,MEDIUM,LARGE));


        Set<Tag> tags = of("Hello", "Nice").map(Tag::new).collect(Collectors.toSet());
        Set<Image> images = of("http://1", "http://2").map(Image::new).collect(Collectors.toSet());

        final Pet[] pets = {
                new Pet("puchi",SMALL, PetStatus.AVAILABLE),
                new Pet("muchi", LARGE, PetStatus.SOLD),
                new Pet("bozo", MEDIUM,PetStatus.PENDING)
        };

        pets[0].getImages().addAll(images);
        pets[0].getTags().addAll(tags);


        Iterable<Pet> persistedPets = petRepository.saveAll(Arrays.asList(pets));


        persistedPets.forEach(pet -> {
            pet.getImages().addAll(images);
            pet.getTags().addAll(tags);
        });

        petRepository.saveAll(persistedPets);

    }
}
