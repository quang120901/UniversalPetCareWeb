package com.springboot.universalpetcare.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class AppointmentUpdateRequest {
    private String appoinmentDate;
    private String appoinmentTime;
    private String reason;

    
}
