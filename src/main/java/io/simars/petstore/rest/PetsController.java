package io.simars.petstore.rest;

import io.simars.petstore.entity.pet.Pet;
import io.simars.petstore.model.payload.PetPayload;
import io.simars.petstore.repository.PageablePetRepository;
import io.simars.petstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/pets")
public class PetsController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PageablePetRepository pageablePetRepository;

    @Autowired
    ConversionService conversionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<PetPayload>> getPets() {
        return new ResponseEntity<>( StreamSupport.stream(petRepository.findAll().spliterator(),false).map(
                pet -> conversionService.convert(pet,PetPayload.class)
        ).collect(Collectors.toSet()), HttpStatus.OK);
    }
}
