package com.cdcm.apirestpsql.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDto {
    @NotBlank(message = "Provide a name")
    private String name;

    @Email(message = "Provide a valid email")
    @NotBlank(message = "Provide an email")
    private String email;

    @NotBlank(message = "Provide a password")
    private String password;

    private List<ProductDto> products;
}
