package io.simars.petstore.entity.pet;


import io.simars.petstore.entity.image.ImageLink;

import javax.persistence.*;

@Entity
@Table(name = "pet_image")
public class PetImageLink extends ImageLink<Long,Pet> {

    @EmbeddedId
    private EntityImageId<Long> id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("entityId")
    private Pet entity;

    @Override
    public EntityImageId<Long> getId() {
        return id;
    }

    @Override
    public Pet getEntity() {
        return entity;
    }

}
