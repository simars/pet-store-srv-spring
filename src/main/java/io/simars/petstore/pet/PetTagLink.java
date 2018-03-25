package io.simars.petstore.pet;

import io.simars.petstore.tag.Tag;
import io.simars.petstore.tag.TagLink;

import javax.persistence.*;


@Entity
@DiscriminatorValue(value = "pet")
public class PetTagLink extends TagLink<Long,Pet> {


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("entityId")
    private Pet entity;


    public Pet getEntity() {
        return entity;
    }


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Tag.class)
    @MapsId("tagId")
    protected Tag tag;

    public Tag getTag() {
        return tag;
    }


}
