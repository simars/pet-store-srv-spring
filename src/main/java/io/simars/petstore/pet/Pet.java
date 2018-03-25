package io.simars.petstore.pet;

import io.simars.petstore.entity.NamedEntity;
import io.simars.petstore.image.ImageLinked;
import io.simars.petstore.tag.TagLinked;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="pet")
public class Pet extends NamedEntity implements TagLinked<Long, Pet, PetTagLink>, ImageLinked<Long, Pet, PetImageLink>{

    @ManyToOne
    @JoinColumn(name = "category_id")
    private PetCategory category;


    @OneToMany(
            mappedBy = "tag",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<PetTagLink> tagLinks = new HashSet<>();


    @Column(name = "status")
    private PetStatus status;


    /*@OneToMany(
            mappedBy = "image",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )*/
    @Transient
    private Set<PetImageLink> imageLinks = new HashSet<>();

    @Override
    public Set<PetImageLink> getImageLinks() {
        return this.imageLinks;
    }

    @Override
    public void setImageLinks(Set<PetImageLink> imageLinks) {
        this.imageLinks = imageLinks;
    }

    @Override
    public Set<PetTagLink> getTagLinks() {
        return this.tagLinks;
    }

    @Override
    public void setTagLinks(Set<PetTagLink> tagLinks) {
        this.tagLinks = tagLinks;
    }
}
