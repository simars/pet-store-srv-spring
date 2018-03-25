package io.simars.petstore.entity.pet;

import io.simars.petstore.entity.BaseEntity;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Model's pet categories
 */
@Entity
@Table(name="pet_category")
public class PetCategory extends BaseEntity {

    public PetCategory() {
    }

    public PetCategory(Long id,  @NotNull @NotBlank String name) {
        this.id = id;
        this.name = name;
    }

    public PetCategory(@NotNull @NotBlank String name) {
        this.name = name;
    }

    @NaturalId
    @Column(name = "name")
    @NotNull
    @NotBlank
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
