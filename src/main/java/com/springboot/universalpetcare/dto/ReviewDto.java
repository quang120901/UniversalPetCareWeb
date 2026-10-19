package com.springboot.universalpetcare.dto;

import lombok.Data;

@Data 
public class ReviewDto {
    private Long id;
    private String stars;
    private String feedback;
    private Long vetId;
    private String vetName;
    private Long reviewerId;
    private String reviewerName;
    private byte[] reviewerImage;
    private byte[] vetImage;
}
