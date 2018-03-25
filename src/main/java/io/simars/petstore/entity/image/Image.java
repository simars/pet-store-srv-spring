package io.simars.petstore.entity.image;

import io.simars.petstore.entity.BaseEntity;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "image")
public class Image extends BaseEntity {

    @NaturalId
    private String url;

    public Image() {
    }

    public Image(String url) {
        this(null,url);
    }


    public Image(@NotNull Long id, @NotBlank String url) {
        this.id = id;
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
