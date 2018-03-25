package io.simars.petstore.entity.image;


import io.simars.petstore.entity.AbstractEntity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Set;

@MappedSuperclass
public interface ImageLinked<ID extends Serializable, E extends AbstractEntity<ID> & ImageLinked, L extends ImageLink<ID, E>> {

    Set<L> getImageLinks();

    void setImageLinks(Set<L> imageLinks);
}
