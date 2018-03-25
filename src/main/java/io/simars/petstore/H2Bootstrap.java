package io.simars.petstore;

import io.simars.petstore.pet.Pet;
import io.simars.petstore.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class H2Bootstrap implements CommandLineRunner {

    @Autowired
    PetRepository petRepository;

    @Override
    public void run(String... args) throws Exception {
        Pet pet = new Pet();

        pet.setName("doggie");
        petRepository.save(pet);
    }
}
