package io.simars.petstore.repository;

import io.simars.petstore.entity.image.Image;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image,Long> {

    Optional<Image> findByUrl(@NotNull @NotBlank String url);
}
