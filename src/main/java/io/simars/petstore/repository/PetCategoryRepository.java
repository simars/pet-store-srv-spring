package io.simars.petstore.repository;

import io.simars.petstore.entity.pet.PetCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PetCategoryRepository extends CrudRepository<PetCategory,Long> {

    Optional<PetCategory>  findByName(String name);
}
