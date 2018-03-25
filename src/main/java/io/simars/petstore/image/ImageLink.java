package io.simars.petstore.image;

import io.simars.petstore.entity.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

//@Entity
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ImageLink <ID extends Serializable, E extends AbstractEntity<ID> & ImageLinked>
        extends AbstractEntity<ImageLink.EntityImageId> {

    @EmbeddedId
    private EntityImageId<Long> id;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("imageId")
//    private Image image;


    @Override
    public EntityImageId<Long> getId() {
        return id;
    }


//
//    public Image getImage() {
//        return image;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageLink image = (ImageLink) o;
        return Objects.equals(getId(), getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

    @Embeddable
    public static class EntityImageId<EID extends Serializable> implements Serializable {

        @Column(name = "entity_id")
        private final EID entityId;

        @Column(name = "image_id")
        private final Long imageId;

        public EntityImageId(EID entityId, Long imageId) {
            this.entityId = entityId;
            this.imageId = imageId;
        }

        public EID getEntityId() {
            return entityId;
        }

        public Long getImageId() {
            return imageId;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final EntityImageId that = (EntityImageId) o;
            return Objects.equals(getEntityId(), that.getEntityId()) &&
                    Objects.equals(getImageId(), that.getImageId());
        }
    }
}
