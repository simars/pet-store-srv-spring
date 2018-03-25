package io.simars.petstore.entity.image;


import io.simars.petstore.entity.AbstractEntity;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@MappedSuperclass
public interface ImageLinked<ID extends Serializable, E extends AbstractEntity<ID> & ImageLinked> {

    @NotNull Set<Image> getImages();

}
