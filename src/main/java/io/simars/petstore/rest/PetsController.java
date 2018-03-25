package io.simars.petstore.rest;

import io.simars.petstore.pet.Pet;
import io.simars.petstore.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pets")
public class PetsController {

    @Autowired
    private PetRepository petRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Pet> getPets() {

        return new ResponseEntity<>(petRepository.findAll().iterator().next(), HttpStatus.OK);
    }
}
