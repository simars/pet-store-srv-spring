package io.simars.petstore.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class IdName {

    public IdName(@NotNull Long id, @NotNull @NotBlank String name) {
        this.id = id;
        this.name = name;
    }

    public IdName() {
    }

    private Long id;

    @NotNull
    @NotBlank
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
