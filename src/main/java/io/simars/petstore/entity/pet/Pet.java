package io.simars.petstore.entity.pet;

import io.simars.petstore.entity.BaseEntity;
import io.simars.petstore.entity.image.ImageLinked;
import io.simars.petstore.entity.tag.TagLinked;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="pet")
public class Pet extends BaseEntity implements TagLinked<Long, Pet, PetTagLink>, ImageLinked<Long, Pet, PetImageLink> {

    public Pet() {
    }

    public Pet(@NotNull @NotBlank String name, @NotNull PetCategory category, @NotNull PetStatus status) {
        this.name = name;
        this.category = category;
        this.status = status;
    }

    public Pet(@NotNull @NotBlank String name, @NotNull PetCategory category, @NotNull PetStatus status,
               Set<PetTagLink> tagLinks, Set<PetImageLink> imageLinks) {
        this(name,category,status);
        this.tagLinks = tagLinks;
        this.imageLinks = imageLinks;
    }

    @NaturalId
    @Column(name = "name")
    @NotNull
    @NotBlank
    private String name;


    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private PetCategory category;

    @Column(name = "status")
    @NotNull
    private PetStatus status;


    @OneToMany(
            mappedBy = "tag",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<PetTagLink> tagLinks = new HashSet<>();



    @OneToMany(
            mappedBy = "image",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Transient
    private Set<PetImageLink> imageLinks = new HashSet<>();


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


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

    public PetCategory getCategory() {
        return category;
    }

    public void setCategory(PetCategory category) {
        this.category = category;
    }

    public PetStatus getStatus() {
        return status;
    }

    public void setStatus(PetStatus status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return super.toString() + "#" + this.getName();
    }

}
