package io.simars.petstore.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Abstract Entity, ensures identity + equals & hashcode & default toString
 */
@MappedSuperclass
public abstract class AbstractEntity<PK extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    public AbstractEntity() {

    }

    @Id
    public abstract PK getId();

    @Override
    public abstract boolean equals(Object otherEntity);

    @Override
    public abstract int hashCode();


    public boolean isNew() {
        return null == getId();
    }
}
