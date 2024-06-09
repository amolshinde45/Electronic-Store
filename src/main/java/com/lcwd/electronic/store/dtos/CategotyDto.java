package com.lcwd.electronic.store.dtos;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategotyDto {

    private  String categoryId;
    @NotBlank
    @Min(value=4, message = "title must of minimum of 4 character")
     private String title;

    @NotBlank(message = "descriptiom Required")
    private String description;
    @NotBlank(message = "Cover image Required")
    private String coverImage;
}
