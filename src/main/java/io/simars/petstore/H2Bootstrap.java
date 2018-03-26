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

        final PetCategory DOG  = new PetCategory(1L,"DOG");
        final PetCategory CAT = new PetCategory(2L,"CAT");
        final PetCategory RABBIT = new PetCategory(3L,"RABBIT");

        petCategoryRepository.saveAll(Arrays.asList(DOG,CAT,RABBIT));


        final Set<Tag> tags = of("Hello", "Nice").map(Tag::new).collect(Collectors.toSet());
        final Set<Image> images = of(
                "https://proxy.topixcdn.com/ipicimg/QTC9VVKRMHFOEJPM-cp0x191x2560x1471-fill810x415x",
                "https://www.planwallpaper.com/static/images/734899052_13956580111.jpg"
        ).map(Image::new).collect(Collectors.toSet());



        final Pet[] pets = {
                new Pet("Bozo",DOG, PetStatus.AVAILABLE),
                new Pet("King Bunny", RABBIT, PetStatus.SOLD),
                new Pet("Billy", CAT,PetStatus.PENDING)
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
