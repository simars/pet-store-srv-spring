package io.simars.petstore.entity.tag;

import io.simars.petstore.entity.BaseEntity;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "tag")
public class Tag extends BaseEntity {

    @NaturalId
    private String name;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Tag tag = (Tag) o;
        return Objects.equals(this.getName(), tag.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}