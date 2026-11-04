package com.springboot.universalpetcare.service.pet;

import java.util.List;

import com.springboot.universalpetcare.model.Pet;

public interface IPetService {
    List<Pet> savePetsForAppointment(List<Pet> pets);
    Pet updatePet(Pet pet, Long petId);
    void deletePet(Long petId);
    Pet getPetById(Long petId);
    List<String> getPetTypes();
    List<String> getPetColors();
    List<String> getPetBreeds(String petType);
}
