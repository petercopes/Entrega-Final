package com.coderhouse.model.request;

import lombok.*;

import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "El campo email no puede vacio")
    private String email;
    @NotBlank(message = "El campo nombre no puede vacio")
    private String name;
    @NotBlank(message = "El campo telefono no puede vacio")
    private String phone;
    @NotBlank(message = "El campo contraseña no puede vacio")
    private String password;
    @NotBlank(message = "El campo de verificacion de contraseña no puede vacio")
    private String password2;
}
