package io.simars.petstore.model.payload;

import io.simars.petstore.entity.pet.PetStatus;
import io.simars.petstore.model.IdName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

public class PetPayload {

    private Long petId;

    @NotNull
    @NotBlank
    private String name;

    private Set<String> photoUrls;

    private Set<String> tags;

    @NotNull
    private IdName category;

    @NotNull
    private PetStatus status;

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(Set<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public IdName getCategory() {
        return category;
    }

    public void setCategory(IdName category) {
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
        return "PetPayload{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetPayload that = (PetPayload) o;
        return Objects.equals(petId, that.petId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(petId);
    }
}