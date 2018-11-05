package com.daqi.vo;

import com.daqi.entity.Pet;

import java.util.List;

public class FormBean {
    private List<Pet> pets;

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
