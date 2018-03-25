package io.simars.petstore.repository;

import io.simars.petstore.entity.tag.Tag;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface TagRepository extends CrudRepository<Tag,Long> {

    Optional<Tag> findByName(@NotNull @NotBlank final String name);
}
