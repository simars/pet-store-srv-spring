package io.simars.petstore.pet;


import io.simars.petstore.image.ImageLink;

import javax.persistence.*;
//
//@Entity
//@DiscriminatorValue(value = "pet")
public class PetImageLink extends ImageLink<Long,Pet> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("entityId")
    private Pet entity;

    public Pet getEntity() {
        return entity;
    }


}
