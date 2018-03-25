package io.simars.petstore.entity;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Extends {@link BaseEntity} with name property for {id -> name}
 */
@MappedSuperclass
public class NamedEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + "#" + this.getName();
    }

}
