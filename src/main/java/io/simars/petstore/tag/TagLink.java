package io.simars.petstore.tag;


import io.simars.petstore.entity.AbstractEntity;
import io.simars.petstore.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class TagLink <ID extends Serializable, E extends AbstractEntity<ID> & TagLinked>
        extends AbstractEntity<TagLink.EntityTagId> {

    @EmbeddedId
    private EntityTagId<Long> id;

    @Override
    public EntityTagId<Long> getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagLink tag = (TagLink) o;
        return Objects.equals(getId(), getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

    @Embeddable
    public static class EntityTagId<EID extends Serializable> implements Serializable {

        @Column(name = "entity_id")
        private  EID entityId;

        @Column(name = "tag_id")
        private  Long tagId;

        public EntityTagId() {

        }

        public EntityTagId(EID entityId, Long tagId) {
            this.entityId = entityId;
            this.tagId = tagId;
        }

        public EID getEntityId() {
            return entityId;
        }

        public Long getTagId() {
            return tagId;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final EntityTagId that = (EntityTagId) o;
            return Objects.equals(getEntityId(), that.getEntityId()) &&
                    Objects.equals(getTagId(), that.getTagId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.getTagId(), this.getEntityId());
        }



    }
}
