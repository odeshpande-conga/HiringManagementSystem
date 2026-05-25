package com.hiringms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobRequest {
    @NotBlank
    private String title;

    private String description;

    private String location;

    private String company;

    private String salary;

    private String type;
}
