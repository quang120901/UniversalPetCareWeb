package com.springboot.universalpetcare.request;

import java.util.List;

import com.springboot.universalpetcare.model.Appointment;
import com.springboot.universalpetcare.model.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class BookAppointmentRequest {
    private Appointment appointment;
    private List<Pet> pets;

}
