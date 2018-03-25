package io.simars.petstore.repository;

import io.simars.petstore.entity.pet.PetCategory;
import org.springframework.data.repository.CrudRepository;

public interface PetCategoryRepository extends CrudRepository<PetCategory,Long> {
}
