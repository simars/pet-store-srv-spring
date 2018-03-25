package io.simars.petstore.entity.tag;

import io.simars.petstore.entity.AbstractEntity;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@MappedSuperclass
public interface TagLinked<ID extends Serializable, E extends AbstractEntity<ID> & TagLinked> {

    @NotNull Set<Tag> getTags();

}
