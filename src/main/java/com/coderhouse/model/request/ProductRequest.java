package com.coderhouse.model.request;

import lombok.*;

import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "El campo descripcion no puede vacio")
    private String description;
    @Max(50000)
    @Min(1)
    private Double price;
    @NotBlank(message = "El campo name no puede vacio")
    private String name;
    @NotNull(message = "El campo category es obligatorio")
    private String category;
    @NotNull(message = "El campo code es obligatorio")
    private String code;
}
