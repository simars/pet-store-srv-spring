package io.simars.petstore.model.convertor;

import io.simars.petstore.entity.image.Image;
import io.simars.petstore.entity.pet.Pet;
import io.simars.petstore.entity.tag.Tag;
import io.simars.petstore.model.IdName;
import io.simars.petstore.model.payload.PetPayload;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;
import java.util.stream.Collectors;

public class PetPayloadConvertor implements Converter<Pet, PetPayload> {

    @Override
    public PetPayload convert(Pet source) {

        final PetPayload payload = new PetPayload();
        payload.setId(source.getId());
        Optional.ofNullable(source.getCategory())
                .map(cat -> new IdName(cat.getId(), cat.getName()))
                .ifPresent(payload::setCategory);
        payload.setStatus(source.getStatus());
        payload.setPhotoUrls(source.getImages().stream().map(Image::getUrl).collect(Collectors.toSet()));
        payload.setTags(source.getTags().stream().map(Tag::getName).collect(Collectors.toSet()));
        return payload;
    }
}
