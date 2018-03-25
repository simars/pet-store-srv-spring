package io.simars.petstore.image;


import io.simars.petstore.entity.AbstractEntity;
import io.simars.petstore.entity.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@MappedSuperclass
public interface ImageLinked<ID extends Serializable, E extends AbstractEntity<ID> & ImageLinked, L extends ImageLink<ID, E>> {

    Set<L> getImageLinks();

    void setImageLinks(Set<L> imageLinks);
}
