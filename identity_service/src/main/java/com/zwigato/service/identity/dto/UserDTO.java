package com.zwigato.service.identity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record UserDTO(Long userId,
                      @Min(value = 3, message = "Name must contains at least 3 character")
                      @NotBlank(message = "Please enter first name")
                      String firstName,
                      @Min(value = 3, message = "Name must contains at least 3 character")
                      @NotBlank(message = "Please enter last name")
                      String lastName,
                      @NotBlank(message = "Please enter email")
                      String emailAddress,
                      @NotBlank(message = "Please enter contact number")
                      String contactNumber,
                      Boolean isActive,
                      @NotBlank(message = "Password can not be empty")
                      String userPassword,
                      @JsonFormat(pattern = "dd-MM-yyyy")
                      LocalDate dateOfBirth,
                      LocalDateTime createdDate,
                      LocalDateTime lastModifiedDate,
                      List<RolesDTO> roles) {

}
