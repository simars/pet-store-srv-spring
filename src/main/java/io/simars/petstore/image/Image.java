package io.simars.petstore.image;

import io.simars.petstore.entity.BaseEntity;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
//
////@Entity
//@Table(name = "image")
//@DiscriminatorColumn(name = "type")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Image extends BaseEntity {

    @NaturalId
    private String url;

//    @OneToMany(
//            mappedBy = "image",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<ImageLink> imageLinks = new LinkedList<>();


    public Image() {
    }

    public Image(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Image image = (Image) o;
        return Objects.equals(getUrl(), image.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }


}
