package io.simars.petstore.repository;

import io.simars.petstore.entity.pet.Pet;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageablePetRepository extends PagingAndSortingRepository<Pet, Long> {

}
