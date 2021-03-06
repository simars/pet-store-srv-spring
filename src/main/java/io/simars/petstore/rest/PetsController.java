package io.simars.petstore.rest;

import io.simars.petstore.entity.pet.Pet;
import io.simars.petstore.model.IdName;
import io.simars.petstore.model.convertor.PetInputConverter;
import io.simars.petstore.model.payload.PetPayload;
import io.simars.petstore.repository.PageablePetRepository;
import io.simars.petstore.repository.PetCategoryRepository;
import io.simars.petstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/pet")
@CrossOrigin
public class PetsController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PageablePetRepository pageablePetRepository;

    @Autowired
    ConversionService conversionService;

    @Autowired
    PetInputConverter petInputConverter;

    @Autowired
    PetCategoryRepository categoryRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<PetPayload>> getPets() {
        return new ResponseEntity<>(StreamSupport.stream(petRepository.findAll().spliterator(), false).map(
                pet -> conversionService.convert(pet, PetPayload.class)
        ).collect(Collectors.toSet()), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PetPayload> getPet(@PathVariable("id") @NotNull Long petId) {
        return this.petRepository.findById(petId)
                .map(id -> this.conversionService.convert(id, PetPayload.class))
                .map(petPayload1 -> new ResponseEntity<>(petPayload1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PetPayload> crete(@NotNull @Valid @RequestBody PetPayload payload) {
        final Pet pet = petRepository.save(petInputConverter.convert(payload));
        return new ResponseEntity<>(
                conversionService.convert(pet, PetPayload.class),
                HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PetPayload> update(
            @PathVariable("id") @NotNull Long id,
            @NotNull @Valid @RequestBody PetPayload payload) {
        if(petRepository.existsById(id)) {
            final Pet pet = petRepository.save(petInputConverter.convert(payload));
            return new ResponseEntity<>(
                    conversionService.convert(pet, PetPayload.class),
                    HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<PetPayload> removePet(@PathVariable("id") @NotNull Long petId) {
        if(this.petRepository.existsById(petId)) {
            this.petRepository.deleteById(petId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(value = "/meta/categories", method = RequestMethod.GET)
    public ResponseEntity<Iterable<IdName>> getCategories() {
        return new ResponseEntity<>(StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .map(cat -> new IdName(cat.getId(), cat.getName())).collect(Collectors.toSet()), HttpStatus.OK);
    }

}
