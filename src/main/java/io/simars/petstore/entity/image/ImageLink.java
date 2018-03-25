package io.simars.petstore.entity.image;

import io.simars.petstore.entity.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class ImageLink<ID extends Serializable, E extends AbstractEntity<ID> & ImageLinked>
        extends AbstractEntity<ImageLink.EntityImageId> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("imageId")
    protected Image image;

    @Id
    @EmbeddedId
    public abstract EntityImageId<ID> getId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("imageId")
    public abstract E getEntity();

    public Image getImage() {
        return image;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ImageLink image = (ImageLink) o;
        return Objects.equals(getId(), image.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

    @Embeddable
    public static class EntityImageId<EID extends Serializable> implements Serializable {

        @Column(name = "entity_id")
        private EID entityId;

        @Column(name = "image_id")
        private Long imageId;

        public EntityImageId() {

        }

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

        @Override
        public int hashCode() {
            return Objects.hash(this.getImageId(), this.getEntityId());
        }

    }
}
