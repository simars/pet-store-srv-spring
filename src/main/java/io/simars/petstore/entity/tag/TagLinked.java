package io.simars.petstore.entity.tag;

import io.simars.petstore.entity.AbstractEntity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Set;

@MappedSuperclass
public interface TagLinked<ID extends Serializable, E extends AbstractEntity<ID> & TagLinked, L extends TagLink<ID, E>> {


    Set<L> getTagLinks();

    void setTagLinks(Set<L> tagLinks);

}
