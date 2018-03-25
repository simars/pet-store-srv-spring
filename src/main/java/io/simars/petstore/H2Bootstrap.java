package io.simars.petstore;

import io.simars.petstore.entity.image.Image;
import io.simars.petstore.entity.image.ImageLink;
import io.simars.petstore.entity.pet.Pet;
import io.simars.petstore.entity.pet.PetCategory;
import io.simars.petstore.entity.pet.PetStatus;
import io.simars.petstore.entity.pet.PetTagLink;
import io.simars.petstore.entity.tag.TagLink;
import io.simars.petstore.repository.PetCategoryRepository;
import io.simars.petstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class H2Bootstrap implements CommandLineRunner {

    @Autowired
    PetRepository petRepository;

    @Autowired
    PetCategoryRepository petCategoryRepository;

    @Override
    public void run(String... args) throws Exception {

        final PetCategory SMALL  = new PetCategory(1L,"SMALL");
        final PetCategory MEDIUM = new PetCategory(2L,"MEDIUM");
        final PetCategory LARGE = new PetCategory(3L,"LARGE");

        petCategoryRepository.saveAll(Arrays.asList(SMALL,MEDIUM,LARGE));


        final Pet[] pets = {
                new Pet("puchi",SMALL, PetStatus.AVAILABLE),
                new Pet("muchi", LARGE, PetStatus.SOLD),
                new Pet("bozo", MEDIUM,PetStatus.PENDING)
        };

        petRepository.saveAll(Arrays.asList(pets));



    }
}
