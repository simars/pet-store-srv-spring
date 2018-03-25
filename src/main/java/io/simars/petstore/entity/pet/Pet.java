package io.simars.petstore.entity.pet;

import io.simars.petstore.entity.BaseEntity;
import io.simars.petstore.entity.image.Image;
import io.simars.petstore.entity.image.ImageLinked;
import io.simars.petstore.entity.tag.Tag;
import io.simars.petstore.entity.tag.TagLinked;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "pet")
public class Pet extends BaseEntity implements TagLinked<Long, Pet>, ImageLinked<Long, Pet> {

    public Pet() {
    }

    public Pet(@NotNull @NotBlank String name, @NotNull PetCategory category, @NotNull PetStatus status) {
        this.name = name;
        this.category = category;
        this.status = status;
    }

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


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "pet_tag",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new LinkedHashSet<>();


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "image_tag",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private Set<Image> images = new LinkedHashSet<>();


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Set<Image> getImages() {
        return images;
    }

    @Override
    public Set<Tag> getTags() {
        return tags;
    }


    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }


    @Override
    public String toString() {
        return super.toString() + "#" + this.getName();
    }

}
