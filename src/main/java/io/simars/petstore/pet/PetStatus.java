package io.simars.petstore.pet;

/**
 * Pet statuses enumerated
 * @author simars
 */
public enum PetStatus {

    AVAILABLE((short)0),PENDING((short)1),STORED((short)2);

    public final short code;

    PetStatus(short code) {
        if(this.ordinal() != code) {
            throw new IllegalArgumentException("Enum code should match its ordinal to prevent unintended reordering");
        }
        this.code = code;
    }
}
