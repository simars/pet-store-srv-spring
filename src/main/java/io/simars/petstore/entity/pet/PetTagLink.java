package io.simars.petstore.entity.pet;

import io.simars.petstore.entity.tag.Tag;
import io.simars.petstore.entity.tag.TagLink;

import javax.persistence.*;


@Entity
@DiscriminatorValue(value = "pet")
public class PetTagLink extends TagLink<Long,Pet> {

    public PetTagLink() {
    }

    public PetTagLink(EntityTagId<Long> id, Pet entity) {
        this.id = id;
        this.entity = entity;
    }

    @EmbeddedId
    private EntityTagId<Long> id;

    @Override
    public EntityTagId<Long> getId() {
        return this.id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("entityId")
    private Pet entity;


    public Pet getEntity() {
        return entity;
    }





}
