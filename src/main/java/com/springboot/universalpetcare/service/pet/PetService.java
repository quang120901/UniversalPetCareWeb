package com.springboot.universalpetcare.service.pet;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.universalpetcare.exception.ResourceNotFoundException;
import com.springboot.universalpetcare.model.Pet;
import com.springboot.universalpetcare.repository.PetRepository;
import com.springboot.universalpetcare.ultis.FeedBackMessage;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class PetService implements IPetService{
    private final PetRepository petRepository;

    @Override
    public List<Pet> savePetsForAppointment(List<Pet> pets) {
       return petRepository.saveAll(pets);
    }

    @Override
    public Pet updatePet(Pet pet, Long petId) {
        Pet existingPet = getPetById(petId);
        existingPet.setName(pet.getName());
        existingPet.setAge(pet.getAge());
        existingPet.setColor(pet.getColor());
        existingPet.setType(pet.getType());
        existingPet.setBreed(pet.getBreed());
        return petRepository.save(existingPet);
    }

    @Override
    public void deletePet(Long petId) {
        petRepository.findById(petId).ifPresentOrElse(petRepository::delete, 
            () -> {
                throw new ResourceNotFoundException(FeedBackMessage.NOT_FOUND);
            });
    }

    @Override
    public Pet getPetById(Long petId) {
        return petRepository.findById(petId)
        .orElseThrow(() -> new ResourceNotFoundException(FeedBackMessage.NOT_FOUND));
    }
    
}
